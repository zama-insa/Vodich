package com.vodich.core.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


public class WebUtils {

	public static void forward(ServletRequest request, ServletResponse response, String jspPage)
			throws ServletException, IOException {
		request.getServletContext()
		.getRequestDispatcher("/WEB-INF/jsp/" + jspPage)
		.forward(request,response);
	}
}
