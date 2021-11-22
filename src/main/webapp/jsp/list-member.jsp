<%@ page import="com.info.mvc1.domain.Member" %>
<%@ page import="com.info.mvc1.repository.MemberRepository" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: soleo2102
  Date: 2021-11-22
  Time: 오후 1:31
  To change this template use File | Settings | File Templates.
--%>
<%
    MemberRepository memberRepository = MemberRepository.getInstance();
    List<Member> list = memberRepository.findAll();

%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    회원목록
    <table>
        <% for (Member member : list) {%>
        <tr>
            <td><%=member.getId()%></td>
            <td><%=member.getUsername()%></td>
            <td><%=member.getAge()%></td>
        </tr>
        <% } %>
    </table>

    <a href="/jsp/new-member.jsp"> 등록 </a>
</body>
</html>
