package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class MemoryMemberRepositoryTest {

    MemoryMemberRepository  repository = new MemoryMemberRepository();
    // 테스트 실행 한 후 데이터를 클린 시키는 코드가 필요함(현재 Mock 데이터를 사용하기 때문에)
    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");
        repository.save(member);

        Member result = repository.findById(member.getId()).get();
//        Assertions.assertEquals(member , result);
          assertThat(member).isEqualTo(result); // 옵션 엔터 치면 => 자동으로 static 화 됨
    }
    @Test
    public void  findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }
    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring1");
        repository.save(member2);

        List<Member> memberList =  repository.findAll();
        assertThat(memberList.size()).isEqualTo(2);
    }
}
