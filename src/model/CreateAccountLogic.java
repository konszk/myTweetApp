package model;

import dao.AccountDAO;

public class CreateAccountLogic {
	public Boolean excecute(User user) {
		AccountDAO dao = new AccountDAO();
		return dao.create(user);
	}
}
