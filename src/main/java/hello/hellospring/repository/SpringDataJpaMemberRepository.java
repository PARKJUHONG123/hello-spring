package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * 우선 인터페이스 간의 상속은 extends 로 가능 (여러 개도 가능)
 *
 * 스프링 데이터 JPA 가 JpaRepository 를 받고 있으면
 * 구현체를 자동으로 만들어서 스프링빈으로 자동으로 등록함
 * 
 * 프록시라는 기술을 통해서 객체를 생성함
 * JpaRepository 를 들어가서 보면 기본적인 CRUD 관련 함수들이 제공되어 있음
 */

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {


    /**
     * 스프링 데이터 JPA 가 메소드 명 규칙에 따라서 기본 제공 CRUD 외 CRUD 를 생성해서 제공함
     * JPQL select m from Member m where m.name = ?
     *
     * findByNameAndId(String name, Long Id) 로 메소드명을 정하면
     * JPQL select m from Member m where m.name = ? and m.id = ?
     * 로 자동으로 쿼리를 만듬
     */

    @Override
    Optional<Member> findByName(String name);
}
