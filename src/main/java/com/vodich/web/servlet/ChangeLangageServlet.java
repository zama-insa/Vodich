package com.vodich.web.servlet;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;

import com.vodich.core.util.VodichUtils;

/**
 * Servlet implementation class ChangeLangage
 */
@WebServlet("/changelangage")
public class ChangeLangageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String PARAM_REDIRECT_URL = "redirectURL";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChangeLangageServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		Locale locale = (Locale) Config.get(session, Config.FMT_LOCALE);
		if ( request.getParameter("language") != null) {
			locale = new Locale(request.getParameter("language"));
		}
		Config.set(session, Config.FMT_LOCALE, locale);
		String redirectURL = request.getParameter(PARAM_REDIRECT_URL);
		request.getSession().setAttribute("language", locale.toString());
		if (!VodichUtils.isNullOrEmpty(redirectURL)) {
			response.sendRedirect(redirectURL);
		} else {
			response.sendRedirect("default");
		}
		//WebUtils.forward(request, response, "create-scenario.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}
