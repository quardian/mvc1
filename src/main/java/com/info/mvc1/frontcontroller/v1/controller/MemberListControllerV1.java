package com.info.mvc1.frontcontroller.v1.controller;

import com.info.mvc1.domain.Member;
import com.info.mvc1.frontcontroller.v1.ControllerV1;
import com.info.mvc1.repository.MemberRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MemberListControllerV1 implements ControllerV1
{
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Member> members = memberRepository.findAll();

        // Model에 데이터 보관
        request.setAttribute("members", members);

        // 다른 서블릿이나 JSP로 이동할 수 있는 기능 (서버 내부에서 재호출)
        String viewPath = "/WEB-INF/views/list-member.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }
}
