package model;

import dao.AccountDAO;

public class UpdateAccount {
	public boolean execute(User user, String new_displayname, String profile_image) {
		AccountDAO dao = new AccountDAO();
		return dao.update(user, new_displayname, profile_image);
	}
}
