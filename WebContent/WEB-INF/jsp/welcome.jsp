<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<%
//セッションスコープからユーザー情報を取得
User loginUser = (User) session.getAttribute("loginUser");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Welcome to DocoTsubu</title>
        <link rel="stylesheet" href="./css/welcome.css">
    </head>
    <body>
        <div class="contents">
            <div class="side-menu">
                <h2><a class="subtitle" href="/docoTsubu/Main">DocoTsubu</a></h2>
                <nav>
                    <ul class="main-nav">
                        <li id="nav-title">Menu</li>
                        <li class="item"><a href="/docoTsubu/Main"># home</a></li>
                        <li class="item"><a href="/docoTsubu/Bookmark"># bookmarks</a></li>
                        <li class="item"><a href="/docoTsubu/EditProfile"># profile</a></li>
                        <li class="item"><a href="/docoTsubu"># sign out</a></li>
                    </ul>
                </nav>
            </div>
            <div class="message-wrapper">
                <div class="message">
                    <p>Welcome to DocoTsubu!!</p>
                    <a href="/docoTsubu/Main">Goto home</a>
                </div>
            </div>
        </div>
    </body>
</html>