<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<%
//セッションスコープからユーザー情報を取得
User loginUser = (User) session.getAttribute("loginUser");
%>
<% String errorMsg = (String) request.getAttribute("errorMsg"); %>
<% String Msg = (String) request.getAttribute("Msg"); %>
<!DOCTYPE html>
<html>
	<head>
        <meta charset="UTF-8">
        <title>profile</title>
        <link rel="stylesheet" href="./css/edit_account.css">
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
                        <li class="item" id="selected"><a href="/docoTsubu/EditProfile"># profile</a></li>
                        <li class="item"><a href="/docoTsubu/Logout"># sign out</a></li>
                    </ul>
                </nav>
            </div>
            <div class="profile-wrapper">
                <div class="profile">
                    <div class="title">#profile</div>
                    <% if (errorMsg != null) { %>
                		<p id="errmsg" style="color:red"><%= errorMsg %></p>
               		<% } %>
               		<% if (Msg != null) { %>
                		<p id="msg"><%= Msg %></p>
               		<% } %>
                    <div class="profile-main">
                        <form action="/docoTsubu/EditProfile" method="post" enctype="multipart/form-data">
                            <div class="left-side">
                                <div class="account-info">
                                    <label for="name">表示名</label>
                                    <input type="text" id="username" name="new_displayname" value="<%= loginUser.getDisplayName() %>">
                                </div>
                            </div>
                            <div class="right-side">
	                            <% if (loginUser.getIconPath() == null) { %>
	                            	<img id="preview" src="./image/default_icon.jpg" width="50px" height="50px">
	                            <% } else { %>
	                            	<img id="preview" src=<%= loginUser.getIconPath() %> width="50px" height="50px">
	                            <%} %>
                                <!-- <img id="preview"> -->
                                <div class="fileBtn">
                                    プロフィール画像を編集
                                    <input type="file" id="profile-img" accept="image/*" name="file">
                                </div>
                                <script type="text/javascript" src="./js/previewImage.js"></script>
                                <div class="button-wrapper">
                                    <div class="cancel">
                                        <a href="/docoTsubu/Main">キャンセル</a>
                                    </div>
                                    <input type="submit" value="変更を保存する"> 
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>