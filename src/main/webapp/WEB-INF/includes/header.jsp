<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Header Example</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        header {
            background-color: #343a40;
            color: white;
            text-align: center;
            padding: 20px 0;
        }

        .lead {
            margin-bottom: 20px;
        }

        .link-container {
            display: inline-block;
            margin-top: 10px;
        }

        .link-container form {
            display: inline;
            margin: 0 10px;
        }

        .link-container a {
            color: white;
            text-decoration: none;
            font-weight: bold;
            font-size: 18px;
            padding: 8px 15px;
            border: 2px solid white;
            border-radius: 5px;
            transition: background-color 0.3s, color 0.3s;
            display: inline-block;
        }

        .link-container a:hover {
            background-color: white;
            color: #343a40;
        }
    </style>
</head>
<body>

<header class="bg-dark text-white text-center py-3">
    <h1>올드팝</h1>
    <p class="lead">맛있는 안주와 함께하는 즐거운 시간!</p>
    <div class="link-container">
        <form action="/startkiosk" method="get">
            <a href="#" onclick="this.closest('form').submit(); return false;">처음화면</a>
        </form>
        <form action="/kiosk" method="get">
            <a href="#" onclick="this.closest('form').submit(); return false;">메뉴화면</a>
        </form>
    </div>
</header>

</body>
</html>
