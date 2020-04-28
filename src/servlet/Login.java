package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.LoginLogic;
import model.User;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String userid = request.getParameter("userid");
		String displayname = null;
		String pass = request.getParameter("pass");
		
		//Userインスタンス(User情報)生成
		User user = new User(userid, displayname, pass);
		
		//ログイン処理
		LoginLogic loginLogic = new LoginLogic();
		Boolean isLogin = loginLogic.excecute(user);
		
		//ログイン成功時の処理
		if(isLogin) {
			//ユーザ情報をセッションスコープに保存
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", user);
			response.sendRedirect("/docoTsubu/Main");
		} else {
			request.setAttribute("errorMsg", "ユーザ名またはパスワードが間違っています。");
			RequestDispatcher dispacher = request.getRequestDispatcher("/index.jsp");
			dispacher.forward(request, response);
		}

	}

}
