package com.e2on.assignment.entity;

import com.e2on.assignment.util.FileUtils;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@DynamicInsert
@Entity
@Table(name = "image")
@Getter
@Setter
public class Image {

    @Id
    @Column(name = "uuid")
    private UUID uuid;

    @Column(name = "uploaded_name")
    private String uploadedName;

    @Column(name = "extension")
    private String extension;

    @ColumnDefault(value = "current_timestamp")
    @Column(name = "uploaded_at")
    private LocalDateTime uploadedAt;

    @Column(name = "analyzed_at")
    private LocalDateTime analyzedAt;

    public Image() {

    }

    public Image(UUID uuid, String uploadName, String extension) {
        this.uuid = uuid;
        this.uploadedName = uploadName;
        this.extension = extension;
    }

    public String getUuidNameWithExt() {
        return uuid + "." + extension;
    }
//
//    private Image generateFileData(String originalFilename) {
//        UUID imageUuid = UUID.randomUUID();
//        String extension = FileUtils.extractExt(originalFilename);
//        String uploadName = FileUtils.removeExt(originalFilename);
////        return new Image(imageUuid, uploadName, extension);
//        Image image = new Image();
//        image.setUuid(imageUuid);
//        image.setUploadedName(uploadedName);
//        image.setExtension(extension);
//        return image;
//    }
}
