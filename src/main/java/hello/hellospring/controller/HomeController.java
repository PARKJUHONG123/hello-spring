package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 웹 브라우저에서 localhost:8080 으로 접속했을 때
 * 1. 스프링 부트 내장 톰캣 서버가 스프링 컨테이너의 컨트롤러 스프링빈들을 먼저 탐색 (없으면 static page 를 찾음)
 * 2. / 경로로 되어 있는 HomeController 가 있으니까 home.html 로 이동
 */

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home";
    }
}
