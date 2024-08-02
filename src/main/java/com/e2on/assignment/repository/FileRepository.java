package com.e2on.assignment.repository;

import com.e2on.assignment.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FileRepository extends JpaRepository<Image, UUID> {
}
