package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    // 클리어 데이터를 하기 위해 repository 추가
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public  void beaforeEach () {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @Test
    void join() { // 테스트 코드는 한글로 이름 적어도 되긴함
//       given : 기반 데이터
        Member member = new Member();
        member.setName("hello");
//        when : 무엇을 검증하는지
        Long saveId = memberService.join(member);
//        then : 검증부 확인

        Member findMember = memberService.findOne(saveId).get();
        assertThat(findMember.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        // 트라이 캐치를 굳이 추가하지 않기 위한 처리!!
        assertThrows(IllegalStateException.class, () -> memberService.join(member2)); // 로직 실행ㅅ 시 생길 예외 설정

//        try {
//            memberService.join(member2);
//            fail();
//        } catch (IllegalStateException e){
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
//        }
        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}