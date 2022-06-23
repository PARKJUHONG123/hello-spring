package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * ctrl + shift + T : 테스트 자동으로 만들어줌
 */


// 스프링이 올라올 때, 스프링이 서비스네 하고 스프링 컨테이너에 MemberService 스프링빈 등록
//@Service
public class MemberService {
    private final MemberRepository memberRepository;

    /**
     * 생성자에 @AutoWired 를 하면 스프링 컨테이너에 있는 MemberRepository 객체를 엮어줌
     * 의존 관계를 스프링이 주입함 (Dependency Injection)
     * Controller -> Service -> Repository
     */
//    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    /**
     * 회원 가입
     */

    public Long join(Member member) {
        // 요건 : 같은 이름이 있는 중복 회원은 안된다 (ctrl + alt + v 누르면 변수가 자동 생성됨)
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(member1 -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * 특정 회원 조회
     */

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
