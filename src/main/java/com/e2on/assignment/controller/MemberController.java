package com.e2on.assignment.controller;

import com.e2on.assignment.entity.MemberEntity;
import com.e2on.assignment.service.MemberService;
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
//        BCryptPasswordEncoder bpe = new BCryptPasswordEncoder();
//        String encode = bpe.encode("1234");
//        System.out.println(encode);
        return "signUpForm";
    }

    @PostMapping("/sign-up")
    @ResponseBody
    public String signUp(MemberEntity member) {
//        @ModelAttribute MemberEntity member
        memberService.save(member);
        return "hi";
    }

    @GetMapping("/login-page")
    public String loginPage() {
        return "loginForm";
    }


}
