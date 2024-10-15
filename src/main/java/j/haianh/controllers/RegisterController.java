package j.haianh.controllers;

import java.io.IOException;

import j.haianh.entity.User;
import j.haianh.services.userService;
import j.haianh.services.impl.userServiceImpl;
import j.haianh.util.Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/views/register")
public class RegisterController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session != null && session.getAttribute("username") != null) {
			User u = (User) session.getAttribute("account");
			req.setAttribute("username", u.getUsername());
			if (u.getRoleid() == 2) {
				resp.sendRedirect(req.getContextPath() + "/views/admin/home.jsp");
			} else if (u.getRoleid() == 3) {
				resp.sendRedirect(req.getContextPath() + "/views/manager/home.jsp");
			} else {
				resp.sendRedirect(req.getContextPath() + "/views/home.jsp");
			}
			
		}
		
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("username")) {
					session = req.getSession(true);
					session.setAttribute("username", cookie.getValue());
					resp.sendRedirect(req.getContextPath() + "/views/waiting");
					return;
				}
			}
		}
		req.getRequestDispatcher(Constant.Path.REGISTER).forward(req, resp);
	}

	@SuppressWarnings("static-access")
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		String username = req.getParameter("username");
		String email = req.getParameter("email");
		String fullname = req.getParameter("fullname");
		String phone = req.getParameter("phone");
		String password = req.getParameter("password");
		String images = req.getParameter("images");
		User user=new User(0, username, 0, false, false, email, phone, fullname, password, images);
		userService service = new userServiceImpl();
		String alertMsg = "";

		if (service.checkExistEmail(email)) {
			alertMsg = "Email đã tồn tại!";
			req.setAttribute("alert", alertMsg);
			req.getRequestDispatcher(Constant.Path.REGISTER).forward(req, resp);
			return;
		}
		if (service.checkExistUsername(username)) {
			alertMsg = "Tài khoản đã tồn tại!";
			req.setAttribute("alert", alertMsg);
			req.getRequestDispatcher(Constant.Path.REGISTER).forward(req, resp);
			return;
		}
		
		
		HttpSession session=req.getSession();
		session.setAttribute("account", user);

		boolean isSuccess = service.register(username, password, email, fullname, phone, images);
		if (isSuccess) {
			// SendMail sm = new SendMail();
			// sm.sendMail(email, "Shopping.iotstar.vn", "Welcome to Shopping. Please
			// Loginto use service. Thanks !");
			req.setAttribute("alert", alertMsg);
			resp.sendRedirect(req.getContextPath() + "/views/login.jsp");
		} else {
			alertMsg = "System error!";
			req.setAttribute("alert", alertMsg);
			req.getRequestDispatcher(Constant.Path.REGISTER).forward(req, resp);
		}
	}

}
