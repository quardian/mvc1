package com.info.mvc1.frontcontroller.v3.controller;

import com.info.mvc1.domain.Member;
import com.info.mvc1.domain.ModelView;
import com.info.mvc1.frontcontroller.v3.ControllerV3;
import com.info.mvc1.repository.MemberRepository;

import java.util.List;
import java.util.Map;

public class MemberListControllerV3 implements ControllerV3
{
    private MemberRepository memberRepository = MemberRepository.getInstance();


    @Override
    public ModelView process(Map<String, String> paramMap) {

        ModelView modelView = new ModelView("list-member");

        List<Member> members = memberRepository.findAll();
        modelView.getModel().put("members", members);

        return modelView;
    }
}
