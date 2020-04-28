<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String errorMsg = (String) request.getAttribute("errorMsg"); %>
<!DOCTYPE html>
<html>
	<head>
        <meta charset="UTF-8">
        <title>DocoTsubu Login</title>
        <link rel="stylesheet" href="./css/login.css">
	</head>
	<body>
		<div class="contents">
            <div class="wrapper">
                <div class="subtitle">DocoTsubu</div>
                <% if (errorMsg != null) { %>
                	<p style="color:yellow"><%= errorMsg %></p>
               	<% } %>
                <form action="/docoTsubu/Login" method="post">
                    <div class="login-info">
                        <label for="name">ユーザー名</label>
                        <input type="text" id="username" name="userid">
                    </div>
                    <div class="login-info">
                        <label for="pass">パスワード</label>
                        <input type="password" id="password" name="pass">
                    </div>
                    <input type="submit" class="button" value="ログイン"> 
                </form>
                <a class="create-account" href="/docoTsubu/CreateAccount">アカウントを作成する</a>
            </div>
        </div>
	</body>
</html>