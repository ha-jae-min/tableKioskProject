<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Menu</title>
    <style>
        .category-button {
            margin: 5px;
            padding: 10px;
            background-color: blue;
            color: white;
            border: none;
            cursor: pointer;
            border-radius: 5px;
            text-decoration: none;
        }

        .category-button.active {
            background-color: #45a049;
        }

        .menu-item {
            margin: 10px 0;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }

        .menu-item h3 {
            margin: 0;
            font-size: 1.2em;
        }

        .hidden {
            display: none;
        }
    </style>
</head>
<body>
<h1>MENU</h1>

<!-- 카테고리 버튼 생성 -->
<div>
    <c:set var="lastCategoryId" value="-1" />
    <c:set var="selectedCategory" value="${param.category}" />
    <c:forEach var="menu" items="${menuList}">
        <c:if test="${lastCategoryId != menu.category.cno}">
            <a class="category-button ${selectedCategory == menu.category.cno ? 'active' : ''}"
               href="?category=${menu.category.cno}">
                    ${menu.category.name}
            </a>
            <c:set var="lastCategoryId" value="${menu.category.cno}" />
        </c:if>
    </c:forEach>
</div>

<!-- 카테고리별 메뉴 표시 -->
<c:set var="lastCategoryId" value="-1" />
<c:forEach var="menu" items="${menuList}">
<c:if test="${selectedCategory == null || selectedCategory == menu.category.cno}">
<c:if test="${lastCategoryId != menu.category.cno}">

<!-- 이전 카테고리 섹션 닫기 -->
<c:if test="${lastCategoryId != -1}">
    </div>
</c:if>

<!-- 카테고리 -->
<div id="category-${menu.category.cno}" class="category-section">
    <h2>${menu.category.name}</h2>
    <c:set var="lastCategoryId" value="${menu.category.cno}" />
    </c:if>
    <div class="menu-item">
        <img src="/img/m${menu.mno}_c${menu.category.cno}.jpg" alt="${menu.name} image">
        <h3>${menu.name}</h3>
        <p>${menu.description}</p>
    </div>
    </c:if>
    </c:forEach>
</div>

</body>
</html>
