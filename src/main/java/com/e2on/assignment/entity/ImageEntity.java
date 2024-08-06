package com.e2on.assignment.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@DynamicInsert
@Entity
@Table(name = "image")
public class ImageEntity {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "member_id")
    private UUID memberId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", insertable = false, updatable = false)
    private MemberEntity member;

    @Column(name = "uploaded_name")
    private String uploadedName;

    @Column(name = "extension")
    private String extension;

    @Column(name = "mime_type")
    private String mimeType;

    @Column(name = "uploaded_size")
    private Long uploadedSize;

    @Column(name = "analyzed_size")
    private Long analyzedSize;

    @Column(name = "width")
    private Integer width;

    @Column(name = "height")
    private Integer height;

    @ColumnDefault(value = "current_timestamp")
    @Column(name = "uploaded_at")
    private LocalDateTime uploadedAt;

    @Column(name = "analyzed_at")
    private LocalDateTime analyzedAt;

    public String getUuidNameWithExt() {
        return id + "." + extension;
    }

    public String getUploadedNameWithExt() {
        return uploadedName + "." + extension;
    }
}
