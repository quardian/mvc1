package com.info.mvc1.frontcontroller.v2.controller;

import com.info.mvc1.domain.Member;
import com.info.mvc1.frontcontroller.domain.MyView;
import com.info.mvc1.frontcontroller.v2.ControllerV2;
import com.info.mvc1.repository.MemberRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MemberListControllerV2 implements ControllerV2 {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Member> members = memberRepository.findAll();
        // Model에 데이터 보관
        request.setAttribute("members", members);

        return new MyView("/WEB-INF/views/list-member.jsp");
    }
}
