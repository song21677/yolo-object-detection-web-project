package com.e2on.assignment.repository;

import com.e2on.assignment.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ImageRepository extends JpaRepository<ImageEntity, UUID> {
    @Query(value = "SELECT img FROM ImageEntity img WHERE img.memberId = :memberId")
    List<ImageEntity> findAllByMemberId(@Param("memberId") UUID memberId);
}
