package com.e2on.assignment.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PythonLogger {

    public static void recordLog(Process process) throws IOException, InterruptedException {
        InputStream inputStream = process.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);

            String regex ="xywhn\tclass\tconfidence\t([0-9.\t]+)";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(line);

            if (matcher.find()) {
                String group = matcher.group(1);
                String[] result = group.split("\t");
            }
        }

        process.waitFor();
    }
}
