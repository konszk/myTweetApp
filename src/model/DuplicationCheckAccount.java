package model;

import dao.AccountDAO;

public class DuplicationCheckAccount {
	public Boolean excecute(User user) {
		AccountDAO dao = new AccountDAO();
		return dao.checkDuplication(user);
	}
}
