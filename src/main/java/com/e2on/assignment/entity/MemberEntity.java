package com.e2on.assignment.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "member")
public class MemberEntity implements UserDetails {

    @Id
    @Column(name = "id")
    private UUID id;
    @Column(name = "login_id")
    private String loginId;
    @Column(name = "password")
    private String password;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // 사용자에게 부여된 권한(롤)을 GrantedAuthority 객체의 컬렉션으로 반환
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> auth = new ArrayList<>();
        auth.add(new SimpleGrantedAuthority("USER"));
        return auth;
    }

    @Override
    public String getUsername() {
        return loginId;
    }

    // 만료되지 않았나?
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정 안잠겼나?
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 비밀번호 만료되지 않았나?
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정 활성화 되어 있나?
    @Override
    public boolean isEnabled() {
        return true;
    }
}
