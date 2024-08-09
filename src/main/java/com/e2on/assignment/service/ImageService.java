package com.e2on.assignment.service;

import com.e2on.assignment.entity.AnalysisResultEntity;
import com.e2on.assignment.entity.ImageEntity;
import com.e2on.assignment.entity.MemberEntity;
import com.e2on.assignment.repository.AnalysisResultRepository;
import com.e2on.assignment.repository.ImageRepository;
import com.e2on.assignment.repository.MemberRepository;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ImageService {

    public static String uploadedDir;
    private final ImageRepository imageRepository;
    private final AnalysisService analysisService;
    private final AnalysisResultRepository analysisResultRepository;
    private final MemberRepository memberRepository;

    public ImageService(ImageRepository imageRepository, AnalysisService analysisService,
                        AnalysisResultRepository analysisResultRepository, MemberRepository memberRepository) {
        this.analysisService = analysisService;
        this.imageRepository = imageRepository;
        this.analysisResultRepository = analysisResultRepository;
        this.memberRepository = memberRepository;
    }

    @Transactional
    public ImageEntity analysisImage(MultipartFile image, UUID id) throws Exception {

        ImageEntity originalImage = uploadImage(image, id);
        List<AnalysisResultEntity> analysisResults = analysisService.analysisImage(originalImage);
        File file = new File(AnalysisService.analyzedDir + "/" + originalImage.getUuidNameWithExt());
        originalImage.setAnalyzedSize(file.length());
        originalImage.setAnalyzedAt(LocalDateTime.now());
        analysisResultRepository.saveAll(analysisResults);

        return imageRepository.save(originalImage);
    }

    @Transactional
    public ImageEntity uploadImage(MultipartFile multipartFile, UUID id) throws IOException {
        if (multipartFile.isEmpty()) {
            return null;
        }

        if (!Files.exists(Paths.get(uploadedDir))) {
            Files.createDirectories(Paths.get(uploadedDir));
        }

        Tika tika = new Tika();
        ImageEntity image = new ImageEntity();


        String mimeType = null;
        try (InputStream in = multipartFile.getInputStream()) {
            mimeType = tika.detect(in);
        }

        BufferedImage bufferedImage = null;
        try (InputStream in = multipartFile.getInputStream()) {
            bufferedImage = ImageIO.read(in);
        }

        image.setId(UUID.randomUUID());
        image.setMemberId(id);
        image.setUploadedName(removeExt(multipartFile.getOriginalFilename()));
        image.setExtension(extractExt(multipartFile.getOriginalFilename()));
        image.setUploadedSize(multipartFile.getSize());
        image.setMimeType(mimeType);
        image.setWidth(bufferedImage.getWidth());
        image.setHeight(bufferedImage.getHeight());
        image.setUploadedAt(LocalDateTime.now());

        multipartFile.transferTo(new File(uploadedDir + "/" + image.getUuidNameWithExt()));
        imageRepository.save(image);

        return image;
    }

    @Transactional
    public File downloadImage(String imageName, String type) {
        File file = null;

        if (type.equals("original")) {
            file = new File(uploadedDir + "/" + imageName);
        } else if (type.equals("analysis")) {
            file = new File(AnalysisService.analyzedDir + "/" + imageName);
        } else {
            throw new IllegalArgumentException("잘못된 type입니다.");
        }

        return file;
    }

    private String extractExt(String originalImageName) {

        int pos = originalImageName.lastIndexOf(".");
        return "." + originalImageName.substring(pos + 1);
    }

    private String removeExt(String originalImageName) {

        int pos = originalImageName.lastIndexOf(".");
        return originalImageName.substring(0, pos);
    }

    @Value("${dir.images.original}")
    private void setUploadedDir(String imageDir) {
        ImageService.uploadedDir = imageDir;
    }
}
