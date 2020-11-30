package lsq.Bryan.web.manager;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lsq.Bryan.domain.Category;
import lsq.Bryan.domain.User;
import lsq.Bryan.factory.ServiceFactory;
import lsq.Bryan.service.BusinessService;
import lsq.Bryan.utils.SecurityException;
import lsq.Bryan.utils.WebUtils;

@WebServlet("/manager/CategoryServlet")
public class CategoryServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String changeid="0";

	//private BusinessService service = new BusinessServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		if ("add".equals(method)) {
			add(request, response);
		}
		if ("getAll".equals(method)) {
			getAll(request, response);
		}
		if ("dele".equals(method)) {
			dele(request, response);
		}
		if ("change".equals(method)) {
			change(request, response);
		}
		if ("changec".equals(method)) {
			changec(request, response);
		}
	}

	private void getAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//使用ServiceFactory工厂创建service时，需要传递一个用户过去
		BusinessService service = ServiceFactory.getInstance().createService((User) request.getSession().getAttribute("user"));
		try {
			List<Category> list = service.getAllCategory();
			request.setAttribute("categories", list);
			request.getRequestDispatcher("/manager/listcategory.jsp").forward(request, response);
		} catch (Exception e) {
			if (e.getCause() instanceof SecurityException) {
				request.setAttribute("message", e.getCause().getMessage());
				request.getRequestDispatcher("/message.jsp").forward(request, response);
			}
		}
		
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BusinessService service = ServiceFactory.getInstance().createService((User) request.getSession().getAttribute("user"));
		
		try {
			Category c = WebUtils.request2Bean(request, Category.class);
			c.setId(UUID.randomUUID().toString());
			service.addCategory(c);
			
			request.setAttribute("message", "添加成功！！");
		} catch (Exception e) {
			if (e.getCause() instanceof SecurityException) {
				request.setAttribute("message", e.getCause().getMessage());
			} else {
				e.printStackTrace();
				request.setAttribute("message", "添加失败！！");
			}
		}
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}
	
	private void dele(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BusinessService service = ServiceFactory.getInstance().createService((User) request.getSession().getAttribute("user"));
		
		try {
			
			String id = request.getParameter("id");
			service.deleteCategory(id);
			
			request.setAttribute("message", "删除成功！！");
		} catch (Exception e) {
			if (e.getCause() instanceof SecurityException) {
				request.setAttribute("message", e.getCause().getMessage());
			} else {
				e.printStackTrace();
				request.setAttribute("message", "删除失败！！");
			}
		}
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}
	
	private void change(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BusinessService service = ServiceFactory.getInstance().createService((User) request.getSession().getAttribute("user"));
		
		try {
			String id = request.getParameter("id");
			service.deleteCategory(id);
			changeid=id;
			response.sendRedirect(request.getContextPath() + "/manager/changecategory.jsp");
			return;
		} catch (Exception e) {
			if (e.getCause() instanceof SecurityException) {
				request.setAttribute("message", e.getCause().getMessage());
			} else {
				e.printStackTrace();
				request.setAttribute("message", "添加失败！！");
			}
		}
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}
	
	private void changec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BusinessService service = ServiceFactory.getInstance().createService((User) request.getSession().getAttribute("user"));
		
		try {
			Category c = WebUtils.request2Bean(request, Category.class);
			c.setId(changeid);
			service.addCategory(c);
			
			request.setAttribute("message", "修改成功！！");
		} catch (Exception e) {
			if (e.getCause() instanceof SecurityException) {
				request.setAttribute("message", e.getCause().getMessage());
			} else {
				e.printStackTrace();
				request.setAttribute("message", "修改失败！！");
			}
		}
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
