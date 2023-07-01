package com.coup.repository;


import com.coup.domain.Member;
import com.coup.service.MemberService;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberService memberService;

    @Test
    @Transactional
    public void testMember() throws Exception {

        //given
        Member member = new Member();
        member.setUsername("memberA");

        //when
        Long saveId = memberRepository.save(member);
        Member findMember = memberRepository.findOne(saveId);


        //then
        //Assertions.assertThat(findMember.getId().equals(member.getId()));
        //Assertions.assertThat(findMember.getUsername().equals(member.getUsername()));

       /*
        org.springframework.dao.InvalidDataAccessApiUsageException: No EntityManager with actual transaction available for current thread - cannot reliably process 'persist' call
        이런 오류가 나면 EntityManager에선 Transaction이 꼭 필요하다는 뜻
        @Transactional 어노테이션 추가
        * */

    }
    @Test
    @Transactional
    public void memberDuplicateException(){
        // given
        Member member1 = new Member();
        member1.setUsername("member");
        Member member2 = new Member();
        member2.setUsername("member");

        // when
        memberService.join(member1);

        //then
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    }

}