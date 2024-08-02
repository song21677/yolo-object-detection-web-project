package com.e2on.assignment.service;

import com.e2on.assignment.entity.Image;
import com.e2on.assignment.repository.FileRepository;
import com.e2on.assignment.util.FileStore;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class ImageService {

    private final FileStore fileStore;
    private final FileRepository fileRepository;

    public ImageService(FileStore fileStore, FileRepository fileRepository) {
        this.fileStore = fileStore;
        this.fileRepository = fileRepository;
    }

    public Image saveImage(MultipartFile image) throws IOException {
        Image storedImage = fileStore.storeImage(image);
//        Image image1 = new Image();
//        ...;
//        image1.setUploadedAt(LocalDateTime.now());
        return fileRepository.save(storedImage);
    }
}
