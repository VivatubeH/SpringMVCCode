package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

// 이 클래스를 "웹 요청을 받아서 처리하는 컨트롤러"로 등록합니다.
// 이렇게 하면 스프링이 알아서 이 클래스를 찾아서 웹 요청을 연결해줍니다.
@Controller
public class HelloController {

    // /hello라는 주소로 GET 방식이 들어오면 해당 메서드가 실행됩니다.
    @GetMapping("hello")
    // Model은 뷰(html 페이지)에 데이터를 실어 보낼 때 사용하는 바구니 역할입니다.
    public String hello(Model model) {
        // model의 addAttribute 메서드로 "data"라는 이름으로 "spring!!"이라는 값을 담습니다.
        // key, value 형태로 값을 담습니다.
        model.addAttribute("data", "spring!!");
        // 스프링의 기본 설정에 의해서
        // resources/templates/ + 파일명 + .html을 찾아서 사용자에게 보여줍니다.
        return "hello";
    }

    @GetMapping("hello-mvc")
    // URL에서 ?name=xxx처럼 전달된 파라미터(변수)를 받아옵니다.
    public String helloMvc(@RequestParam(value = "name") String name, Model model) {
        // 받은 name값을 name이라는 이름으로 뷰에 넘깁니다.
        model.addAttribute("name", name);
        // hello-template.html 파일을 찾아서 브라우저에 보여줍니다.
        return "hello-template";
    }

    @GetMapping("hello-string")
    // 리턴값을 그냥 "그대로" 브라우저에 돌려줍니다.
    // 즉, HTML 렌더링이 아닙니다.
    @ResponseBody
    // url에서 name값을 받아와서 name 변수에 저장합니다.
    public String helloString(@RequestParam("name") String name) {
        // html 페이지가 아닌 hello 뒤에 문자열을 담아서 브라우저에 보냅니다.
        return "hello " + name;
    }

    @GetMapping("hello-api")
    // 리턴값을 JSON(데이터) 형태로 브라우저에 바로 전달합니다.
    // 객체일 때는 기본적으로 JSON으로 반환하게 되어 있습니다.
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        // Hello 객체를 생성해서, name 값을 넣어줍니다.
        Hello hello = new Hello();
        hello.setName(name);
        // Hello 객체를 반환합니다.
        // 이 때 Hello 객체는 자동으로 JSON 형태로 변환 후 응답됩니다.
        return hello;
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
