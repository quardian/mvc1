package com.info.mvc1.servlet.web;

import com.info.mvc1.repository.MemberRepository;
import com.info.mvc1.util.ServeltUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="memberFormServlet", urlPatterns="/servlet/web/member-form")
public class MemberFormServlet extends HttpServlet
{
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        StringBuilder sbHTML = new StringBuilder();

        sbHTML.append("<form action=\"/servlet/web/member-save\" method=\"post\" />\n");
        sbHTML.append(" username: <input type=\"text\" name=\"username\" />\n");
        sbHTML.append(" age: <input type=\"text\" name=\"age\" />\n");
        sbHTML.append(" <button type=\"submit\">전송</button>\n");
        sbHTML.append("</form>\n");

        sbHTML.append( "<a href=\"/servlet/web/member-list\">목록</a>" );

        ServeltUtils.writeHtml( response, "회원가입", sbHTML.toString() );
    }
}
