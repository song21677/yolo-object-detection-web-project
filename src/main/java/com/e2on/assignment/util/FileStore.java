package com.e2on.assignment.util;

import com.e2on.assignment.entity.Image;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Component
public class FileStore {
    public static String imageDir;
    public static String uploadedDir;

    public Image storeImage(MultipartFile multipartFile) throws IOException {

        if (multipartFile.isEmpty()) {
            return null;
        }

        // mkdirs
        if (!Files.exists(Paths.get(uploadedDir))) {
            Files.createDirectories(Paths.get(uploadedDir));
        }

        Image uuidImage = createImage(multipartFile.getOriginalFilename());

        multipartFile.transferTo(new File(uploadedDir + "/" + uuidImage.getUuidNameWithExt()));

        return uuidImage;
    }

    private Image createImage(String originalFilename) {
        UUID imageUuid = UUID.randomUUID();
        String extension = extractExt(originalFilename);
        String uploadName = removeExt(originalFilename);

        return new Image(imageUuid, uploadName, extension);
//        return null;
    }

    private String extractExt(String originalImageName) {

        int pos = originalImageName.lastIndexOf(".");
        return "." + originalImageName.substring(pos + 1);
    }

    private String removeExt(String originalImageName) {

        int pos = originalImageName.lastIndexOf(".");
        return originalImageName.substring(0, pos);
    }

    public String getPath(String fullPath) {
        return fullPath.split(imageDir)[1];
    }

    @Value("${dir}")
    public void setImageDir(String imageDir) {
        FileStore.imageDir = imageDir;
    }

    @Value("${dir.images.original}")
    public void setUploadedDir(String uploadedDir) {
        FileStore.uploadedDir = uploadedDir;

    }
}
