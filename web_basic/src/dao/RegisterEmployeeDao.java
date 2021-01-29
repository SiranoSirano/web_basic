
package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import model.Employee;

public class RegisterEmployeeDao extends DAO {


	//データベースへデータを登録するメソッド

	public int registerEmployee(Employee employee) {

		//変数宣言

		Connection con = null;

		Statement smt = null;

		//return用変数

		int count = 0;

		//SQL文
		//ここをemployee.getID()などに変える

		String sql = "INSERT INTO EMPLOYEE VALUES('"

				+ accountinfo.getId() + "','"

				+ accountinfo.getName() + "','"

				+ accountinfo.getEmail() + "','"

				+ accountinfo.getAuthority() + "')";

		try {

			con = this.getConnection();

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
