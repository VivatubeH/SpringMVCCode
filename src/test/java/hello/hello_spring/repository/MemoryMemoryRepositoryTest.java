package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

// 테스트 클래스는 클래스명 뒤에 Test를 쓰는 것이 관례
public class MemoryMemoryRepositoryTest {

    // 테스트마다 새로운 MemoryMemberRepository 객체를 만든다. ( 저장소 )
    MemoryMemberRepository repository = new MemoryMemberRepository();

    // @AfterEach -> 각 테스트 메서드 실행 후 자동으로 해당 메서드가 실행된다.
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    // @Test -> 테스트를 위한 어노테이션
    // 각각 독립적으로 실행됨
    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        // assertThat(실제값).isEqualTo(예상값)
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring2").get();

        assertThat(result).isEqualTo(member2);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }


}
