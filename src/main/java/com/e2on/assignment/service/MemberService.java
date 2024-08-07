package com.e2on.assignment.service;

import com.e2on.assignment.entity.MemberEntity;
import com.e2on.assignment.repository.MemberRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional(readOnly = true)
    public MemberEntity loadUserByUsername(String loginId) throws UsernameNotFoundException {
        MemberEntity member = memberRepository.findByLoginId(loginId);
        if (member == null) {
            throw new UsernameNotFoundException("사용자가 없습니다.");
        }
        return member;
        //return toUserDetails(member);
    }

    private UserDetails toUserDetails(MemberEntity member) {
        return User.builder()
                .username(member.getLoginId())
                .password(member.getPassword())
               .build();
    }

    public void save(MemberEntity member) {
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        member.setId(UUID.randomUUID());
        member.setCreatedAt(LocalDateTime.now());
        member.setUpdatedAt(LocalDateTime.now());
        memberRepository.save(member);
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return null;
//    }
}
