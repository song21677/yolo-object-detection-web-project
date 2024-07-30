package com.e2on.assignment.service;

import com.e2on.assignment.entity.UploadFile;
import com.e2on.assignment.repository.FileRepository;
import com.e2on.assignment.util.FileStore;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageService {

    private final FileStore fileStore;
    private final FileRepository fileRepository;

    public ImageService(FileStore fileStore, FileRepository fileRepository) {
        this.fileStore = fileStore;
        this.fileRepository = fileRepository;
    }

    public UploadFile saveImage(MultipartFile image) throws IOException {
        UploadFile uploadFile = fileStore.storeFile(image);
        return fileRepository.save(uploadFile);
    }
}
