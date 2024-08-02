package com.e2on.assignment.util;

import com.e2on.assignment.entity.AnalysisResult;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PythonLogger {

    public static List<AnalysisResult> recordLog(Process process) throws IOException, InterruptedException {
        InputStream inputStream = process.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        List<AnalysisResult> results = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);

            String regex = "xywhn\tclass\tconfidence\t([0-9.\t]+)";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(line);

            if (matcher.find()) {
                String group = matcher.group(1);
                String[] imageInfo = group.split("\t");

                Double x = Double.parseDouble(imageInfo[0]);
                Double y = Double.parseDouble(imageInfo[1]);
                Double w = Double.parseDouble(imageInfo[2]);
                Double h = Double.parseDouble(imageInfo[3]);
                Integer cls = Integer.parseInt(imageInfo[4]);
                Double confidence = Double.parseDouble(imageInfo[5]);
                results.add(new AnalysisResult(x, y, w, h, cls, confidence));
            }
        }

        return results;
    }
}
