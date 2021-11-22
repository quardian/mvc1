<%@ page import="com.info.mvc1.domain.Member" %>
<%@ page import="com.info.mvc1.repository.MemberRepository" %><%--
  Created by IntelliJ IDEA.
  User: soleo2102
  Date: 2021-11-22
  Time: 오후 1:31
  To change this template use File | Settings | File Templates.
--%>
<%

    Member member = (Member)request.getAttribute("member");


%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    성공
    <ul>
        <li>username: <%=member.getUsername()%></li>
        <li>age: <%=member.getAge()%></li>
    </ul>
    <ul>
        <li>username: ${member.username}</li>
        <li>age: ${member.age}</li>
    </ul>
    <a href="/index.html"> 메인 </a>
    <a href="list"> 목록 </a>
</body>
</html>
