package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
                        // post로 넘어온 input 데이터(name)는 매개변수로 입력한 MemberForm에 있는 name에 자동으로 setName이 됨
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
