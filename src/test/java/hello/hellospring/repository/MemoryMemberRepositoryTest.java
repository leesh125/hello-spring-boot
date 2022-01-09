package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get(); // 위에서 생성한 member의 id로 회원 검색
        // System.out.println("result = " + (result == member));
        // Assertions.assertEquals(member, null);
        assertThat(member).isEqualTo(result); // 검색한 회원과 새로 생성한 회원(member)이 같은지 판별
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get(); // "spring1"이라는 이름을 가진 회원조회

        assertThat(result).isEqualTo(member1); // member1 회원과 result 회원이 같은지 테스트
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();  // 전체 member를 조회(위에서 2개 생성)

        assertThat(result.size()).isEqualTo(2); // 회원의 총 수가 2와 같은지 테스트

    }
}
