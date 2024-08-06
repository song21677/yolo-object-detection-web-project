package com.e2on.assignment.controller;

import com.e2on.assignment.entity.ImageEntity;
import com.e2on.assignment.service.AnalysisService;
import com.e2on.assignment.service.ImageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService, AnalysisService analysisService) {
        this.imageService = imageService;
    }

    @GetMapping("/upload-form")
    public String imageUploadForm() {
        return "imageUploadForm";
    }

    @PostMapping("/analysis")
    @ResponseBody
    public String analysisImage(MultipartFile image) throws Exception {

//        Image originalImage = imageService.saveImage(image);
//        analysisService.analysisImage(originalImage);

        ImageEntity analyzedImage = imageService.analysisImage(image);

        return analyzedImage.getUuidNameWithExt();
    }


}
