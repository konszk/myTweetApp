package model;

import dao.BookmarkDAO;

public class PostBookmarkLogic {
	public boolean execute(User user, int mutter_id) {
		BookmarkDAO dao = new BookmarkDAO();
		return dao.create(user, mutter_id);
		
	}

}
