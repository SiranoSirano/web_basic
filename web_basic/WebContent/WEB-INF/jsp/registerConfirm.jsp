<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "model.Login" %>
<%
Login registerUser = (Login) session.getAttribute("registerUser");
%>
<%--ユーザー登録確認画面を出力するビュー --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー登録確認画面</title>
</head>
<body>
<p>下記の登録内容でよろしいですか？</p>

<p>
ユーザー名：<%= registerUser.getUserName() %>
パスワード：<%= registerUser.getPass() %>
</p>
<a href ="/社員情報管理システムDB接続/RegisterUser">戻る</a>

<a href ="/社員情報管理システムDB接続/RegisterUser?action=done">ユーザー登録</a>
</body>
</html>