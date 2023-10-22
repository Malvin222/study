<%--
  Created by IntelliJ IDEA.
  User: maheb
  Date: 2023-10-19
  Time: 오후 7:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>List Page</h1>

<ul>
    <c:forEach var="dto" items="${list}">

        <li>${dto}</li>
    </c:forEach>
    <c:forEach var="num" begin="1" end="10">
        <li>${num}</li>
    </c:forEach>

    <c:if test="${list.size() % 2 == 0}">
        짝수
    </c:if>
    <c:if test="${list.size() % 2 != 0}">
        홀수
    </c:if>

    <c:choose>
        <c:when test="${list.size() % 2 ==0}">
            짝수
        </c:when>
        <c:otherwise>
            홀수
        </c:otherwise>
    </c:choose>

    <c:set var="target" value="5"></c:set>
    <ul>
        <c:forEach var="num" begin="1" end="10">
            ${num}
            <c:if test="${num == target}">
                num is target
            </c:if>
        </c:forEach>
    </ul>
</ul>
</body>
</html>
