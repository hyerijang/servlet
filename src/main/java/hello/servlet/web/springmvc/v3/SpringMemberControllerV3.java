package hello.servlet.web.springmvc.v3;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


// NOTE : 변경점
//  - 컨트롤러 리턴타입 : String (viewName)
//  - 컨트롤러 파라미터 : @RequestParam, Model
//  - @GetMapping, @PostMapping

@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @GetMapping("/new-form")
    public String newForm() {
        // NOTE: 컨트롤러의 리턴갑
        //   1. ModelAndView 를 반환
        //   2. String 을 반환 (viewName)
        return "new-form";
    }

    // NOTE : @RequestParam 으로 파라미터 가져올 수 있다.
    // NOTE : Model 으로 모델 가져올 수 있다.
    @PostMapping("/save")
    public String save(
            @RequestParam("username") String username,
            @RequestParam("age") int age,
            Model model) {

        Member member = new Member(username, age);
        memberRepository.save(member);

        model.addAttribute("member", member);
        return "save-result";

    }

    @GetMapping
    public String list(Model model) {
        List<Member> members = memberRepository.findAll();
        model.addAttribute("members", members);
        return "members";
    }

}
