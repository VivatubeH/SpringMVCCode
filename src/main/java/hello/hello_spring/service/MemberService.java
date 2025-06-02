package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

// command + shift + T를 누르면 쉽게 테스트 클래스를 만들 수 있습니다.
// 테스트 라이브러리는 jUnit5로 선택하고 필요한 메서드를 선택해서 생성하면
// 껍데기를 제공해줍니다.
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member) {
        // 같은 이름이 있는 중복 회원 x
        // 이미 이름이 있는 회원이면 이미 존재하는 회원이라는 예외 객체 생성
        // ctrl + T를 누르면 메서드 추출이 가능함
        // 로직이 쭉 나오는 경우라면 -> 메서드를 추출하는 게 권장됨
        // 단축키로는 전체 코드 드래그 한 뒤 command + option + M을 누르면 생성
        validateDuplicateMember(member);
        // 존재하는 회원이 아닐 때는
        // 회원 저장소에 저장한다.
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
           throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    //  서비스 클래스의 메서드는 기획자에게 공유될 수도 있으니
    // 보통은 더 직관적인 이름을 택한다.

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
