package com.e2on.assignment.controller;

import com.e2on.assignment.entity.ImageEntity;
import com.e2on.assignment.entity.MemberEntity;
import com.e2on.assignment.service.AnalysisService;
import com.e2on.assignment.service.ImageService;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

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
    public String analysisImage(@AuthenticationPrincipal MemberEntity session, MultipartFile image) throws Exception {

        ImageEntity analyzedImage = imageService.analysisImage(image, session.getId());

        return analyzedImage.getUuidNameWithExt();
    }

    @GetMapping("/download/{file}")
    public void downloadImage(@PathVariable("file") String imageName, @Param("type") String type,
                              HttpServletResponse response) throws Exception {

        File file = imageService.downloadImage(imageName, type);

        response.setContentType("application/download");
        response.setContentLength((int) file.length());
        response.setHeader("Content-disposition", "attachment;filename=\"" + imageName + "\"");
        OutputStream os = response.getOutputStream();
        FileInputStream stream = new FileInputStream(file);
        FileCopyUtils.copy(stream, os);
        stream.close();
        os.close();
    }

}
