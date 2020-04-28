package servlet;

import java.io.IOException;
//import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DeleteBookmarkLogic;
import model.GetBookmarkLogic;
import model.Mutter;
import model.PostBookmarkLogic;
import model.User;

/**
 * Servlet implementation class Bookmark
 */
@WebServlet("/Bookmark")
public class Bookmark extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Bookmark() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// ユーザ情報の取得
		HttpSession session = request.getSession();
		User loginUser = (User)session.getAttribute("loginUser");
		
		// DBから取得
		GetBookmarkLogic getBookmarkLogic = new GetBookmarkLogic();
		List<Mutter> bookmarkMutterList = getBookmarkLogic.execute(loginUser);
		request.setAttribute("bookmarkMutterList", bookmarkMutterList);
		
		// ログインセッションが切れてたらログイン画面へ
		if (loginUser == null) {
			response.sendRedirect("/docoTsubu");
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/bookmarks.jsp");
			dispatcher.forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		int mutter_id = Integer.parseInt(request.getParameter("id"));
		String errorMsg = null;
		boolean postStatus = false;
		
		// ユーザ情報の取得
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");
		
		// action='post'だったら、DBにINSERT
		// action='delet'だったら、DBにDELETE
		if(action.equals("post")) {
			PostBookmarkLogic postBookmarkLogic = new PostBookmarkLogic();
			postStatus = postBookmarkLogic.execute(loginUser, mutter_id);
			if(!postStatus) {
				errorMsg = "お気に入り登録が失敗しました。";
			}
		} else if (action.equals("delete")) {
			DeleteBookmarkLogic deleteBookmarkLogic = new DeleteBookmarkLogic();
			postStatus = deleteBookmarkLogic.execute(loginUser, mutter_id);
			if(!postStatus) {
				errorMsg = "お気に入り解除が失敗しました。";
			}
		}
		
		request.setAttribute("errorMsg", errorMsg);
	}

}
