<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.example.tablekioskproject.vo.OrderDetailVO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.stream.Collectors" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Collections" %>


<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            padding: 20px;
            background-color: #f8f9fa;
        }
        .card {
            margin-bottom: 20px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }
        .total-price {
            font-weight: bold;
            font-size: 1.2em;
        }
        .btn-remove {
            background-color: #dc3545;
            color: white;
            border: none;
            transition: background-color 0.3s;
        }
        .btn-remove:hover {
            background-color: #c82333;
        }
        h1 {
            margin-bottom: 30px;
        }
        .total-sum {
            margin-top: 20px;
            font-weight: bold;
            font-size: 1.5em;
            text-align: right;
        }
        h1 {
            margin-bottom: 30px;
            margin-top: 20px;
        }
        .d-flex {
            display: flex !important;
        }
        .justify-content-between {
            justify-content: space-between !important;
        }
    </style>
</head>
<body>

<div class="container">
    <%
        List<OrderDetailVO> orderDetails = (List<OrderDetailVO>) request.getAttribute("orderDetails");
        BigDecimal totalSum = (BigDecimal) request.getAttribute("totalSum");
        int table_number = (int) request.getAttribute("table_number");

        // ono로 orderDetails 그룹화
        Map<Integer, List<OrderDetailVO>> groupedOrders = orderDetails.stream()
                .collect(Collectors.groupingBy(OrderDetailVO::getOno));

        // ono 순서대로 정렬
        List<Integer> sortedOnoList = new ArrayList<>(groupedOrders.keySet());
        Collections.sort(sortedOnoList, Collections.reverseOrder());


        // DateTimeFormatter를 생성하여 HH:mm:ss 형식으로 시간을 표시
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    %>

    <h1 class="text-center"><%= table_number %>번 테이블</h1>
    <form action="/manage" method="get">
        <button class="btn btn-primary">테이블 목록</button>
    </form>
    <% if (sortedOnoList != null && !sortedOnoList.isEmpty()) { %>
    <div class="row">
        <% for (Integer ono : sortedOnoList) {
            List<OrderDetailVO> orderGroup = groupedOrders.get(ono);
        %>
        <div class="col-md-12">
            <div class="card">
                <div class="card-body">
                    <div class="d-flex justify-content-between">
                        <p class="card-text">
                            <strong>주문 시간:</strong>
                            <%= orderGroup.get(0).getO_time().format(timeFormatter) %>
                        </p>
                        <div class="d-flex align-items-center">
                            <p class="card-text mr-3">
                                <strong>상태:</strong>
                                <%= orderGroup.get(0).getO_status() %>
                            </p>
                            <form action="/manageDetails" method="post">
                                <input type="hidden" name="table_number" value="<%= table_number %>">
                                <input type="hidden" name="ono" value="<%= ono %>">
                                <button class="btn btn-primary">완료</button>
                            </form>
                        </div>
                    </div>
                    <div class="row">
                        <% for (OrderDetailVO detail : orderGroup) { %>
                        <div class="col-md-4">
                            <div class="card">
                                <img src="/img/m<%= detail.getMno() %>_c<%= detail.getCategory_id() %>.jpg" class="card-img-top" alt="<%= detail.getMenuName() %>" style="height: 150px; object-fit: cover;">
                                <div class="card-body">
                                    <h5 class="card-title"><%= detail.getMenuName() %></h5>
                                    <p class="card-text">
                                        <strong>가격:</strong> <%= detail.getMenuPrice().intValue() %>원<br>
                                        <strong>수량:</strong> <%= detail.getQuantity() %><br>
                                        <strong>총합:</strong> <span class="total-price"><%= detail.getTotal_price().intValue() %></span>원
                                    </p>
                                </div>
                            </div>
                        </div>
                        <% } %>
                    </div>
                </div>
            </div>
        </div>
        <% } %>
    </div>
    <div class="total-sum">
        총합: <%= totalSum.intValue() %>원
    </div>
    <% } else { %>
    <p class="text-center">주문 상세 정보가 없습니다.</p>
    <% } %>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.0.6/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
