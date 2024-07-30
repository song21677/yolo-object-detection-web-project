package com.e2on.assignment.entity;

import javax.persistence.*;

@Entity
@Table(name = "image")
public class UploadFile {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String uploadFileName;

    @Column(nullable = false, unique = true)
    private String storeFileName;

    public UploadFile() {

    }

    public UploadFile(String uploadFileName, String storeFileName) {
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }

    public String getStoreFileName() {
        return storeFileName;
    }
}
