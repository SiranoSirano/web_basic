package ch13;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class AccountDao5 {

	//接続用の情報をフィールドに定数として定義
	private static String RDB_DRIVE = "com.mysql.jdbc.Driver";
	private static String URL = "jdbc:mysql://localhost/accountdb";
	private static String USER = "root";
	private static String PASS = "root123";

	//データベース接続を行うメソッド
	public static Connection getConnection(){
		try{
			Class.forName(RDB_DRIVE);
			Connection con = DriverManager.getConnection(URL, USER, PASS);
			return con;
		}catch(Exception e){
			throw new IllegalStateException(e);
		}
	}

	//データベースへデータを修正するメソッド
	public int update(AccountInfo accountinfo){
		//変数宣言
		Connection con = null;
		Statement  smt = null;

		//return用変数
		int count = 0;

		//SQL文
		String sql = "UPDATE account SET "
				+ "name = '" + accountinfo.getName() + "', "
				+ "email = '" + accountinfo.getEmail() + "', "
				+ "authority = '" + accountinfo.getAuthority() + "' "
				+ "WHERE id = '" + accountinfo.getId() + "'";

		try{
			con = getConnection();
			smt = con.createStatement();

			//SQLをDBへ発行
			count = smt.executeUpdate(sql);

		}catch(Exception e){
			throw new IllegalStateException(e);
		}finally{
			//リソースの開放
			if(smt != null){
				try{smt.close();}catch(SQLException ignore){}
			}
			if(con != null){
				try{con.close();}catch(SQLException ignore){}
			}
		}
		return count;
	}
}