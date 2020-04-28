package servlet;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.GetMutterLogic;
import model.Mutter;
import model.PostMutterLogic;
import model.User;

/**
 * Servlet implementation class Main
 */
@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Main() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//セッションスコープからユーザー情報を取得
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");
		
		//投稿一覧を取得
		GetMutterLogic getMutterLogic = new GetMutterLogic();
		List<Mutter> mutterList = getMutterLogic.execute(loginUser);
		request.setAttribute("mutterList", mutterList);
		
		//ログインセッションが切れていたらログイン画面へ遷移
		if (loginUser == null) {
			response.sendRedirect("/docoTsubu");
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String text = request.getParameter("text");
		User loginUser = null;
		
		//入力値チェック
		if (text != null && text.length() != 0) {
			
			//セッションスコープに保存されたユーザ情報を取得
			HttpSession session = request.getSession();
			loginUser = (User) session.getAttribute("loginUser");
			
			//現在時刻の取得
			LocalDateTime now = LocalDateTime.now();
			String datetime = now.getMonthValue() + "/" + now.getDayOfMonth() + " " + now.getHour() + ":" + now.getMinute();
			
			//つぶやきをつぶやきリストに追加
			Mutter mutter = new Mutter(loginUser.getUserId(), datetime, text);
			PostMutterLogic postMutterLogic = new PostMutterLogic();
			postMutterLogic.execute(mutter);

		} else {
			request.setAttribute("errorMsg", "つぶやきが入力されていません");
		}
		
		GetMutterLogic getMutterLogic = new GetMutterLogic();
		List<Mutter> mutterList = getMutterLogic.execute(loginUser);
		
		request.setAttribute("mutterList", mutterList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
		dispatcher.forward(request, response);
	}
}
