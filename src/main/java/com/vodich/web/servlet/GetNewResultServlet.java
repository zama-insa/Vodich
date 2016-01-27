package com.vodich.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vodich.business.ResultService;
import com.vodich.business.ResultServiceImpl;
import com.vodich.business.ScenarioService;
import com.vodich.business.ScenarioServiceImpl;
import com.vodich.core.bean.Scenario;
import com.vodich.core.util.VodichUtils;
import com.vodich.dao.DAOException;

/**
 * Servlet implementation class GetNewResultServlet
 */
@WebServlet("/getNewResult")
public class GetNewResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String PARAM_SID = "sid";
	private ResultService resultService;
	private ScenarioService scenarioService;

	@Override
	public void init() throws ServletException {
		scenarioService = ScenarioServiceImpl.getInstance();
		resultService = ResultServiceImpl.getInstance();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sid = request.getParameter(PARAM_SID);
		if (VodichUtils.isNullOrEmpty(sid)) {
			response.sendError(400, "Missing required parameter : '" + PARAM_SID + "'");
			return;
		}
		String rid;
		Scenario scenario;
		try {
			scenario = scenarioService.load(sid);
		} catch (DAOException e1) {
			response.sendError(400, "No scenario found with id='" + sid + "'");
			return;
		}
		try {
			rid = resultService.actualizeResult(scenario);
		} catch (Throwable e) {
			response.sendError(400,e.getMessage());
			return;
		}
		response.getWriter().println(rid);
	}

}
