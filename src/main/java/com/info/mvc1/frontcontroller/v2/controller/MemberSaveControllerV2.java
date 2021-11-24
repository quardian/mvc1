package com.info.mvc1.frontcontroller.v2.controller;

import com.info.mvc1.domain.Member;
import com.info.mvc1.domain.MyView;
import com.info.mvc1.frontcontroller.v2.ControllerV2;
import com.info.mvc1.repository.MemberRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberSaveControllerV2 implements ControllerV2 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

// Business Logic
        String username = request.getParameter("username");
        String age      = request.getParameter("age");

        Member member = new Member(username, Integer.valueOf(age));
        Member saveMember = memberRepository.save(member);

        // Model에 데이터 보관
        request.setAttribute("member", member);


        return new MyView("/WEB-INF/views/save-result.jsp");
    }
}
