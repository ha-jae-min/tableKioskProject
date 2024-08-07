<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>메뉴 검색</title>
    <style>
        .category-header {
            margin-top: 20px;
            padding: 10px;
            background-color: #f2f2f2;
            font-size: 1.5em;
        }

        .menu-item {
            margin-left: 20px;
            margin-bottom: 10px;
            font-size: 1.2em;
        }

        .menu-item span {
            font-weight: bold;
        }
    </style>
</head>
<body>
<h1>메뉴 검색</h1>
<form method="post" action="/search"> <!-- action 경로 수정 -->
    <input type="text" name="keyword" placeholder="메뉴 검색" required>
    <button type="submit">검색</button>
</form>

<c:choose>
    <c:when test="${not empty menuList}">
        <c:set var="lastCategoryId" value="-1" />

        <c:forEach var="menu" items="${menuList}">
            <!-- 카테고리가 바뀔 때만 헤더 출력 -->
            <c:if test="${lastCategoryId != menu.category.categoryId}">
                <c:set var="lastCategoryId" value="${menu.category.categoryId}" />
                <div class="category-header">${menu.category.name}</div>
            </c:if>

            <!-- 메뉴 아이템 출력 -->
            <div class="menu-item">
                <span>${menu.name}</span>: ${menu.description}
            </div>
        </c:forEach>
    </c:when>
    <c:otherwise>
        <p>등록된 메뉴가 없습니다.</p>
    </c:otherwise>
</c:choose>

</body>
</html>
