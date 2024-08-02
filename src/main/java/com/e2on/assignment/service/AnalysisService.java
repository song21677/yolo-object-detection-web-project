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

    @Value("${dir.images.analysis}")
    private String analyzedDir;
    @Value("${dir.python.analysis}")
    private String analysisFile;

    private final FileStore fileStore;

    public AnalysisService(FileStore fileStore) {
        this.fileStore = fileStore;
    }

    public String[] analysisImage(Image image) throws IOException, InterruptedException {

        String uploadImageFullPath = FileStore.uploadedDir + "/" + image.getUuidNameWithExt();
        String analyzedImageFullPath = analyzedDir + "/" + image.getUuidNameWithExt();

        if (!Files.exists(Paths.get(analyzedDir))) {
            Files.createDirectories(Paths.get(analyzedDir));
        }

        ProcessBuilder pb = new ProcessBuilder("python", analysisFile, uploadImageFullPath, analyzedImageFullPath);
        pb.redirectErrorStream(true);
        Process process = pb.start();

        /*
        * void process(String command, Consumer consumer) {
        *     ProcessBuilder .... <-- command
        *     Process run
        *     while stream
        *         consumer.accept(stream line)
        *     wait
        * }
        *
        * process("python ...", consumer -> {
        *    System.out.print(consumer)
        * });
        *
        * */

        PythonLogger.recordLog(process);

        process.waitFor();

        return new String[] {fileStore.getPath(uploadImageFullPath), fileStore.getPath(analyzedImageFullPath)};
    }
}
