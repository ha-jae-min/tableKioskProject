<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>--%>

<%--<%@ include file="../includes/header.jsp" %>--%>

<%--<html>--%>
<%--<head>--%>
<%--    <title>Menu</title>--%>
<%--    <style>--%>
<%--        .category-button {--%>
<%--            margin: 5px;--%>
<%--            padding: 10px;--%>
<%--            background-color: blue;--%>
<%--            color: white;--%>
<%--            border: none;--%>
<%--            cursor: pointer;--%>
<%--            border-radius: 5px;--%>
<%--            text-decoration: none;--%>
<%--        }--%>

<%--        .category-button.active {--%>
<%--            background-color: #45a049;--%>
<%--        }--%>

<%--        .menu-item {--%>
<%--            margin: 10px 0;--%>
<%--            padding: 10px;--%>
<%--            border: 1px solid #ddd;--%>
<%--            border-radius: 5px;--%>
<%--            display: flex;--%>
<%--            align-items: center;--%>
<%--        }--%>

<%--        .menu-item img {--%>
<%--            width: 100px; /* Set the width of the image */--%>
<%--            height: 100px; /* Set the height of the image */--%>
<%--            object-fit: cover; /* Ensure the image covers the area proportionally */--%>
<%--            margin-right: 20px; /* Space between image and text */--%>
<%--        }--%>

<%--        .menu-item h3 {--%>
<%--            margin: 0;--%>
<%--            font-size: 1.2em;--%>
<%--        }--%>

<%--        .menu-details {--%>
<%--            display: flex;--%>
<%--            flex-direction: column;--%>
<%--        }--%>

<%--        .menu-details h3, .menu-details p {--%>
<%--            margin: 5px 0;--%>
<%--        }--%>

<%--        .hidden {--%>
<%--            display: none;--%>
<%--        }--%>
<%--    </style>--%>
<%--</head>--%>

<%--<!-- 카테고리 버튼 생성 -->--%>
<%--<div>--%>
<%--    <c:set var="lastCategoryId" value="-1" />--%>
<%--    <c:set var="selectedCategory" value="${param.category}" />--%>

<%--    <!-- Check if the selectedCategory is valid -->--%>
<%--    <c:set var="isValidCategory" value="false" />--%>
<%--    <c:forEach var="menu" items="${menuList}">--%>
<%--        <c:if test="${selectedCategory == menu.category.cno}">--%>
<%--            <c:set var="isValidCategory" value="true" />--%>
<%--        </c:if>--%>
<%--    </c:forEach>--%>

<%--    <!-- Use default category if selectedCategory is invalid -->--%>
<%--    <c:if test="${!isValidCategory}">--%>
<%--        <c:set var="selectedCategory" value="1" />--%>
<%--    </c:if>--%>

<%--    <c:forEach var="menu" items="${menuList}">--%>
<%--        <c:if test="${lastCategoryId != menu.category.cno}">--%>
<%--            <a class="category-button ${selectedCategory == menu.category.cno ? 'active' : ''}"--%>
<%--               href="?category=${menu.category.cno}">--%>
<%--                    ${menu.category.name}--%>
<%--            </a>--%>
<%--            <c:set var="lastCategoryId" value="${menu.category.cno}" />--%>
<%--        </c:if>--%>
<%--    </c:forEach>--%>
<%--</div>--%>

<%--<!-- 카테고리별 메뉴 표시 -->--%>
<%--<c:set var="lastCategoryId" value="-1" />--%>
<%--<c:forEach var="menu" items="${menuList}">--%>
<%--<c:if test="${selectedCategory == null || selectedCategory == menu.category.cno}">--%>
<%--<c:if test="${lastCategoryId != menu.category.cno}">--%>

<%--<!-- 이전 카테고리 섹션 닫기 -->--%>
<%--<c:if test="${lastCategoryId != -1}">--%>
<%--    </div>--%>
<%--</c:if>--%>

<%--<!-- 카테고리 -->--%>
<%--<div id="category-${menu.category.cno}" class="category-section">--%>
<%--    <h2>${menu.category.name}</h2>--%>
<%--    <c:set var="lastCategoryId" value="${menu.category.cno}" />--%>
<%--    </c:if>--%>
<%--    <div class="menu-item">--%>
<%--        <img src="/img/m${menu.mno}_c${menu.category.cno}.jpg" alt="${menu.name} image">--%>
<%--        <div class="menu-details">--%>
<%--            <h3>${menu.name}</h3>--%>
<%--            <h3>가격 : ${menu.price}</h3>--%>
<%--            <p>${menu.description}</p>--%>

<%--            &lt;%&ndash;  이 부분이 수량 증가시켜서 넘기는 부분      &ndash;%&gt;--%>

            <form action="/order" method="post">
                <input type="hidden" name="price" value="${menu.price}" />
                <label for="quantity">Quantity:</label>
                <input type="number" id="quantity" name="quantity" min="1" value="1" />
                <button>Add to Order</button>
            </form>

<%--            &lt;%&ndash;  여기 사이          &ndash;%&gt;--%>

<%--        </div>--%>
<%--    </div>--%>
<%--    </c:if>--%>
<%--    </c:forEach>--%>
<%--</div>--%>

<%--</body>--%>
<%--</html>--%>

<%--<%@ include file="../includes/footer.jsp" %>--%>
