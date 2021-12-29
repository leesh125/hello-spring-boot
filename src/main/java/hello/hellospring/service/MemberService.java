package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional // jpa는 모든 데이터 변경이 transaction 안에서 실행되어야함
public class MemberService {

    // new를 사용해 새로운 memberRepo를 생성하지 않고
    // 밑에 생성자를 이용해서 할당받음 그 이유는
    // serviceTest와 같은 repo를 공유하기 위해서
    private final MemberRepository memberRepository;


    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*
            회원 가입
         */
    public Long join(Member member){

        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
        /* 메소드 실행시간 측정 로직 (AOP 없이)
        long start = System.currentTimeMillis();

        try{
            validateDuplicateMember(member); // 중복 회원 검증
            memberRepository.save(member);
            return member.getId();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("join = " + timeMs + "ms");
        }*/
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent((m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                }));
    }

    /*
        전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
