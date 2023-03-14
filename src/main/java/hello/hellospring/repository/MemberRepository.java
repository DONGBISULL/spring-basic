package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;
/**
 *
 */

public interface MemberRepository {
    // 인터페이스에서는 제어자 생략하고 구현
    Member save(Member member);
    Optional<Member> findById(Long id); // null 처리 대신 Optional 로 반환할 수 있도록 함
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
