package com.e2on.assignment.repository;

import com.e2on.assignment.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MemberRepository extends JpaRepository<MemberEntity, UUID> {

}
