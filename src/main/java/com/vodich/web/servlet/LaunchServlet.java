package com.vodich.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vodich.business.ScenarioService;
import com.vodich.business.ScenarioServiceImpl;
import com.vodich.core.bean.Scenario;
import com.vodich.core.util.WebUtils;
import com.vodich.dao.DAOException;

/**
 * Servlet implementation class LaunchServlet
 */
@WebServlet("/launch")
public class LaunchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ATT_ERROR_MSG = "error";
	private static final String ATT_SCENARIO_ID = "id";
	private ScenarioService scenarioService;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LaunchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
	public void init() throws ServletException {
		scenarioService = ScenarioServiceImpl.getInstance();
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Scenario scenario = new Scenario();

		String scenarioID  = (String)request.getParameter(ATT_SCENARIO_ID);
		try {
			scenario =  scenarioService.load(scenarioID);
			request.setAttribute("scenario", scenario);
		} catch (DAOException e) {
			request.setAttribute(ATT_ERROR_MSG, "Database error : Save scenario failed");
			WebUtils.forward(request, response, "create-scenario.jsp");
			
		}
		
		WebUtils.forward(request, response, "launch.jsp"); 
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
