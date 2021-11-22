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
import java.util.List;

@WebServlet(name="memberListServlet", urlPatterns="/servlet/web/member-list")
public class MemberListServlet extends HttpServlet
{
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String fmtTd  = "<td>%s=%s<td/>";
        StringBuilder sbHTML = new StringBuilder();

        List<Member> memberList = memberRepository.findAll();

        sbHTML.append("<table border=1>");
        for (Member member : memberList) {
            sbHTML.append("<tr>");
            sbHTML.append( String.format(fmtTd, "id", member.getId()) );
            sbHTML.append( String.format(fmtTd, "username", member.getUsername()) );
            sbHTML.append( String.format(fmtTd, "age", member.getAge()) );
            sbHTML.append("</tr>");
        }
        sbHTML.append("</table>");

        sbHTML.append( "<a href=\"/servlet/web/member-form\">등록</a>" );
        
        ServeltUtils.writeHtml( response, "회원목록", sbHTML.toString() );

    }
}
