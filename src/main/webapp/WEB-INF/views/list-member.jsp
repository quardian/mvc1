<%@ page import="com.info.mvc1.domain.Member" %>
<%@ page import="com.info.mvc1.repository.MemberRepository" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: soleo2102
  Date: 2021-11-22
  Time: 오후 1:31
  To change this template use File | Settings | File Templates.
--%>
<%
    List<Member> members= (List<Member>) request.getAttribute("members");
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h3>회원목록1</h3>
    <table>
        <!-- old jsp 문법 -->
        <% for (Member member : members) {%>
        <tr>
            <td><%=member.getId()%></td>
            <td><%=member.getUsername()%></td>
            <td><%=member.getAge()%></td>
        </tr>
        <% } %>
    </table>

    <h3>회원목록2</h3>
    <table>
        <!-- jstl 문법 -->
        <c:forEach var="item" items="${members}">
            <tr>
                <td>${item.id}</td>
                <td>${item.username}</td>
                <td>${item.age}</td>
            </tr>
        </c:forEach>
    </table>

    <a href="new-form"> 등록 </a>
</body>
</html>
