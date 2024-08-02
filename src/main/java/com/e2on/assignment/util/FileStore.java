package com.e2on.assignment.util;

import com.e2on.assignment.entity.Image;
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
    private String imageDir;

    public String getPath(String fullPath) {
        return fullPath.split(imageDir)[1];
    }

    public static boolean hasDirectory(String dir) {
        Path path = Paths.get(dir);

        if (Files.exists(path)) {
            return true;
        } else {
            return false;
        }
    }

    public Image storeImage(MultipartFile multipartFile) throws IOException {

        if (multipartFile.isEmpty()) {
            return null;
        }
        String uploadNameWithExt = multipartFile.getOriginalFilename();

        UUID imageUuid = UUID.randomUUID();
        String extension = extractExt(uploadNameWithExt);
        String uuidNameWithExt = imageUuid + extension;

        // mkdirs
        if (hasDirectory(Image.uploadedDir)) {
            Files.createDirectories(Paths.get(Image.uploadedDir));
            multipartFile.transferTo(new File(Image.uploadedDir + "/" + uuidNameWithExt));
        }

        String uploadName = removeExt(uploadNameWithExt);
        return new Image(imageUuid, uploadName, extension);
    }

    private String removeExt(String originalImageName) {

        int pos = originalImageName.lastIndexOf(".");
        return originalImageName.substring(0, pos);
    }

    private String extractExt(String originalImageName) {

        int pos = originalImageName.lastIndexOf(".");
        return "." + originalImageName.substring(pos + 1);
    }

//    public String getOriginalFullPath(String fileName) throws IOException {
//        Path path = Paths.get(originalFileDir);
//
//        if (Files.exists(path)) {
//            System.out.println("디렉토리가 존재합니다");
//            return originalFileDir + "/" + fileName;
//        } else {
//            System.out.println("디렉토리가 존재하지 않습니다.");
//            Files.createDirectories(Paths.get(originalTempDir));
//            return originalTempDir + "/" + fileName;
//        }
//    }
//
//    public String getStoreFullPath(String fileName) throws IOException {
//        Path path = Paths.get(storeTempDir);
//
//        if (Files.exists(path)) {
//            System.out.println("디렉토리가 존재합니다");
//            return storeFileDir + "/" + fileName;
//        } else {
//            System.out.println("디렉토리가 존재하지 않습니다.");
//            Files.createDirectories(Paths.get(storeTempDir));
//            return storeTempDir + "/" + fileName;
//        }
//    }
}
