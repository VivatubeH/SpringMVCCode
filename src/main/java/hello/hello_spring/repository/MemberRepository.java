package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;

import java.util.List;
import java.util.Optional;

// 회원 저장소(Repository)의 설계도가 역할을 하는 인터페이스
// 회원 데이터를 어떻게 저장/조회/검색할 지 기본 틀 (메서드 정의)만 있음
// 구현 내용은 없다.

public interface MemberRepository {
    // Member 객체를 저장하고, 저장된 Member 객체 반환
    Member save(Member member);
    // id로 회원을 찾아서 Optional<Member> 타입으로 반환
    // 없으면 빈 Optional을 반환한다.
    Optional<Member> findById(Long id);
    // 이름으로 회원을 찾아서, Optional<Member>를 반환함
    Optional<Member> findByName(String name);
    // 저장된 모든 회원 객체를 리스트로 반환한다.
    List<Member> findAll();
}
