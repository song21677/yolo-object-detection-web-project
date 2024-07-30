package com.e2on.assignment.util;

import com.e2on.assignment.entity.UploadFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Component
public class FileStore {


    @Value("${dir}")
    private String fileDir;

    // 원본
    @Value("${dir.images.original}")
    private String originalFileDir;

    // 분석
    @Value("${dir.images.analysis}")
    private String storeFileDir;

    @Value("${tempdir.images.original}")
    private String originalTempDir;

    @Value("${tempdir.images.analysis}")
    private String storeTempDir;

    public String getPath(String fullPath) {
        return fullPath.split(fileDir)[1];
    }

    public String getOriginalFullPath(String fileName) throws IOException {
        Path path = Paths.get(originalFileDir);

        if (Files.exists(path)) {
            System.out.println("디렉토리가 존재합니다");
            return originalFileDir + "/" + fileName;
        } else {
            System.out.println("디렉토리가 존재하지 않습니다.");
            Files.createDirectories(Paths.get(originalTempDir));
            return originalTempDir + "/" + fileName;
        }
    }

    public String getStoreFullPath(String fileName) throws IOException {
        Path path = Paths.get(storeTempDir);

        if (Files.exists(path)) {
            System.out.println("디렉토리가 존재합니다");
            return storeFileDir + "/" + fileName;
        } else {
            System.out.println("디렉토리가 존재하지 않습니다.");
            Files.createDirectories(Paths.get(storeTempDir));
            return storeTempDir + "/" + fileName;
        }
    }

    public UploadFile storeFile(MultipartFile multipartFile) throws IOException {

        if (multipartFile.isEmpty()) {
            return null;
        }

        String originalFilename = multipartFile.getOriginalFilename();
        String storeFileName = createStoreFileName(originalFilename);
        // transferTo: 지정한 경로에 파일이 저장된다.
        // File: 파일과 폴더 경로를 추상화하는 것
        System.out.println(getOriginalFullPath(storeFileName));
        multipartFile.transferTo(new File(getOriginalFullPath(storeFileName)));

        return new UploadFile(originalFilename, storeFileName);
    }

    private String createStoreFileName(String originalFilename) {

        String uuid = UUID.randomUUID().toString();
        String ext = extractExt(originalFilename);
        return uuid + "." + ext;
    }

    private String extractExt(String originalFilename) {

        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }
}
