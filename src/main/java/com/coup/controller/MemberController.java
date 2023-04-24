package com.coup.controller;

import com.coup.domain.Member;
import com.coup.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
//@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    //의존성 주입 (생성자)
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping(path = "/test") //http://localhost:8080/test
    public String test() {
        return "test success";
    }

    @GetMapping("/members")
    public List<Member> retrieveAllUsers() {
        return memberService.findMembers();
    }

    // GET /members/1 or /members/10 -> String
    @GetMapping("/members/{id}")
    public Member retrieveUser(@PathVariable Long id) {
        return memberService.findOne(id);
    }

    @PostMapping("/members")
    public Member createUser(@RequestBody Member member) {
        return memberService.createMember(member);
    }

}
