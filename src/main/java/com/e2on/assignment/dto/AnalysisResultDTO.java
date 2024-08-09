package com.e2on.assignment.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AnalysisResultDTO {

    private Double x;

    private Double y;

    private Double w;

    private Double h;

    private String cls;

    private Double confidence;
}
