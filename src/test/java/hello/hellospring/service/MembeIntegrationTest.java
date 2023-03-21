package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest // 스프링 부트 테스트
@Transactional // transactional 커밋하지 않으면 반영 안됨=> 테스트 실행 후 롤백
class MembeIntegrationTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    void join() { // 테스트 코드는 한글로 이름 적어도 되긴함
//       given : 기반 데이터
        Member member = new Member();
        member.setName("spring");
//        when : 무엇을 검증하는지
        Long saveId = memberService.join(member);
//        then : 검증부 확인

        Member findMember = memberService.findOne(saveId).get();
        assertThat(findMember.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        // 트라이 캐치를 굳이 추가하지 않기 위한 처리!!
        assertThrows(IllegalStateException.class, () -> memberService.join(member2)); // 로직 실행ㅅ 시 생길 예외 설정
        //then
    }


}