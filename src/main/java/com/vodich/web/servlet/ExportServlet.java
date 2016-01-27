package com.vodich.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.vodich.business.ScenarioService;
import com.vodich.business.ScenarioServiceImpl;
import com.vodich.core.bean.Scenario;
import com.vodich.dao.DAOException;



/**
 * Servlet implementation class Export
 */
@WebServlet("/export")
public class ExportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ATT_ERROR_MSG = "error";
	private static final String PARAM_SCENARIO_ID = "id";
	private ScenarioService scenarioService;
	private static ObjectMapper mapper = new ObjectMapper();
	
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExportServlet() {
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
				String scenarioID  = (String)request.getParameter(PARAM_SCENARIO_ID);
		
					Scenario scenario;
					try {
						scenario = scenarioService.load(scenarioID);
					
						ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
				    	String json = ow.writeValueAsString(scenario);
				    	response.setHeader("Content-Type", "application/json");
				    	response.getWriter().println(json);
					} catch (DAOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
