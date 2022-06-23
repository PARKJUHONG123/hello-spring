package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
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

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
        // return new DbMemberRepository(); // 와 같이 기존 다른 코드를 일절 변경하지 않고 바꿀 수 있게 함
    }

}
