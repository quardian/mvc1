<%@ page import="com.info.mvc1.domain.Member" %>
<%@ page import="com.info.mvc1.repository.MemberRepository" %><%--
  Created by IntelliJ IDEA.
  User: soleo2102
  Date: 2021-11-22
  Time: 오후 1:31
  To change this template use File | Settings | File Templates.
--%>
<%
    MemberRepository memberRepository = MemberRepository.getInstance();

    String username = request.getParameter("username");
    String age      = request.getParameter("age");

    Member member = new Member(username, Integer.valueOf(age));
    Member saveMember = memberRepository.save(member);

%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    성공
    <ul>
        <li>username: <%=saveMember.getUsername()%></li>
        <li>age: <%=saveMember.getAge()%></li>
    </ul>

    <a href="/index.html"> 메인 </a>
</body>
</html>
