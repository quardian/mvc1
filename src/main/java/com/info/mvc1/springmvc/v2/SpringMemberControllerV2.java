package com.info.mvc1.springmvc.v2;

import com.info.mvc1.domain.Member;
import com.info.mvc1.repository.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * Controller Method로 통합
 *
 * 컨트롤러 레벨에 @RequestMapping을 하게 되면, Method 레벨의 @RequestMapping과 조합되어 URL을 결정한다.
 */
@Controller
@RequestMapping("/springmvc/v2/members")
public class SpringMemberControllerV2 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @RequestMapping("/new-form")
    public ModelAndView newForm()
    {
        return new ModelAndView("new-form");
    }

    @RequestMapping("/save")
    public ModelAndView save(@RequestParam Map<String, String> paramMap)
    {
        ModelAndView modelAndView = new ModelAndView("save-result");
        String username = paramMap.get("username");
        String age      = paramMap.get("age");

        Member member = new Member(username, Integer.valueOf(age));
        Member saveMember = memberRepository.save(member);

        // Model에 데이터 보관
        modelAndView.addObject("member", member);

        return modelAndView;
    }

    @RequestMapping("/list")
    public ModelAndView list(Map<String, String> paramMap)
    {
        ModelAndView modelAndView = new ModelAndView("list-member");
        List<Member> members = memberRepository.findAll();

        // Model에 데이터 보관
        modelAndView.addObject("members", members);

        return modelAndView;
    }
}
