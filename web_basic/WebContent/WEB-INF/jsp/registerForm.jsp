<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 最初のユーザー登録画面を出力するビュー --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>社員登録</title>
</head>
<body>
<form action ="/社員情報管理システムDB接続/RegisterUser" method ="post">
<h1>ユーザー登録画面</h1>
<h2>ユーザーの登録情報を入力してください</h2>
<%-- ここに必要な情報を加える --%>
ユーザー名:<input type = "text" name="userName"><br>
パスワード:<input type="password" name="pass"><br>
<input type ="submit" value="確認">
</form>
</body>
</html>