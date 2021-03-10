<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="modeltest.*,servlettest.*,daotest.*,java.util.List" %>
<%
//セッションスコープに保存されたユーザー情報を取得
Login loginUser = (Login) session.getAttribute("loginUser");

//リクエストスコープに保存されたリストを取得
@SuppressWarnings("unchecked")
List<Employee> employeeList = (List<Employee>)request.getAttribute("employeeList");

//リクエストスコープに保存されたエラーメッセージを取得
String errorMsg = (String) request.getAttribute("errorMsg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>社員情報管理システムメイン</title>
</head>
<body>
<h1>社員情報管理システムメイン</h1>
<p><%= loginUser.getUserName() %>さんはログイン中です
<a href ="/社員情報管理システムDB接続/Logout">ログアウト</a>
</p>

<p><a href="/社員情報管理システムDB接続/Main">更新</a></p>
<p></p>
<form action ="/社員情報管理システムDB接続/Employee" method = "post">
<input type="submit" value="社員情報登録">
</form>
<p></p>
<form action ="/社員情報管理システムDB接続/DeleteUser" method = "post">
<input type="submit" value="削除">
</form>

<%--　エラーメッセージのerrorMsgがある場合はそこに遷移して表示する --%>
<%
if(errorMsg != null) {
%>
<p><%=errorMsg%>><p>
<%
}
%>

<%-- ArrayListに格納されたインスタンスを先頭から取得、名前と入力内容を紐づけて表示 --%>
<%-- 拡張for文を使って取り出していく --%>
<%
for(Employee Employee : employeeList){
%>
	<p><%= Employee.getUserName() %>:<%= Employee.getText() %></p>
<% } %>

</body>
</html>