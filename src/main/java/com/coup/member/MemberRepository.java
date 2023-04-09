package com.coup.member;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class MemberRepository {

    @PersistenceContext
    // spring boot 가 Entity Manager 주입 해줌
    // implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    // build.gradle 에 이거 등록할 때 em 생성하는게 자동으로 들어감. 설정파일 읽어서 코드 생성 다 해줌
    private EntityManager em;

    public Long save(Member member) {
        em.persist(member);

        return member.getId();
    }

    public Member find(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findByUserName(String userName) {
        return em.createQuery("select m from Member m where m.username = :name", Member.class)
                .setParameter("name", userName)
                .getResultList();
    }

}
