package ch13;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InsertServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)

			throws ServletException, IOException {

		String error = "";

		try {

			//文字エンコーディングの指定

			request.setCharacterEncoding("UTF-8");

			//DTOオブジェクト宣言

			AccountInfo accountinfo = new AccountInfo();

			//パラメータの取得

			accountinfo.setId(request.getParameter("id"));

			accountinfo.setName(request.getParameter("name"));

			accountinfo.setEmail(request.getParameter("email"));

			accountinfo.setAuthority(request.getParameter("authority"));

			//DAOオブジェクト宣言

			AccountDao4 objDao4 = new AccountDao4();

			//1件登録メソッドを呼び出し

			int count = objDao4.insert(accountinfo);

			//登録された件数を持ってlist.jspにフォワード

			request.setAttribute("count", count);

		} catch (IllegalStateException e) {

			error = "DB接続エラーの為、登録できませんでした。";

		} catch (Exception e) {

			error = "予期せぬエラーが発生しました。<br>" + e;

		} finally {

			request.setAttribute("error", error);

			request.getRequestDispatcher("/view/ch13/insertReceipt.jsp").forward(request, response);

		}

	}

}
