package model;

import dao.EmployeeDAO2;

public class LoginLogic {
	public boolean execute(User user) {
		EmployeeDAO2 dao = new EmployeeDAO2();
		Employee employee = dao.findByUser(user);
		return employee != null;
		//ここをDBと連携させて,ユーザー名とpasswordの情報を社員の情報と一致させる

	}
}
