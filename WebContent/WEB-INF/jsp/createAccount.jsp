<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String errorMsg = (String) request.getAttribute("errorMsg"); %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Sign up</title>
        <link rel="stylesheet" href="css/create_account.css">
    </head>
    <body>
        <div class="contents">
            <div class="wrapper">
                <div class="subtitle">Create Account</div>
                <% if (errorMsg != null) { %>
                	<p style="color:red"><%= errorMsg %></p>
               	<% } %>
                <form action="/docoTsubu/CreateAccount" method="POST">
                    <div class="account-info">
                        <label for="name">ユーザーID</label>
                        <input type="text" name="userid" required="required">
                    </div>
                    <div class="account-info">
                        <label for="name">表示名</label>
                        <input type="text" name="displayname" required="required">
                    </div>
                    <div class="account-info">
                        <label for="pass">パスワード</label>
                        <input type="password" name="pass" required="required">
                    </div>
                    <div class="account-info">
                        <label for="pass">パスワード(確認用)</label>
                        <input type="password" name="confirm_pass" required="required">
                    </div>
                    <input type="submit" class="button" value="create"> 
                </form>
            </div>
        </div>
    </body>
</html>