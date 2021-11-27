package com.info.mvc1.springmvc.v3;

import com.info.mvc1.domain.Member;
import com.info.mvc1.repository.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *  ModelAndView를 개발자가 직접 생성해서 반환했던 불편함을 개선
 * @RequestMapping : GET, POST 모두 수략
 * @GetMapping : GET 요청만 수략
 * = @RequestMapping(value="{path}", method = RequestMethod.GET)
 * @PostMapping : POST 요청만 수략
 * = @RequestMapping(value="{path}", method = RequestMethod.POST)
 */

@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    //@RequestMapping(value="/new-form", method= RequestMethod.GET)
    @GetMapping("/new-form")
    public String newForm(Model model) {
        return "new-form";
    }

    //@RequestMapping(value="/save", method= RequestMethod.POST)
    @PostMapping("/save")
    public String save(Model model,
                       @RequestParam String username,
                       @RequestParam Integer age)
    {
        Member member = new Member(username, age);
        Member saveMember = memberRepository.save(member);

        // Model에 데이터 보관
        model.addAttribute("member", member);
        return "save-result";
    }

    //@RequestMapping(value="/list", method= RequestMethod.GET)
    @GetMapping("/list")
    public String  list(Model model)
    {
        List<Member> members = memberRepository.findAll();
        // Model에 데이터 보관
        model.addAttribute("members", members);

        return "list-member";
    }
}
