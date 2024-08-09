package com.e2on.assignment.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "analysis_result")
public class AnalysisResultEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "image_uuid")
    private UUID imageId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_uuid", insertable = false, updatable = false)
    private ImageEntity image;

    @Column(name = "x")
    private Double x;

    @Column(name = "y")
    private Double y;

    @Column(name = "w")
    private Double w;

    @Column(name = "h")
    private Double h;

    @Convert(converter = ClsCodeConverter.class)
    @Column(name="cls")
    private ClsCode cls;

    @Column(name = "confidence")
    private Double confidence;
}