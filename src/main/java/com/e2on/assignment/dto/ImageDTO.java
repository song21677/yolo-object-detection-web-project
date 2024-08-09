package com.e2on.assignment.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
public class ImageDTO {

    private UUID id;

    private String storedName;

    private String uploadedName;

    private String owner;

    private LocalDate requestAt;

    private String mimeType;

    private Long uploadedSize;

    private Long analyzedSize;

    private LocalDateTime analyzedAt;

    private List<AnalysisResultDTO> analysisResultDTOList;

}
