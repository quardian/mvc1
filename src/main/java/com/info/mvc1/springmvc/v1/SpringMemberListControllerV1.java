package com.info.mvc1.springmvc.v1;

import com.info.mvc1.domain.Member;
import com.info.mvc1.repository.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class SpringMemberListControllerV1 {

    private MemberRepository memberRepository = MemberRepository.getInstance();


    @RequestMapping("/springmvc/v1/members/list")
    public ModelAndView process(Map<String, String> paramMap)
    {
        ModelAndView modelAndView = new ModelAndView("list-member");
        List<Member> members = memberRepository.findAll();

        // Model에 데이터 보관
        modelAndView.addObject("members", members);

        return modelAndView;
    }
}
