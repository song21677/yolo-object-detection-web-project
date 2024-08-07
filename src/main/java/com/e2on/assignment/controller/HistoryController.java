package com.e2on.assignment.controller;

import com.e2on.assignment.dto.ImageDTO;
import com.e2on.assignment.entity.MemberEntity;
import com.e2on.assignment.service.HistoryService;
import com.e2on.assignment.service.ImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class HistoryController {

    private final ImageService imageService;
    private final HistoryService historyService;

    public HistoryController(ImageService imageService, HistoryService historyService) {
        this.imageService = imageService;
        this.historyService = historyService;
    }

    @GetMapping("/history-board")
    public String historyBoard() {
        return "historyBoard";
    }

    @GetMapping("/history")
    @ResponseBody
    public ResponseEntity<List<ImageDTO>> showHistoryList(@AuthenticationPrincipal MemberEntity session) {
        List<ImageDTO> historyList = historyService.showHistoryList(session);
        return new ResponseEntity<>(historyList, HttpStatus.OK);
    }

    @GetMapping("/history/{id}")
    @ResponseBody
    public ImageDTO showHistory(@PathVariable("id") String id) {
        return historyService.showHistory(id);
    }
}
