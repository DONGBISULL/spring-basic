package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService { // 테스트 생성 단축키 : 커맨드 + 쉬프트 + T

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {  // Dependency di 의존성 주입
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     *
     * @param member
     * @return
     */
    public Long join(Member member) {
        // 같은 이름이 있는 중복 회원 X
        // 커맨드 옵션 v : 자동 리턴값 세팅
        validateDuplicateMember(member); // 중복회원 검증
        //        result.getEleseGet 많이 사용
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()).ifPresent(m -> { // ifPresent : 값이 있을 경우 로직 실행
            throw new IllegalStateException("이미 존재하는 회원입니다");
        }); // 컨트롤 T : 리팩토링 관련 기능 단축키
    }

    /**
     * 전체 회원 조직 // 서비스는 비즈니스 의존적 설계 repository 는 기계어와 연결하여
     * @return
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public  Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

}
