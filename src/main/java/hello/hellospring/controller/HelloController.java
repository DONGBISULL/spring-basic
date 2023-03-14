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
        model.addAttribute("data", "hello!!");
        return "hello";
    }
    //커맨드 p 옵션 => 파라미터 옵션 확인 가능
    @GetMapping("hello-mvc")
    public String helloMㅁvc(@RequestParam("name") String name , Model model ) {
        model.addAttribute("name" , name);
        return "hello-mvc";
    }

    @GetMapping("hello-spring")
    @ResponseBody //http body에 해당 데이터를 직접 넣어주겠다!!
    public String helloSpring(@RequestParam("name") String name){
        return "hello " + name;
    }
    @GetMapping("hello-api")
    @ResponseBody //http body에 해당 데이터를 직접 넣어주겠다!!
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello(); // 커맨드 쉬프트 엔터 => 자동 ; 닫기
        hello.setName(name);
        return hello;
    }

    public class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


}
