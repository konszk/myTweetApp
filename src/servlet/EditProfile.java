package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import model.UpdateAccount;
import model.User;

/**
 * Servlet implementation class EditProfile
 */
@WebServlet("/EditProfile")
@MultipartConfig(location="/tmp", maxFileSize=1048576)
public class EditProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditProfile() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// プロフィール編集画面へ遷移
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/profile.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String new_displayname = request.getParameter("new_displayname");
		
		// プロフィール画像を取得・アップロード
		Part part = request.getPart("file");
		String iconpath = null;
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loginUser");
		
		// 画像が選択されているかのチェック
		if (part.getSize() != 0) {
	        String name = this.getFileName(part);
	        part.write(getServletContext().getRealPath("/image/icon") + "/" + name);
	        iconpath = "./image/icon/" + name;
		} else {
			iconpath = user.getIconPath();
		}

		UpdateAccount updateAccountLogic = new UpdateAccount();
		boolean updateStatus = updateAccountLogic.execute(user, new_displayname, iconpath);
		
		if(updateStatus) {
			request.setAttribute("Msg", "変更が保存されました。");
			user.setDisplayName(new_displayname);
			user.setIconPath(iconpath);
			session.setAttribute("loginUser", user);
		} else {
			request.setAttribute("errorMsg", "変更が失敗しました。");
		}
		
		RequestDispatcher dispacher = request.getRequestDispatcher("/WEB-INF/jsp/profile.jsp");
		dispacher.forward(request, response);

	}
	
    private String getFileName(Part part) {
        String name = null;
        for (String dispotion : part.getHeader("Content-Disposition").split(";")) {
            if (dispotion.trim().startsWith("filename")) {
                name = dispotion.substring(dispotion.indexOf("=") + 1).replace("\"", "").trim();
                name = name.substring(name.lastIndexOf("\\") + 1);
                break;
            }
        }
        return name;
    }

}
