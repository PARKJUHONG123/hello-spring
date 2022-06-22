package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository memoryMemberRepository = new MemoryMemberRepository();

    // 메소드 테스트가 끝날 때마다 실행하는 호출문
    @AfterEach
    public void afterEach() {
        memoryMemberRepository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        memoryMemberRepository.save(member);

        Member result = memoryMemberRepository.findById(member.getId()).get(); // get() : optional 을 까서 꺼낼 수 있음
//        System.out.println("result = " + (result == member));
        assertEquals(member, result);
        assertThat(member).isEqualTo(result); // alt + Enter 를 누르면 import static 으로 뺄 수 있음
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        memoryMemberRepository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        memoryMemberRepository.save(member2);

        Member result = memoryMemberRepository.findByName("spring1").get();
        // 테스트를 클래스 단위로 할 때, 안에 있는 메소드 테스트 순서는 보장할 수 없기 때문에 위의 result 객체와 member1 객체가 동일하지 않게 find 되는 경우가 발생할 수 있음
        // 따라서 각 메소드 별로 테스트가 끝났을 때는 데이터를 지워줘야 함
        
        assertThat(result).isEqualTo(member1);

    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        memoryMemberRepository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        memoryMemberRepository.save(member2);

        List<Member> result = memoryMemberRepository.findAll();

        assertThat(result.size()).isEqualTo(2);

    }
}
