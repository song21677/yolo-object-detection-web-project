package com.e2on.assignment.service;

import com.e2on.assignment.entity.AnalysisResultEntity;
import com.e2on.assignment.entity.ImageEntity;
import com.e2on.assignment.repository.AnalysisResultRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AnalysisService {

    public static String analyzedDir;

    @Value("${dir.python.analysis}")
    private String analysisFile;

    private final AnalysisResultRepository analysisResultRepository;

    public AnalysisService(AnalysisResultRepository analysisResultRepository) {
        this.analysisResultRepository = analysisResultRepository;
    }

    @Transactional
    public List<AnalysisResultEntity> analysisImage(ImageEntity image) throws IOException, InterruptedException {

        if (!Files.exists(Paths.get(analyzedDir))) {
            Files.createDirectories(Paths.get(analyzedDir));
        }
        String uploadImageFullPath = ImageService.uploadedDir + "/" + image.getUuidNameWithExt();
        String analyzedImageFullPath = analyzedDir + "/" + image.getUuidNameWithExt();
        ProcessBuilder pb = new ProcessBuilder("python", analysisFile, uploadImageFullPath, analyzedImageFullPath);
        pb.redirectErrorStream(true);
        Process process = pb.start();
        InputStream inputStream = process.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        List<AnalysisResultEntity> results = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
            String regex = "xywhn\tclass\tconfidence\t([0-9.\t]+)";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                String group = matcher.group(1);
                String[] imageInfo = group.split("\t");
                AnalysisResultEntity analysisResult = new AnalysisResultEntity();
                analysisResult.setImageId(image.getId());
                analysisResult.setX(Double.parseDouble(imageInfo[0]));
                analysisResult.setY(Double.parseDouble(imageInfo[1]));
                analysisResult.setW(Double.parseDouble(imageInfo[2]));
                analysisResult.setH(Double.parseDouble(imageInfo[3]));
                analysisResult.setCls((int) Double.parseDouble(imageInfo[4]));
                analysisResult.setConfidence(Double.parseDouble(imageInfo[5]));
                results.add(analysisResult);
            }
        }
        reader.close();
        process.waitFor();

        return results;
    }

    @Value("${dir.images.analysis}")
    private void setAnalyzedDir(String analyzedDir) {
        AnalysisService.analyzedDir = analyzedDir;
    }
}
