package j.haianh.controllers;

import java.io.IOException;

import j.haianh.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/views/waiting")
public class WaitingController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if (session != null && session.getAttribute("account") != null) {
			User u = (User) session.getAttribute("account");
			req.setAttribute("username", u.getUsername());
			if (u.getRoleid() == 2) {
				resp.sendRedirect(req.getContextPath() + "/views/admin/home.jsp");
			} else if (u.getRoleid() == 3) {
				resp.sendRedirect(req.getContextPath() + "/views/manager/home.jsp");
			} else {
				resp.sendRedirect(req.getContextPath() + "/views/home.jsp");
			}
		} else {
			resp.sendRedirect(req.getContextPath() + "/views/login.jsp");
		}
	}
}
