package com.coup.api;

import com.coup.domain.Member;
import com.coup.service.MemberService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberApiController {
    private final MemberService memberService;

    @GetMapping("/api/members")
    public Result retrieveMembers(){
        List<Member> findMembers = memberService.findMembers();

        List<MemberDto> collect = findMembers.stream()
                .map(m -> new MemberDto(m.getUsername(), m.getEmail()))
                .collect(Collectors.toList());

        // JSON 배열 타입으로 내보내지 않기 위해 List로 만들어서 내보낸다. (이렇게 해야 확장성이 더 좋음)
        return new Result(collect.size(), collect);

    }

    @Data @AllArgsConstructor
    static class Result<T> {
        private int count;
        private T data;
    }
    @Data
    @AllArgsConstructor
    static class MemberDto {
        private String username;
        private String email;
    }

    @PutMapping("/api/members/{id}")
    public UpdateMemberResponse updateMemberV2(
            @PathVariable("id") Long id,
            @RequestBody @Valid UpdateMemberRequest request) {

        memberService.update(id, request.getUsername());
        Member findMember = memberService.findOne(id);
        return new UpdateMemberResponse(findMember.getId(), findMember.getUsername());
    }

    @Data
    static class UpdateMemberRequest {
        private String username;
    }
    @Data
    @AllArgsConstructor
    static class UpdateMemberResponse {
        private Long id;
        private String username;
    }

    //    @PostMapping("/api/v1/members")
//    public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member) {
//        Long id = memberService.join(member);
//        return new CreateMemberResponse(id);
//    }

    // /api/v2/members
    @PostMapping("/api/members") // Entity가 변경되어도 API 스팩이 변경되지 않는다. Entity는 외부에 노출하면 안된다.
    public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest request) {
        Member member = new Member();
        member.setUsername(request.getUsername());
        member.setEmail(request.getEmail());

        Long id = memberService.join(member);

        return new CreateMemberResponse(id);
    }
    @Data
    static class CreateMemberRequest {
        @NotEmpty
        private String username;
        private String email;

    }
    @Data
    static class CreateMemberResponse {
        private Long id;

        public CreateMemberResponse(Long id) {
            this.id = id;
        }
    }

}
