package com.e2on.assignment.controller;

import com.e2on.assignment.entity.Image;
import com.e2on.assignment.service.AnalysisService;
import com.e2on.assignment.service.ImageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class ImageController {

    private final ImageService imageService;
    private final AnalysisService analysisService;

    public ImageController(ImageService imageService, AnalysisService analysisService) {
        this.imageService = imageService;
        this.analysisService = analysisService;
    }

    @GetMapping("/upload-form")
    public String imageUploadForm() {
        return "imageUploadForm";
    }

    @PostMapping("/analysis")
    @ResponseBody
    public String[] analysisImage(MultipartFile image) throws IOException, InterruptedException {

        Image savedImage = imageService.saveImage(image);
        String[] images = analysisService.analysisImage(savedImage);

        return images;
    }
}
