package com.coup.service;

import com.coup.domain.Member;
import com.coup.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)    // JUnit 실행할 때 스프링이랑 같이 실행한다.
@SpringBootTest                 // SpringBoot 띄운상태로 테스트. 이거 없으면 @Autowired 걸린게 전부 fail 됨
@Transactional                  // Test 끝나면 Rollback 시킨다.
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    //@Autowired EntityManager em; // -> em.flush() 하면 insert 쿼리 날릴 수 있음.

    @Test
    @Rollback(value = false) // @Transactional 때문에 insert문이 안나타날 때 사용하면 insert문 보임
    public void 회원가입() throws Exception {
        //given
        Member member = new Member();
        member.setUsername("kim");

        //when
        Long saveId = memberService.join(member);

        //em.flush();
        //then
        assertEquals(member, memberRepository.findOne(saveId));

    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception {
        //given
        Member member1 = new Member();
        member1.setUsername("kim1");

        Member member2 = new Member();
        member2.setUsername("kim1");

        //when
        memberService.join(member1);
        //try { // --> expected 설정으로 바꿔주면 코드가 깔끔해진다.
            memberService.join(member2); // 예외발생해야한다.
        //} catch (IllegalStateException e) {
        //    return;
        //}

        //then
        fail("예외발생해야한다."); // 여기까지 문제없이 오면 안되기 때문에 추가
    }


}