<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        .form-group {
            margin-bottom: 10px;
        }
        label {
            display: inline-block;
            width: 50px; /* 라벨의 넓이를 조정하여 정렬 */
        }
        input {
            padding: 5px;
            margin: 5px 0;
        }
        .login-button {
            margin-left: 55px; /* 버튼을 인풋칸과 맞추기 위한 조정 */
        }
    </style>
</head>
<body>
<h1>Login Page</h1>
<form action="/login" method="post">
    <div class="form-group">
        <label for="uid">uid</label>
        <input id="uid" name="uid" type="text">
    </div>
    <div class="form-group">
        <label for="upw">upw</label>
        <input id="upw" name="upw" type="password">
    </div>
    <div class="form-group login-button">
        <button>LOGIN</button>
    </div>
</form>
</body>
</html>