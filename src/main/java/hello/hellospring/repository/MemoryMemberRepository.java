package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {
    /**
     * HashMap 의 경우 Thread Safe 하지 않으므로 Multi Thread 환경에서 Map 사용 시 ConcurrentHashMap 사용
     */
    private static Map<Long, Member> store = new HashMap<>(); // 보통 공유되는 경우 동시성 문제가 발생하므로 HashMap일 경우 동시성 문제가 발생할 수 있어 ConcurrentHashMap 사용함
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(sequence++);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));// null 반환을 막기위해 optional 을 통해 감싸서 해결
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                    .filter(member -> member.getName().equals(name))
                    .findAny(); // multi thread 에서 Stream 을 처리할 때 먼저 찾은 요소 리턴 (병렬처리시 사용)
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
