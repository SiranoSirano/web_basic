package servlet;
//ユーザー登録に関するリクエストを処理するコントローラー

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.RegisterUserLogic;
import model.User;

@WebServlet("/RegisterUser")
public class RegisterUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	//getの時にregisterFormにリクエストをフォワードしてユーザー登録を行う画面の出力
	//またget時でもnullとdoneで２つのパターンがあり、条件分岐する
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//初回＝pathがnullの時のフォワード
		//フォワード先、どこにフォワードするかの変数。初期値に設定するので = null になっている
		String forwardPath = null;

		//条件分岐の信号の取得(action = null or done)
		//サーブレットクラスの動作を決定する「action」の値をリクエストパラメータから取得
		String action = request.getParameter("action");

		//登録の開始時、action = null の時の処理
		if(action == null) {

			//フォワード先を決定
			forwardPath = "/WEB-INF/jsp/registerForm.jsp";
		}

		//登録のする時、action = done の時の処理。入力された情報を登録して、フォワードし、画面に出力する
		else if (action.equals("done")) {
			//保存された登録内容をセッションスコープから取得
			//(セッションスコープの取得＋セッションスコープから保存されたインスタンスの取得)
			HttpSession session = request.getSession();
			User registerUser = (User) session.getAttribute("registerUser");

			//登録処理の呼び出し、実行
			RegisterUserLogic logic = new RegisterUserLogic();
			logic.execute(registerUser);

			//不要となったセッションスコープ内のインスタンスを削除(登録内容の消去)
			session.removeAttribute("registerUser");

			//登録した後にどこにフォワードするか設定
			forwardPath ="/WEB-INF/jsp/registerDone.jsp";
		}

		//設定されたフォワード先にフォワードする、フォワードのあて先のforwardPathを使って
		//アクションがnullとdoneでも不備なく動けるようにする

		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//リクエストパラメータの取得
		//		このリクエストパラメータを増やして社員情報に必要な分まで増やす。
		//		パラメータが足りない時にエラーメッセージを未入力、～～の形式で入力してくださいと表示する
		request.setCharacterEncoding("UTF-8");
		String userName = request.getParameter("userName");
		String pass = request.getParameter("pass");

		//登録するユーザー情報を設定
		User registerUser = new User(userName,pass);

		//セッションスコープに登録ユーザーを保存
		HttpSession session = request.getSession();
		session.setAttribute("registerUser", registerUser);

		//フォワードを行う
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/registerConfirm.jsp");
		dispatcher.forward(request, response);

	}

}
