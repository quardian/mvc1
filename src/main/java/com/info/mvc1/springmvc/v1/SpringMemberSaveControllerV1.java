package com.info.mvc1.springmvc.v1;

import com.info.mvc1.domain.Member;
import com.info.mvc1.repository.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class SpringMemberSaveControllerV1 {

    private MemberRepository memberRepository = MemberRepository.getInstance();


    @RequestMapping("/springmvc/v1/members/save")
    public ModelAndView process(@RequestParam  Map<String, String> paramMap)
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
}
