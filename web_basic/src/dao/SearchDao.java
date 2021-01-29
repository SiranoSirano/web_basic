package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Employee;

public class SearchDao extends DAO  {
	//データベースから全てのアカウント情報の検索を行うメソッド

	public ArrayList<Employee> search(String id) {

		//変数宣言

		Connection con = null;

		Statement smt = null;

		//return用オブジェクトの生成

		ArrayList<Employee> list = new ArrayList<Employee>();

		//SQL文

		String sql = "SELECT * FROM Employee WHERE name LIKE '%" + id + "%'";

		try {

			con = this.getConnection();

			smt = con.createStatement();

			//SQLをDBへ発行

			ResultSet rs = smt.executeQuery(sql);

			//検索結果を配列に格納
			//setを入れる

			while (rs.next()) {

				Employee employee = new Employee();

				accountinfo.setId(rs.getString("id"));

				accountinfo.setName(rs.getString("name"));

				accountinfo.setEmail(rs.getString("email"));

				accountinfo.setAuthority(rs.getString("authority"));

				list.add(employee);

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
