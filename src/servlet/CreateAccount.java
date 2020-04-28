package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CreateAccountLogic;
import model.DuplicationCheckAccount;
import model.User;

/**
 * Servlet implementation class CreateAccount
 */
@WebServlet("/CreateAccount")
public class CreateAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateAccount() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// アカウント作成画面へ遷移
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/createAccount.jsp");
		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String userid = request.getParameter("userid");
		String displayname = request.getParameter("displayname");
		String pass = request.getParameter("pass");
		String confirm_pass = request.getParameter("confirm_pass");
		
		// パスワードチェック
		if (pass.equals(confirm_pass)) {
			if (String.valueOf(pass).length() == 4) {
				//Userインスタンス(User情報)生成
				User user = new User(userid, displayname, pass);

				//重複チェック
				DuplicationCheckAccount check = new DuplicationCheckAccount();
				Boolean duplication = check.excecute(user);
				
				if (!duplication) {
					//アカウント作成処理
					CreateAccountLogic createLogic = new CreateAccountLogic();
					Boolean createdstatus = createLogic.excecute(user);
					
					if(createdstatus) {
						HttpSession session = request.getSession();
						session.setAttribute("loginUser", user);
						
						RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/welcome.jsp");
						dispatcher.forward(request, response);
					} else {
						request.setAttribute("errorMsg", "作成に失敗しました。");
						RequestDispatcher dispacher = request.getRequestDispatcher("/WEB-INF/jsp/createAccount.jsp");
						dispacher.forward(request, response);
					}
				} else {
					request.setAttribute("errorMsg", "入力されたユーザIDは既に使われています。");
					RequestDispatcher dispacher = request.getRequestDispatcher("/WEB-INF/jsp/createAccount.jsp");
					dispacher.forward(request, response);
					
				}
			} else {
				request.setAttribute("errorMsg", "パスワードは4桁で入力してください。");
				RequestDispatcher dispacher = request.getRequestDispatcher("/WEB-INF/jsp/createAccount.jsp");
				dispacher.forward(request, response);
			}
			
		} else {
			request.setAttribute("errorMsg", "パスワードが一致しません。");
			RequestDispatcher dispacher = request.getRequestDispatcher("/WEB-INF/jsp/createAccount.jsp");
			dispacher.forward(request, response);
		}
		

	}

}
