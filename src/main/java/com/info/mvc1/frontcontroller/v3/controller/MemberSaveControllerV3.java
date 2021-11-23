package com.info.mvc1.frontcontroller.v3.controller;

import com.info.mvc1.domain.Member;
import com.info.mvc1.domain.ModelView;
import com.info.mvc1.frontcontroller.v3.ControllerV3;
import com.info.mvc1.repository.MemberRepository;

import java.util.Map;

public class MemberSaveControllerV3 implements ControllerV3
{
    private MemberRepository memberRepository = MemberRepository.getInstance();


    @Override
    public ModelView process(Map<String, String> paramMap) {
        String username = paramMap.get("username");
        String age      = paramMap.get("age");

        Member member       = new Member(username, Integer.valueOf(age));
        Member saveMember   = memberRepository.save(member);

        ModelView modelView = new ModelView("save-result");
        modelView.getModel().put("member", member);

        return modelView;
    }
}
