package model;

import dao.BookmarkDAO;

public class DeleteBookmarkLogic {
	public boolean execute(User user, int mutter_id) {
		BookmarkDAO dao = new BookmarkDAO();
		return dao.delete(user, mutter_id);
		
	}

}
