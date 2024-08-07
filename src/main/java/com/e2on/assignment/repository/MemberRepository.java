package com.e2on.assignment.repository;

import com.e2on.assignment.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, UUID> {

    @Query(value = "SELECT m FROM MemberEntity m WHERE m.loginId = :loginId")
    public MemberEntity findByLoginId(@Param("loginId") String loginId);
}
