package com.coup.service;

import com.coup.domain.Member;
import com.coup.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
//@Transactional(readOnly = true)
@RequiredArgsConstructor // final 필드만 가지고 생성자를 만들어 줌. 가장 최선
public class MemberService {

    // Test 케이스 작성할 때 유리하기 때문에 생성자 injection 사용
    private final MemberRepository memberRepository;

    /**
     * 회원 가입
     * return Member.Id
     */
    @Transactional
    public Long join(Member member) {

        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    /**
     * 중복회원 검증
     * */
    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByUserName(member.getUsername());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    /**
     * 회원 전체 조회
     * */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * Id 로 회원 조회
     * */
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

    /**
     * 회원 가입
     * return Member
     */
    @Transactional
    public Member createMember(Member member) {
        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        Long memberId = member.getId();
        return memberRepository.findOne(memberId);
    }

    @Transactional
    public void update(Long id, String username) {
        Member member = memberRepository.findOne(id);
        member.setUsername(username);
    }
}
