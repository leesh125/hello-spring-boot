package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {
    /*
        필드 주입
        @Autowired private final MemberService memberService;
    */

    private MemberService memberService;
    /*
        // Setter 주입
        // - setter를 사용하면 public으로 해야하기 때문에 service 값의 변경 위험이 있음
        @Autowired
        public void setMemberService(MemberService memberService) {
            this.memberService = memberService;
        }
    */

    // 생성자 주입 (남이 변동을 못하기에 이 방법이 제일 좋음)
    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }
}
