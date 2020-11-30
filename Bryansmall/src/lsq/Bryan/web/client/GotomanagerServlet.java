package lsq.Bryan.web.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lsq.Bryan.domain.User;

@WebServlet("/client/GotomanagerServlet")
public class GotomanagerServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			request.setAttribute("message", "请您先登录！");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			return;
		}
		String userid=user.getId();
		if(userid.length()<5){
			response.sendRedirect(request.getContextPath() + "/manager.jsp");
			return;
		} else {
			request.setAttribute("message", "您没有权限访问！");
		}
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}


