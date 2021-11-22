package com.info.mvc1.servlet.mvc;

import com.info.mvc1.domain.Member;
import com.info.mvc1.repository.MemberRepository;
import com.info.mvc1.util.ServeltUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

@WebServlet(name="mvcMemberSaveServelt", urlPatterns = "/servlet/mvc/save")
public class MvcMemberSaveServelt extends HttpServlet
{
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
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
