package com.e2on.assignment.repository;

import com.e2on.assignment.entity.AnalysisResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AnalysisResultRepository extends JpaRepository<AnalysisResultEntity, Long> {

    @Query(value = "SELECT rs FROM AnalysisResultEntity rs WHERE rs.imageId = :imageId")
    List<AnalysisResultEntity> findByImageId(@Param("imageId") UUID ImageId);

    @Query(value = "SELECT rs FROM AnalysisResultEntity rs JOIN FETCH rs.image WHERE rs.imageId = :imageId")
    List<AnalysisResultEntity> findAllByImageId(@Param("imageId") UUID ImageId);
}