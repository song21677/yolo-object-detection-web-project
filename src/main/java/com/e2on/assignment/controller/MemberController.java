package com.e2on.assignment.controller;

import com.e2on.assignment.entity.MemberEntity;
import com.e2on.assignment.service.MemberService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/sign-up-page")
    public String signUpPage() {
        return "signUpForm";
    }

    @PostMapping("/sign-up")
    @ResponseBody
    public void signUp(MemberEntity member) {
        memberService.save(member);
    }

    @GetMapping("/login-page")
    public String loginPage() {
        return "loginForm";
    }

    @GetMapping("/login-check")
    @ResponseBody
    public String loginCheck(@AuthenticationPrincipal MemberEntity session) {
        if (session == null) {
            return "anonymous";
        }
        return session.getLoginId();
    }
}
