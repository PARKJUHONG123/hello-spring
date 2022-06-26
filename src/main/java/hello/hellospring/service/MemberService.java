package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * ctrl + shift + T : 테스트 자동으로 만들어줌
 *
 * AOP
 * Aspect Oriented Programming
 * 공통 관심 사항 (cross-cutting concern) vs 핵심 관심 사항 (core concern) 분리
 * - 공통 관심 사항 : 시간 측정 로직
 * - 핵심 관심 사항 : 비즈니스 로직 (로그인, 회원 조회 등)
 * 
 * 시간 측정 로직을 한 군데에 다 모아놓고, 시간 측정 로직이 필요한 핵심 관심 사항에 각각 적용
 */


// 스프링이 올라올 때, 스프링이 서비스네 하고 스프링 컨테이너에 MemberService 스프링빈 등록
//@Service

// JPA 를 통해 데이터를 저장하고 변경하기 위해서는 @Transactional 어노테이션을 통해 명시를 해줘야 함
@Transactional
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

        long start = System.currentTimeMillis();

        try {
            validateDuplicateMember(member); // 중복 회원 검증
            memberRepository.save(member);
            return member.getId();
        }
        finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("join = " + timeMs + "ms");
        }

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
