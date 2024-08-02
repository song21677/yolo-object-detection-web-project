package com.e2on.assignment.entity;

import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Image {

    @Id
    private UUID uuid;
    @Column
    private String uploadName;
    @Column
    private String extension;
    @Column
    private LocalDateTime uoloadedAt;
    @Column
    private LocalDateTime analyziedAt;

    @Value("${dir}")
    private String imageDir;

    // 원본 이미지 폴더
    @Value("${dir.images.original}")
    public static String uploadedDir;
    // 원본 이미지 임시 폴더
    @Value("${tempdir.images.original}")
    public static String uploadedTempDir;
    // 분석 이미지 폴더
    @Value("${dir.images.analysis}")
    public static String analyzedDir;
    // 분석 이미지 임시 폴더
    @Value("${tempdir.images.analysis}")
    public static String analyzedTempDir;

     //확장자 붙은 uuid 이름
    @Transient
    private String uuidNameWithExt;
    // 확장자 붙은 업로드 이름
    @Transient
    private String uploadNameWithExt;

    public Image() {

    }

    public Image(UUID uuid, String uploadName, String extension
//            , String uuidNameWithExt, String uploadNameWithExt
    ) {
        this.uuid = uuid;
        this.uploadName = uploadName;
        this.extension = extension;
//        this.uuidNameWithExt = uuidNameWithExt;
//        this.uploadNameWithExt = uploadNameWithExt;
    }

//    public String getUuidNameWithExt() {
//        return uuidNameWithExt;
//    }
//
//    public String getUploadNameWithExt() {
//        return uploadNameWithExt;
//    }

    public String getUuidNameWithExt() {
        return uuid + "." + extension;
    }
}
