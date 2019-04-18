package com.ssafy.config;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("*.do")
public class WebConfigFilter implements Filter {

	public void init(FilterConfig fConfig) throws ServletException {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();

		String action = req.getParameter("action");
		String url = "main.do?action=";

		if ("login".equals(action) || "signUp".equals(action) || "findPw".equals(action) || "yourPwHere".equals(action)) {
			switch (action) {
				case "login":
				case "signUp":
				case "findPw":
				case "yourPwHere":
					req.getRequestDispatcher(url + action).forward(req, res); break;
			}
			return;
		} else if (session.getAttribute("userId") == null) {
			System.out.println("Session Is NULL : " + session.getAttribute("userId"));
			res.sendRedirect("login.jsp");
			return;
		} else {
			chain.doFilter(req, res);
			return;
		}
	}

	public void destroy() {

	}

}
