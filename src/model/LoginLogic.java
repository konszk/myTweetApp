package model;

import dao.AccountDAO;

public class LoginLogic {

	public boolean excecute(User user) {
		if (user.getPass().equals("1234")) {
			user.setDisplayName(user.getUserId());
			return true;
		}
		//return false;
		
		AccountDAO dao = new AccountDAO();
		return dao.find(user);
		
	}
}
