package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SearchDao;
import model.Employee;

public class SearchServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)

			throws ServletException, IOException {

		String error = "";

		try {

			//パラメータの取得

			String name = request.getParameter("NAME");

			//配列宣言

			ArrayList<Employee> list = new ArrayList<Employee>();

			//オブジェクト宣言
			//obj Dao変える？

			SearchDao objDao2 = new SearchDao();

			//全検索メソッドを呼び出し

			list = objDao2.search(name);

			//検索結果を持ってlist.jspにフォワード
			//employyじゃなくていいのか

			request.setAttribute("list", list);

		} catch (IllegalStateException e) {

			error = "DB接続エラーの為、一覧表示できませんでした。";

		} catch (Exception e) {

			error = "予期せぬエラーが発生しました。<br>" + e;

		} finally {

			request.setAttribute("error", error);

			request.getRequestDispatcher("search.jsp").forward(request, response);

		}

	}

}
