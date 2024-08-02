package com.e2on.assignment.service;

import com.e2on.assignment.entity.Image;
import com.e2on.assignment.util.FileStore;
import com.e2on.assignment.util.PythonLogger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class AnalysisService {

    @Value("${dir.python.analysis}")
    private String analysisFile;

    private final FileStore fileStore;

    public AnalysisService(FileStore fileStore) {
        this.fileStore = fileStore;
    }

    public String[] analysisImage(Image image) throws IOException, InterruptedException {

        String uploadImageFullPath = ""; //fileStore.getOriginalFullPath(image.getUuidNameWithExt());
        String analyzedImageFullPath = ""; //fileStore.getStoreFullPath(image.getUuidNameWithExt());

        // mkdirs
        if (FileStore.hasDirectory(Image.uploadedDir)) {
            Files.createDirectories(Paths.get(Image.uploadedDir));
            uploadImageFullPath += Image.uploadedDir + "/" + image.getUuidNameWithExt();
        }

        if (FileStore.hasDirectory(Image.analyzedDir)) {
            uploadImageFullPath += (Image.analyzedDir + "/" + image.getUuidNameWithExt());
        } else {
            uploadImageFullPath += (Image.analyzedTempDir + "/" + image.getUuidNameWithExt());
        }

        ProcessBuilder pb = new ProcessBuilder("python", analysisFile, uploadImageFullPath, analyzedImageFullPath);
        pb.redirectErrorStream(true);
        Process process = pb.start();

        PythonLogger.recordLog(process);


        return new String[] {fileStore.getPath(uploadImageFullPath), fileStore.getPath(analyzedImageFullPath)};
    }
}
