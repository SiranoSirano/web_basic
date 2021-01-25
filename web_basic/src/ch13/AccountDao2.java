package ch13;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AccountDao2 {

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

	//データベースから全てのアカウント情報の検索を行うメソッド

	public ArrayList<AccountInfo> search(String id) {

		//変数宣言

		Connection con = null;

		Statement smt = null;

		//return用オブジェクトの生成

		ArrayList<AccountInfo> list = new ArrayList<AccountInfo>();

		//SQL文

		String sql = "SELECT * FROM account WHERE id LIKE '%" + id + "%'";

		try {

			con = getConnection();

			smt = con.createStatement();

			//SQLをDBへ発行

			ResultSet rs = smt.executeQuery(sql);

			//検索結果を配列に格納

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

		return list;

	}

}
