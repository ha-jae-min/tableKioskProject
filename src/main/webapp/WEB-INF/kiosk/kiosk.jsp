<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="../includes/header.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Menu</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
    <style>
        .menu-card {
            height: 100%;
            display: flex;
            flex-direction: column;
            justify-content: space-between;
            border-radius: 10px;
            transition: transform 0.3s ease;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        .menu-card:hover {
            transform: translateY(-5px);
        }
        .card-img-top {
            height: 200px;
            object-fit: cover;
            border-top-left-radius: 10px;
            border-top-right-radius: 10px;
        }
        .card-body {
            text-align: center;
            padding: 1rem;
        }
        .card-title {
            font-size: 1.2em;
            font-weight: bold;
            color: #343a40;
        }
        .card-text {
            font-size: 1.1em;
            color: #555;
        }
        .description {
            font-size: 0.9em;
            color: #777;
            margin-top: 10px;
        }
        .active-category {
            background-color: #007bff; /* 활성화된 카테고리 색상 */
            color: white; /* 글자 색상 */
        }
        .input-group {
            max-width: 150px;
            margin: 0 auto;
        }
        .btn-primary {
            background-color: #007bff;
            border-color: #007bff;
            transition: background-color 0.3s ease, border-color 0.3s ease;
        }
        .btn-primary:hover {
            background-color: #0056b3;
            border-color: #0056b3;
        }
        iframe {
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
    </style>
</head>
<body>
<div class="container mt-4">
    <div class="row">
        <div class="col-12">
            <ul class="list-inline text-center">
                <c:set var="selectedCategory" value="${param.category}" /> <!-- 선택된 카테고리 저장 -->
                <li class="list-inline-item">
                    <a href="?category=1" class="btn btn-outline-primary ${selectedCategory == '1' ? 'active-category' : ''}">마른안주류</a>
                </li>
                <li class="list-inline-item">
                    <a href="?category=2" class="btn btn-outline-primary ${selectedCategory == '2' ? 'active-category' : ''}">과일류</a>
                </li>
                <li class="list-inline-item">
                    <a href="?category=3" class="btn btn-outline-primary ${selectedCategory == '3' ? 'active-category' : ''}">튀김류</a>
                </li>
                <li class="list-inline-item">
                    <a href="?category=4" class="btn btn-outline-primary ${selectedCategory == '4' ? 'active-category' : ''}">면류</a>
                </li>
                <li class="list-inline-item">
                    <a href="?category=5" class="btn btn-outline-primary ${selectedCategory == '5' ? 'active-category' : ''}">치킨류</a>
                </li>
                <li class="list-inline-item">
                    <a href="?category=6" class="btn btn-outline-primary ${selectedCategory == '6' ? 'active-category' : ''}">탕류</a>
                </li>
                <li class="list-inline-item">
                    <a href="?category=7" class="btn btn-outline-primary ${selectedCategory == '7' ? 'active-category' : ''}">생맥주</a>
                </li>
                <li class="list-inline-item">
                    <a href="?category=8" class="btn btn-outline-primary ${selectedCategory == '8' ? 'active-category' : ''}">흑맥주</a>
                </li>
                <li class="list-inline-item">
                    <a href="?category=9" class="btn btn-outline-primary ${selectedCategory == '9' ? 'active-category' : ''}">병맥주</a>
                </li>
                <li class="list-inline-item">
                    <a href="?category=10" class="btn btn-outline-primary ${selectedCategory == '10' ? 'active-category' : ''}">수입맥주</a>
                </li>
                <li class="list-inline-item">
                    <a href="?category=11" class="btn btn-outline-primary ${selectedCategory == '11' ? 'active-category' : ''}">소주</a>
                </li>
                <li class="list-inline-item">
                    <a href="?category=12" class="btn btn-outline-primary ${selectedCategory == '12' ? 'active-category' : ''}">집기류</a>
                </li>
                <li class="list-inline-item">
                    <a href="?category=13" class="btn btn-outline-primary ${selectedCategory == '13' ? 'active-category' : ''}">음료</a>
                </li>
            </ul>
        </div>
    </div>

    <%-- 이 부분   --%>

    <div class="row mt-4">
        <div class="col-md-9">
            <div class="row">
                <c:forEach var="menu" items="${menuList}">
                    <div class="col-md-4 mb-4">
                        <div class="card menu-card">
                            <img src="/img/m${menu.mno}_c${menu.categoryId}.jpg" class="card-img-top" alt="${menu.name}" />
                            <div class="card-body">
                                <h5 class="card-title">${menu.name}</h5>
                                <p class="card-text">${fn:substringBefore(menu.price, '.')}원</p>
                                <p class="description">${menu.description}</p>
                                <form action="/order" method="post" target="orderDetailsFrame">
                                    <input type="hidden" name="mno" value="${menu.mno}" />
                                    <input type="hidden" name="table_number" value="1" />
                                    <div class="input-group">
                                        <input type="number" id="quantity" name="quantity" class="form-control" min="1" value="1" />
                                        <div class="input-group-append">
                                            <button class="btn btn-primary" type="submit">담기</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>

        <div class="col-md-3">
            <form action="/detail" method="post">
                <button class="btn btn-primary btn-block mb-2" type="submit">주문하기</button>
            </form>
            <iframe id="orderDetailsFrame" name="orderDetailsFrame" style="width: 100%; height: 100%; border: 2px solid #ccc;"></iframe>
        </div>

    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.0.6/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<script>
    // 새로고침 시 iframe 내용 갱신
    document.getElementById('orderDetailsFrame').src = '/order';
</script>

</body>
</html>
