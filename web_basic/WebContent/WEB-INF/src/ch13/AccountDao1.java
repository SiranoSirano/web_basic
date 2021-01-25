package web_basic.ch13;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AccountDao1 {

	//	DB接続に使用する情報
	private final String URL = "jdbc:h2:~/社員情報管理システムDB接続";
	private final String USER = "sa";
	private final String PASS = "";

	//	DB接続のメソッド
	public final Connection getConnection() {
		try {
			Connection con = DriverManager.getConnection(URL, USER, PASS);
			return con;
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	//		DBから全ユーザーの情報検索を行うメソッド
	//	戻り値としてArrayList<AccountInfo> をつかう
	public ArrayList<AccountInfo> selectAll() {
		//	変数宣言
		Connection con = null;
		Statement smt = null;
		//		return用オブジェクトの生成
		ArrayList<AccountInfo> list = new ArrayList<AccountInfo>();
		//		SQL文
		String sql = "SELECT * FROM account";

		try {
			con = getConnection();
			smt = con.createStatement();

			//			SQL文をDBに出力
			ResultSet rs = smt.executeQuery(sql);
			//			検索結果を配列で取得

			while (rs.next()) {
				AccountInfo accountinfo = new AccountInfo();
				accountinfo.setId(rs.getString("id"));
				accountinfo.setName(rs.getString("name"));
				accountinfo.setEmail(rs.getString("email"));
				accountinfo.setAuthority(rs.getString("authority"));
				list.add(accountinfo);
			}

		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			//			リソースの解放
			if (smt != null) {
				try {
					smt.close();
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}
		}
		return list;
	}
}
