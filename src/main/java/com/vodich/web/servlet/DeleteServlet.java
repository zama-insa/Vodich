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
@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ATT_ERROR_MSG = "error";
	private static final String ATT_SCENARIO_ID = "id";
	private ScenarioService scenarioService;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet() {
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

		String scenarioID  = (String)request.getParameter(ATT_SCENARIO_ID);
		try {
			scenarioService.delete(scenarioID);
			
		} catch (DAOException e) {
			request.setAttribute(ATT_ERROR_MSG, "Database error : Delete scenario failed (id " + scenarioID + ")");
			WebUtils.forward(request, response, "default.jsp");
			return;
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		response.sendRedirect("default");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
