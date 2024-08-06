package com.e2on.assignment.entity;

import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Setter
@NoArgsConstructor
@Entity
@Table(name = "analysis_result")
public class AnalysisResultEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "image_uuid")
    private UUID imageUuid;

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

    @Column(name="cls")
    private Integer cls;

    @Column(name = "confidence")
    private Double confidence;

    public AnalysisResultEntity(Double x, Double y, Double w, Double h, Integer cls, Double confidence) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.cls = cls;
        this.confidence = confidence;
    }
}
