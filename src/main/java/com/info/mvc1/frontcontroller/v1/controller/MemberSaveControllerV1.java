package com.info.mvc1.frontcontroller.v1.controller;

import com.info.mvc1.domain.Member;
import com.info.mvc1.frontcontroller.v1.ControllerV1;
import com.info.mvc1.repository.MemberRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberSaveControllerV1 implements ControllerV1
{
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Business Logic
        String username = request.getParameter("username");
        String age      = request.getParameter("age");

        Member member = new Member(username, Integer.valueOf(age));
        Member saveMember = memberRepository.save(member);

        // Model에 데이터 보관
        request.setAttribute("member", member);

        // 다른 서블릿이나 JSP로 이동할 수 있는 기능 (서버 내부에서 재호출)
        String viewPath = "/WEB-INF/views/save-result.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }
}
