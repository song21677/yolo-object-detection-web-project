package com.e2on.assignment.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AnalysisResultDTO {

    //private ImageDTO imageDto;

    private Double x;

    private Double y;

    private Double w;

    private Double h;

    private Integer cls;

    private Double confidence;
}
