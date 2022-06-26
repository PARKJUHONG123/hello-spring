package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

    // JPA 는 EntityManager 로 동작을 함
    // 스프링 부트가 EntityManager 를 생성해줘서 우리는 Injection 받아서 사용함
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        // persist = 영구저장 : JPA 가 member 파라미터 값을 받아서 알아서 setId 부터 해서 insert query 생성해서 처리함
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // find = SELECT (PK)
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        // PK 기반의 조회가 아닐 경우에는 아래와 같이 createQuery 를 통해 조회
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name) // 파라미터 값을 매핑하고
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        // 테이블이 아니라 객체를 상대로 쿼리를 날림
        // Member 클래스에 테이블이랑 매핑이 되어 있기 때문에 가능
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
