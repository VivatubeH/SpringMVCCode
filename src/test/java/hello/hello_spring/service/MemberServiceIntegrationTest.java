package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

// 해당 어노테이션을 붙이면 스프링 컨테이너와 테스트를 함께 실행한다.
@SpringBootTest
// Transactional을 붙이면 테스트 쿼리가 실행된 뒤 자동 롤백된다.
// DB에 데이터가 남지 않고 다른 테스트에 영향을 주지 않는다.
@Transactional
class MemberServiceIntegrationTest {

    // 다른 데서 가져다 쓸 게 아닌 테스트 케이스는
    // 그냥 이렇게 바로 주입하는 것도 좋음.
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    // 테스트 코드는 외국 사람이랑 작업할 게 아니면
    // 그냥 한글로 적자.
    // 그리고 테스트 케이스 처음 작성할 때는
    // 주석으로 given ~ when ~ then 구조 나누는 게 도움많이 됨.
    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("spring100");

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    // 항상 테스트는 예외 케이스에 대한 검증이 더 중요.
    @Test
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
/*        try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
            // 에러 메시지가 기존에 설정한 에러메시지와 같아야 함
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
*/

        // then
    }
}