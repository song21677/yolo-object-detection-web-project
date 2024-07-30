package com.e2on.assignment.service;

import com.e2on.assignment.entity.UploadFile;
import com.e2on.assignment.util.FileStore;
import com.e2on.assignment.util.PythonLogger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
public class AnalysisService {

    @Value("${dir.python.analysis}")
    private String analysisFile;

    private final FileStore fileStore;

    public AnalysisService(FileStore fileStore) {
        this.fileStore = fileStore;
    }

    public String[] analysisImage(UploadFile image) throws IOException, InterruptedException {

        String originalImageFullPath = fileStore.getOriginalFullPath(image.getStoreFileName());
        String storeImageFullPath = fileStore.getStoreFullPath(image.getStoreFileName());

        ProcessBuilder pb = new ProcessBuilder("python", analysisFile, originalImageFullPath, storeImageFullPath);
        Process process = pb.start();

        pb.redirectErrorStream(true);
        PythonLogger.recordLog(process);


        return new String[] {fileStore.getPath(originalImageFullPath), fileStore.getPath(storeImageFullPath)};
    }
}
