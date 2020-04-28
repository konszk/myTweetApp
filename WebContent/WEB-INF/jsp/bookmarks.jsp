<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User, model.Mutter, java.util.List" %>
<%
//セッションスコープに保存されたユーザ情報を取得
User loginUser = (User) session.getAttribute("loginUser");
//アプリケーションスコープに保存されたつぶやきリストを取得
List<Mutter> bookmarkMutterList = (List<Mutter>) request.getAttribute("bookmarkMutterList");
//リクエストスコープに保存されたエラーメッセージを取得
String errorMsg = (String) request.getAttribute("errorMsg"); 
%>
<!DOCTYPE html>
<html>
	<head>
        <meta charset="UTF-8">
        <title>DocoTsubu #bookmark</title>
        <link rel="stylesheet" href="css/home.css">
        <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.3.1.min.js"></script>
        <script type="text/javascript" src="./js/bookmark.js"></script>
	</head>
    <body>
        <div class="contents">
            <div class="side-menu">
                <h2><a class="subtitle" href="home.html">DocoTsubu</a></h2>
                <nav>
                    <ul class="main-nav">
                        <li id="nav-title">Menu</li>
                        <li class="item"><a href="/docoTsubu/Main"># home</a></li>
                        <li class="item" id="selected"><a href="/docoTsubu/Bookmark"># bookmarks</a></li>
                        <li class="item"><a href="/docoTsubu/EditProfile"># profile</a></li>
                        <li class="item"><a href="/docoTsubu"># sign out</a></li>
                    </ul>
                </nav>
            </div>
            <div class="title">#bookmarks</div>
            <div class="main">
                <div class="wrapper">
                    <div class="tweet-list">
				        <% if (errorMsg != null) { %>
							<p><%= errorMsg %></p>
						<% } %>
						<% for (Mutter mutter : bookmarkMutterList) { %>
								<div class="tweet-item">
									<div class="icon">
										<img src=<%= mutter.getIconPath() %> width="50px">
									</div>
									<div class="tweet-info">
										<div class="time-wrapper">
											<div class="name"><%= mutter.getUserName() %></div>
											<div class="date"><%= mutter.getDateTime() %></div>
											<div class="bookmark">
												<img id="bookmark-<%= mutter.getId() %>" src="./image/bookmark.jpg" alt="お気に入り" onclick="changeImg(this.id)">
											</div>
										</div>
										<div class="tweet">
											<%= mutter.getText() %>
										</div>
									</div>
								</div>
						<% } %>                       
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>