package hello.hellospring;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 1. XML 로 설정할 수도 있는데, 요즘에는 잘 사용 X
 * 2. DI 에는 필드 주입 / Setter 주입 / 생성자 주입 3가지 방법이 있음 -> 의존관계가 실행중에 동적으로 변경하는 경우가 거의 없으므로 생성자 주입 권고
 * 
 * 3. 정형화되지 않거나, 상황에 따라서 구현 클래스를 변경해야 하면 설정을 통해 스프링 빈으로 등록함
 * EX. MemoryMemberRepository 를 나중에 실제 DB 로 바꿔치기 할 때, 기존 다른 코드를 일절 변경하지 않고 바꿀 수 있게 함
 *
 *
 * 4. 스프링빈으로 등록되지 않은 객체 (= 스프링이 관리하지 않는 객체) 는 @Autowired 안 먹음
 * EX. @Bean 으로 등록하거나 @Service, @Controller, @Repository 등으로 등록
 *
 *
 */


@Configuration
public class SpringConfig {

    // 스프링이 applications.properties 을 통해서 DataSource 를 DI
//    private final DataSource dataSource;
//
//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

    // JPA 는 EntityManager 로 동작을 함
    // 스프링 부트가 EntityManager 를 생성해줘서 우리는 Injection 받아서 사용함
    // JPA 가 관리하는 EntityManager 를 Autowire

//    private final EntityManager em;
//    @Autowired
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }


    /**
     * SpringDataJpaMemberRepository 인터페이스에서
     * 스프링 데이터 JPA 가 JpaRepository 를 받고 있으면
     * 구현체를 자동으로 만들어서 스프링빈으로 자동으로 등록함
     * 
     * SpringDataJpaMemberRepository 인터페이스에서 생성한
     * 구현체를 주입받아서 사용
     */
    
    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
//        return new MemberService(memberRepository());
    }

//    @Bean
//    public MemberRepository memberRepository() {
//        // return new JpaMemberRepository(em);
//
//        // return new JdbcTemplateMemberRepository(dataSource);
//        // return new JdbcMemberRepository(dataSource);
//        // return new MemoryMemberRepository();
//        // return new DbMemberRepository(); // 와 같이 기존 다른 코드를 일절 변경하지 않고 바꿀 수 있게 함
//    }

    
    // package hello.hellospring.aop 의 TimeTraceAop 에 @Component 를 써서 스프링빈으로 생성해도 되지만
    // SpringConfig 에 작성하는 것이 어떤 객체가 스프링빈으로 잡힐 건지 확인하기 좋음 
    @Bean
    public TimeTraceAop timeTraceAop() {
        return new TimeTraceAop();
    }

}
