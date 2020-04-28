package model;

import java.util.List;

import dao.BookmarkDAO;

public class GetBookmarkLogic {
	public List<Mutter> execute(User user) {
		BookmarkDAO dao = new BookmarkDAO();
		List<Mutter> mutterList = dao.find(user);
		return mutterList;
	}

}
