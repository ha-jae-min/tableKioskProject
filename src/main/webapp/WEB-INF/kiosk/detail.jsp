<%@ page import="java.math.BigDecimal" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order Details</title>
</head>
<body>
<h1>Order Details</h1>

<%
    // 요청 속성에서 수량, 가격, 총 가격 가져오기
    Integer quantity = (Integer) request.getAttribute("quantity");
    BigDecimal price = (BigDecimal) request.getAttribute("price");
    BigDecimal totalPrice = (BigDecimal) request.getAttribute("totalPrice");
%>

<p>가격 : <%= price %></p>
<p>개수 : <%= quantity %></p>
<p>총가격 : <%= totalPrice %>원</p>

<form action="/menu" method="get">
    <button>menu</button>
</form>
</body>
</html>
