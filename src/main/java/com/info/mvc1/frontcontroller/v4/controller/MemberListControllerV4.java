package com.info.mvc1.frontcontroller.v4.controller;

import com.info.mvc1.domain.Member;
import com.info.mvc1.frontcontroller.v4.ControllerV4;
import com.info.mvc1.repository.MemberRepository;

import java.util.List;
import java.util.Map;

public class MemberListControllerV4 implements ControllerV4
{
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {

        List<Member> members = memberRepository.findAll();
        model.put("members", members);

        return "list-member";
    }
}
