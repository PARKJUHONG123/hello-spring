package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


// 스프링이 @Controller 어노테이션이 있으면, 스프링 컨테이너에 MemberController 객체를 생성해서 관리를 함 (스프링빈으로)

@Controller
public class MemberController {

    /**
     * 생성자에 @AutoWired 를 하면 스프링 컨테이너에 있는 MemberService 객체를 엮어줌
     * 의존 관계를 스프링이 주입함 (Dependency Injection)
     * Controller -> Service -> Repository
     *
     * 컴포넌트 스캔과 자동 의존관계 설정
     * 컴포넌트 스캔 방식 (@Controller, @Service, @Repository 소스를 들어가면 @Component 로 되어 있음)
     * 스프링이 올라올 때, @Component 어노테이션이 있는 객체들을 스프링 컨테이너에 스프링빈으로 등록함
     * 
     * 스프링빈 간의 연관관계를 @Autowired 를 통해서 자동으로 의존관계를 설정함
     */

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) { // 따라서 MemberService 에도 @Service 를 추가해줘야 함
        this.memberService = memberService;
    }

}
