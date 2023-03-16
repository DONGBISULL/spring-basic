package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
/**
 *  스프링 빈 등록 하는 방법
 *  1. 컴포넌트 스캔과 자동 의존관계 설정 => 어노테이션(@Component)을 통해 자동으로 매핑 (@Service, @Controller, @Repository도 @Component를 포함함 )
 *      일반적으로 정형화된 컨트롤러 , 서비스, 리포지토리 => 컴포넌트 스캔 사용
 *      장점 : 생성자를 통해 주입
 *  2. 자바 코드로 직접 스프링 빈 등록
 *      정형화되지 않은 설정의 경우 스프링 빈으로 등록
 *
 *
 *  아무 곳에나 package 를 생성하고 @Component 를 붙여도 될까?
 *  => SpringBootApplication의 패키지를 기준으로 하위 패키지까지 검색=> 기본적으로는 해당 패키지를 기준으로 빈 등록한다!
 *  => 싱글톤 패턴으로 등록!
 *  */
@Controller // spring 컨테이너에 memberContainer 만들어서 관리하게 됨
public class MemberController {

    private final MemberService memberService; //new 로 받을 경우 객체를 여러개 생성하게 됨 => 싱글톤 패턴으로!

    @Autowired// 스프링이 자동으로 의존성을 주입시켜줌 DI(Dependency Injection)
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


}
