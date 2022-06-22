package hello.hellospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 여기서부터 Component-Scan 의 시작
 * 이 패키지 하위의 COMPONENT 만 스프링빈으로 스프링 컨테이너에 등록함
 * @SpringBootApplication 에 보면 @ComponentScan 이 명시되어 있어서
 * 수정하면 이 hello.hellospring 밖의 Component 도 스프링빈으로 등록할 수 있긴 함
 *
 * 스프링빈 = 싱글톤 방식
 * 유일하게 하나만 등록함
 * MemberController 하나만, MemberService 하나만, MemberRepository 하나만 스프링 컨테이너에 스프링빈으로 등록해서 공유함 (메모리 절약)
 * 설정으로 싱글톤이 아니게 설정할 수 있지만, 대부분의 경우에는 싱글톤 사용함
 *
 * 예를 들어서 @Service 로 명시되어 있는 OrderService 에서 MemberRepository 를 @Autowired 해서 가져올 때
 * 별개의 MemberRepository 스프링빈 객체를 생성하는 것이 아닌 MemberService 와 OrderService 가 같은 하나의 MemberRepository 객체를 사용함
 */
@SpringBootApplication
public class HelloSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloSpringApplication.class, args);
	}

}
