package j.haianh.filter;

import java.io.IOException;

import j.haianh.entity.User;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/admin1/*")
public class AdminFilter implements Filter {
	
	@Override
	public void destroy() {
	
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest) request;
		HttpServletResponse resp=(HttpServletResponse) response;
		HttpSession session= req.getSession();
		Object obj=session.getAttribute("account");
		User user=(User) obj;
		
		if(user !=null && user.getRoleid()==2)
		{
			chain.doFilter(request, response);
			return;
		}
		else
		{
			resp.sendRedirect(req.getContextPath()+ "/views/logout");
		}
		
	}
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

}
