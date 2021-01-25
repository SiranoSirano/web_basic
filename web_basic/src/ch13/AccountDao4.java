
package ch13;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class AccountDao4 {

	//接続用の情報をフィールドに定数として定義

	private static String URL = "jdbc:h2:~/社員情報管理システムDB接続";
	private static String USER = "sa";
	private static String PASS = "";

	//データベース接続を行うメソッド

	public static Connection getConnection() {

		try {

			Connection con = DriverManager.getConnection(URL, USER, PASS);

			return con;

		} catch (Exception e) {

			throw new IllegalStateException(e);

		}

	}

	//データベースへデータを登録するメソッド

	public int insert(AccountInfo accountinfo) {

		//変数宣言

		Connection con = null;

		Statement smt = null;

		//return用変数

		int count = 0;

		//SQL文

		String sql = "INSERT INTO account VALUES('"

				+ accountinfo.getId() + "','"

				+ accountinfo.getName() + "','"

				+ accountinfo.getEmail() + "','"

				+ accountinfo.getAuthority() + "')";

		try {

			con = getConnection();

			smt = con.createStatement();

			//SQLをDBへ発行

			count = smt.executeUpdate(sql);

		} catch (Exception e) {

			throw new IllegalStateException(e);

		} finally {

			//リソースの開放

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

		return count;

	}

}
