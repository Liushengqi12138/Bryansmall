package lsq.Bryan.web.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lsq.Bryan.domain.Cart;
import lsq.Bryan.domain.User;
import lsq.Bryan.service.BusinessService;
import lsq.Bryan.service.impl.BusinessServiceImpl;

@WebServlet("/client/OrderServlet")
public class OrderServlet extends HttpServlet {
	
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
		String username=user.getUsername();
		String useremail=user.getEmail();
		String content="尊敬的客户"+username+"，您好！";
		try {
			Cart cart = (Cart) request.getSession().getAttribute("cart");
			BusinessService service = new BusinessServiceImpl();
			service.saveOrder(cart, user);
			EmailServlet.SendMail(useremail, "订单已成功生成", content+"您刚刚在Bryansmall平台生成了一个新订单！");
			request.setAttribute("message", content+"您的订单生成成功，请等待收货！");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", content+"您的订单生成失败，请重新购买！");
		}
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}


