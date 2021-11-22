package com.info.mvc1.servlet.web;

import com.info.mvc1.domain.Member;
import com.info.mvc1.repository.MemberRepository;
import com.info.mvc1.util.ServeltUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="memberSaveServlet", urlPatterns="/servlet/web/member-save")
public class MemberSaveServlet extends HttpServlet
{
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String age      = request.getParameter("age");

        Member member = new Member(username, Integer.valueOf(age));
        Member saveMember = memberRepository.save(member);

        String fmtTag  = "<p>%s=%s<p/>";
        StringBuilder sbHTML = new StringBuilder();
        
        sbHTML.append( String.format(fmtTag, "id", saveMember.getId()) );
        sbHTML.append( String.format(fmtTag, "username", saveMember.getUsername()) );
        sbHTML.append( String.format(fmtTag, "age", saveMember.getAge()) );

        sbHTML.append( "<a href=\"/servlet/web/member-list\">목록</a>" );
        
        ServeltUtils.writeHtml( response, "회원가입 성공", sbHTML.toString() );

    }
}
