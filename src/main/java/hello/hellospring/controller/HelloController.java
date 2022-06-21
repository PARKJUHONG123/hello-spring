package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "spring!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(name = "name", required = true) String name, Model model) {         // ctrl + P : @에 넣을 수 있는 파라미터 알려줌
        model.addAttribute("name", name); // 모델에 name 집어넣고
        return "hello-template"; // resources/templates/hello-template.html 에 넘기려고 함
        // 그러면  viewResolver 가 해당 되는 view 를 찾아주고, html 보니까 Thymeleaf 내용이 있음을 확인함
        // Thymeleaf 탬플릿 엔진에게 처리해주세요 라고 하고 함
        // 탬플릿 엔진이 렌더링해서 변환한 HTML 파일을 웹 브라우저에게 반환함
    }

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam(name = "name") String name) {
        // @ResponseBody : HTTP 통신 바디에 아래의 RETURN 값을 직접 넣어주겠음
        // 뷰가 없고 아래의 RETURN 값이 그대로 전달됨
        return "hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        // command + shift + enter 누르면 자동 완성
        Hello hello = new Hello();
        hello.setName(name);
        return hello; // 객체가 json 방식으로 전달됨
        // key-value 로 이루어진 구조

        // 문자가 아니고 객체일 경우, 스프링 입장에서는 기본적으로 JSON 방식으로 데이터를 만들어서 HTTP 응답에 반환함
        // @ResponseBody 가 있을 경우,
        // HttpMessageConverter 가 Return 값이 String 일 경우, StringConverter
        // HttpMessageConverter 가 Return 값이 객체일 경우, JsonConverter 가 동작 (MappingJackson2HttpMessageConverter)
        // Byte 처리 등등 여러 Converter 가 기본으로 스프링부트 내 등록되어 있음

        // HTTP 요청 메세지가 왔을 때, 클라이언트의 HTTP Accept 헤더가 XML 이거나 HTML 일 경우에는
        // 클라이언트의 HTTP Accept 헤더 + 서버의 컨트롤러 반환 정보 둘을 조합해서 HttpMessageConverter 가 선택됨
    }

    static class Hello {
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        private String name; // alt + insert
    }
}
