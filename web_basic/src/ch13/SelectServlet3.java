package ch13;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SelectServlet3 extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)

			throws ServletException, IOException {

		String error = "";

		try {

			//パラメータの取得

			String id = request.getParameter("id");

			//DTOオブジェクト宣言

			AccountInfo account = new AccountInfo();

			//DAOオブジェクト宣言

			AccountDao3 objDao3 = new AccountDao3();

			//1件検索メソッドを呼び出し

			account = objDao3.selectById(id);

			//検索結果を持ってlist.jspにフォワード

			request.setAttribute("account", account);

		} catch (IllegalStateException e) {

			error = "DB接続エラーの為、一覧表示はできませんでした。";

		} catch (Exception e) {

			error = "予期せぬエラーが発生しました。<br>" + e;

		} finally {

			request.setAttribute("error", error);

			request.getRequestDispatcher("/view/ch13/list3.jsp").forward(request, response);

		}

	}

}
