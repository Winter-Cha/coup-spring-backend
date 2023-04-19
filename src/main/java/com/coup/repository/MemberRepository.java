package com.coup.repository;

import com.coup.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class MemberRepository {

    //@PersistenceContext
    // spring boot 가 Entity Manager 주입 해줌
    // implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    // build.gradle 에 이거 등록할 때 em 생성하는게 자동으로 들어감. 설정파일 읽어서 코드 생성 다 해줌
    //private EntityManager em;

    // Repository 도 생성자 injection으로 바꿔줄 수 있음. 스프링부트에서 @Autowired 를 지원하기 때문.
    private final EntityManager em;

    public Long save(Member member) {
        em.persist(member); // insert 문이 안나감. commit 될 때 flush가 되면서 insert문이 나가기 떄문

        return member.getId();
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByUserName(String userName) {
        return em.createQuery("select m from Member m where m.username = :name", Member.class)
                .setParameter("name", userName)
                .getResultList();
    }

}
