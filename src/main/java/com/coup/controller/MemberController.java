package com.coup.controller;

import com.coup.domain.Member;
import com.coup.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
//@RequestMapping("/user")
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

    @GetMapping("/users")
    public List<Member> retrieveAllUsers() {
        return memberService.findMembers();
    }

    // GET /users/1 or /users/10 -> String
    @GetMapping("/users/{id}")
    public Member retrieveUser(@PathVariable Long id) {
        return memberService.findOne(id);
    }

    @PostMapping("/users")
    public Member createUser(@RequestBody Member member) {
        return memberService.createMember(member);
    }

}
