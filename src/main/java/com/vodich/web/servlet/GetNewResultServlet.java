package com.vodich.web.servlet;

import java.io.IOException;

import javax.jms.JMSException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vodich.business.ResultService;
import com.vodich.business.ResultServiceImpl;
import com.vodich.core.util.VodichUtils;

/**
 * Servlet implementation class GetNewResultServlet
 */
@WebServlet("/getNewResult")
public class GetNewResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String PARAM_SID = "sid";
	private ResultService resultService;

	@Override
	public void init() throws ServletException {
		resultService = ResultServiceImpl.getInstance();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sid = request.getParameter(PARAM_SID);
		if (VodichUtils.isNullOrEmpty(sid)) {
			response.sendError(400, "Missing required parameter : '" + PARAM_SID + "'");
			return;
		}
		String rid;
		try {
			rid = resultService.actualizeResult(sid);
		} catch (JMSException e) {
			e.printStackTrace();
			return;
		}
		
		response.getWriter().println(rid);
	}

}
