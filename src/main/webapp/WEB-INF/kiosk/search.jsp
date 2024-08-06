<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 2024-08-06
  Time: 오전 11:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- JSTL 코어 라이브러리 추가 -->
<html>
<head>
    <title>메뉴 검색</title>
</head>
<body>
<h1>메뉴 검색</h1>
<form method="post" action="/search"> <!-- action 경로 수정 -->
    <input type="text" name="keyword" placeholder="메뉴 검색" required>
    <button type="submit">검색</button>
</form>

<c:if test="${not empty searchResults}">
    <h2>검색 결과</h2>
    <ul>
        <c:forEach var="menu" items="${searchResults}">
            <li>
                <strong>${menu.name}</strong>: ${menu.description}<br>
                가격: ${menu.price}원
            </li>
        </c:forEach>
    </ul>
</c:if>

<c:if test="${empty searchResults}">
    <p>검색 결과가 없습니다.</p>
</c:if>

</body>
</html>
