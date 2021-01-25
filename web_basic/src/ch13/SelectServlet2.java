package ch13;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SelectServlet2 extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)

			throws ServletException, IOException {

		String error = "";

		try {

			//パラメータの取得

			String id = request.getParameter("id");

			//配列宣言

			ArrayList<AccountInfo> list = new ArrayList<AccountInfo>();

			//オブジェクト宣言

			AccountDao2 objDao2 = new AccountDao2();

			//全検索メソッドを呼び出し

			list = objDao2.search(id);

			//検索結果を持ってlist.jspにフォワード

			request.setAttribute("list", list);

		} catch (IllegalStateException e) {

			error = "DB接続エラーの為、一覧表示はできませんでした。";

		} catch (Exception e) {

			error = "予期せぬエラーが発生しました。<br>" + e;

		} finally {

			request.setAttribute("error", error);

			request.getRequestDispatcher("/view/ch13/list2.jsp").forward(request, response);

		}

	}

}
