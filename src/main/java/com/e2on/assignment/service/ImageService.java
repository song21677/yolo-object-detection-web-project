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
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
    public ImageEntity analysisImage(MultipartFile image) throws Exception {

        ImageEntity originalImage = saveImage(image);
        List<AnalysisResultEntity> analysisResults = analysisService.analysisImage(originalImage);
        File file = new File(AnalysisService.analyzedDir + "/" + originalImage.getUuidNameWithExt());
        originalImage.setAnalyzedSize(file.length());
        originalImage.setAnalyzedAt(LocalDateTime.now());
        analysisResultRepository.saveAll(analysisResults);

        return imageRepository.save(originalImage);
    }

    @Transactional
    public ImageEntity saveImage(MultipartFile multipartFile) throws IOException {
        if (multipartFile.isEmpty()) {
            return null;
        }

        if (!Files.exists(Paths.get(uploadedDir))) {
            Files.createDirectories(Paths.get(uploadedDir));
        }

        Tika tika = new Tika();
        ImageEntity image = new ImageEntity();

//        InputStream in = null;
//        InputStream in2 = null;

        String mimeType = null;
        try (InputStream in = multipartFile.getInputStream()) {
            mimeType = tika.detect(in);
        }

        BufferedImage bufferedImage = null;
        try (InputStream in = multipartFile.getInputStream()) {
            bufferedImage = ImageIO.read(in);
        }

//            in = multipartFile.getInputStream();
//            in2 = multipartFile.getInputStream();

        image.setId(UUID.randomUUID());
//        MemberEntity testMember = memberRepository.findById(UUID.fromString("ef2becd2-e00d-43f4-af1b-93e08509bb2a")).get();
//        image.setMember(testMember);
        image.setMemberId(UUID.fromString("ef2becd2-e00d-43f4-af1b-93e08509bb2a"));
        image.setUploadedName(removeExt(multipartFile.getOriginalFilename()));
        image.setExtension(extractExt(multipartFile.getOriginalFilename()));
        image.setUploadedSize(multipartFile.getSize());
//            image.setMimeType(tika.detect(in));
        image.setMimeType(mimeType);
//            BufferedImage bufferedImage = ImageIO.read(in2);
        image.setWidth(bufferedImage.getWidth());
        image.setHeight(bufferedImage.getHeight());
        image.setUploadedAt(LocalDateTime.now());
//        } finally {
//            if (in != null) in.close();
//            if (in2 != null) in2.close();
//        }

        multipartFile.transferTo(new File(uploadedDir + "/" + image.getUuidNameWithExt()));
        imageRepository.save(image);

        return image;
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
