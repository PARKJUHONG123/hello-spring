package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * 통합 테스트
 * 스프링 컨테이너까지 올리면서 하는 테스트
 * 
 * @SpringBootTest : 스프링 컨테이너와 테스트를 함께 진행함 (실제로 스프링 컨테이너가 올라감)
 * @Transactional 로 인해서 테스트 시작 전에 트랜잭션을 시작하고, 테스트 완료 후에 항상 rollback
 */

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    // MemberService memberService = new MemberService(memberRepository);
    // MemoryMemberRepository memoryMemberRepository = new MemoryMemberRepository();
    // MemoryMemberRepository 의 store 객체가 static 이기 때문에 지금은 문제가 안되지만
    // memberService 내의 memberRepository 와 위의 memoryMemberRepository 는 서로 다른 객체이기 때문에 위와 같이 사용되면 안됨

    @Autowired MemberRepository memberRepository;
    @Autowired MemberService memberService;

//    @Transactional 으로 인해서 작업 필요 없음
//    @AfterEach
//    void afterEach() {
//        memoryMemberRepository.clearStore();
//    }

    @Test
    // @Commit 을 하게 되면 @Transactional 상관없이 commit 이 되긴 함
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("spring");

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);
//        try {
//            memberService.join(member2);
//            fail();
//        } catch (IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        // then
    }

    @Test
    void findMembers() {

    }

    @Test
    void findOne() {
    }
}