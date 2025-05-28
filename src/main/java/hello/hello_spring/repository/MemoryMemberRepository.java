package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;

import java.util.*;

// 실제로 회원 데이터를 저장/ 조회/ 검색하는 로직을 구현한 구현 클래스
// 데이터를 자바 프로그램 실행 중인 메모리 내부에 저장하는 방식이다.
// DB 없이 간단히 테스트/실습할 때 쓰는 방식
public class MemoryMemberRepository implements MemberRepository {

    // 회원 정보를 저장할 공간 ( DB처럼 사용 )
    // key는 회원 id, value는 Member 객체
    private static Map<Long, Member> store = new HashMap<>();
    // 회원의 고유 id를 순서대로 증가시키기 위한 변수
    // 새 회원 저장시마다 sequence를 1씩 올림.
    private static long sequence = 0L;

    // 시퀀스를 1씩 증가시켜 id로 세팅한다.
    // store에 id와 Member 객체를 저장한다.
    // 저장된 멤버를 반환함
    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    // store에서 id에 해당하는 Member 객체를 꺼내서 반환함
    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    // 모든 Member객체 중 name이 같은 첫 회원을 찾아서 반환함
    // Stream과 Lambda 사용 ( 나중에 배우기 )
    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
