package com.e2on.assignment.dto;

import com.e2on.assignment.entity.ImageEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
public class ImageDTO {

    private String uploadedName;

    private String owner;

    private String requestAt;

    private String mimeType;

    private Long uploadedSize;

    private Long analyzedSize;

    private LocalDateTime analyzedAt;

    private List<AnalysisResultDTO> analysisResultDTOList;

}
