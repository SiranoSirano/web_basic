<%@page contentType="text/html; charset=UTF-8"%>
<html>

<head>

<title>社員データを登録する</title>

</head>

<body>

	<div style="text-align: center">

		<h2 style="text-align: center">登録データ入力画面</h2>

		<hr style="height: 3; background-color: #0000ff" />

		<br> 登録する社員情報を入力してください。

		<form action="<%=request.getContextPath()%>/RegisterEmployeeServlet">
<--! ここの名前とフォーム内のテキスト、どういったフォームにするかを変更する !-->
			<table style="margin: 0 auto">

				<tr>

					<td style="width: 60">ID</td>

					<td><input type=text size="30" name="id"></input></td>

				</tr>

				<tr>

					<td style="width: 60">名前</td>

					<td><input type=text size="30" name="name"></input></td>

				</tr>

				<tr>

					<td style="width: 70">アドレス</td>

					<td><input type=text size="30" name="email"></input></td>

				</tr>

				<tr>

					<td style="width: 60">権限</td>

					<td><select name="authority">

							<option value="管理者">管理者</option>

							<option value="一般">一般</option>

					</select></td>

				</tr>

				<tr>

					<td colspan=2 style="text-align: center"><input type="submit"
						value="登録"></td>

				</tr>

			</table>

		</form>

		<br>

	</div>

</body>

</html>

