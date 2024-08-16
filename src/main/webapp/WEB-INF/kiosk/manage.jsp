<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="org.example.tablekioskproject.vo.OrderDetailVO" %>
<%@ page import="java.util.Map" %>

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
        .table-card {
            margin-top: 20px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            border: 1px solid #ddd;
            text-align: center;
            cursor: pointer;
            transition: transform 0.3s;
        }
        .table-card:hover {
            transform: scale(1.05);
        }
        .table-card h2 {
            font-size: 1.5em;
            margin-bottom: 15px;
        }
        .total-sum {
            font-weight: bold;
            font-size: 1.5em;
            text-align: center;
            margin-top: 20px;
        }
        .row {
            margin-bottom: 20px;
        }
        form {
            display: inline;
        }
    </style>
</head>
<body>

<div class="container">
    <h1 class="text-center">테이블 주문 내역</h1>
    <div class="row">
        <%
            Map<Integer, BigDecimal> tableTotalSums = (Map<Integer, BigDecimal>) request.getAttribute("tableTotalSums");
            int[] tableNumbers = {1, 2, 3, 4, 5, 6}; // 테이블 번호 배열

            for (int i = 0; i < tableNumbers.length; i++) {
                int table_number = tableNumbers[i];
                BigDecimal totalSum = tableTotalSums.get(table_number); // 해당 테이블의 총합
        %>
        <div class="col-md-4">
            <form action="/manageDetails" method="get">
                <input type="hidden" name="table_number" value="<%= table_number %>">
                <div class="table-card card" onclick="this.parentNode.submit();">
                    <div class="card-body">
                        <h2><%= table_number %>번 테이블</h2>
                        <div class="total-sum">
                            전체 총합: <%= totalSum != null ? totalSum.intValue() : 0 %>원
                        </div>
                    </div>
                </div>
            </form>
        </div>
        <%
            if ((i + 1) % 3 == 0 && (i + 1) < tableNumbers.length) {
        %>
    </div>
    <div class="row">
        <%
                }
            } %>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.0.6/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
