package com.vodich.web.servlet;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.vodich.business.ScenarioService;
import com.vodich.business.ScenarioServiceImpl;
import com.vodich.core.bean.Flow;
import com.vodich.core.bean.Scenario;
import com.vodich.core.util.VodichUtils;
import com.vodich.core.util.WebUtils;
import com.vodich.dao.DAOException;

/**
 * Servlet implementation class ImportServlet
 */
@WebServlet("/import")
@MultipartConfig
public class ImportServlet extends HttpServlet {

	private static final String PARAM_NAME = "Scenario name : ";
	private static final String PARAM_CONSUMER = "Consumer : ";
	private static final String PARAM_PRODUCER = "Producer : ";
	private static final String PARAM_FREQUENCY = "Frequency : ";
	private static final String PARAM_PROCESS_TIME = "ProcessTime : ";
	private static final String PARAM_START_TIME = "Start : ";
	private static final String PARAM_STOP_TIME = "Stop : ";
	private static final String PARAM_MESSAGELOAD = "MessageLoad : ";
	private ArrayList<String> params = new ArrayList<String>();
	private static final String ATT_ERROR_MSG = "error";
	private static final long serialVersionUID = 1L;
	private static Pattern pattern;
	private static Matcher matcher;
	private ScenarioService scenarioService;

	@Override
	public void init() throws ServletException {
		scenarioService = ScenarioServiceImpl.getInstance();
		params.add(PARAM_NAME);
		params.add(PARAM_CONSUMER);
		params.add(PARAM_PRODUCER);
		params.add(PARAM_FREQUENCY);
		params.add(PARAM_PROCESS_TIME);
		params.add(PARAM_START_TIME);
		params.add(PARAM_MESSAGELOAD);

	}

	public Scenario createScenarioFromFile(InputStream fileContent) {
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(fileContent));
		
		Scenario scenario = new Scenario();
		
		List<Flow> flows = new ArrayList<>();
		try {
			String ligne = "";
			int j = 0;
			while (((ligne = reader.readLine()) != null) && j < params.size()) {
				Flow flow = new Flow();
				System.out.println(ligne);
				System.out.println(j);
				pattern = Pattern.compile(params.get(j));
				matcher = pattern.matcher(ligne);
				while (matcher.find()) {
					Pattern p = Pattern.compile(":");
					String[] val = p.split(ligne);
					System.out.println("Valeurs récup " + val[1]);
					if(params.get(j) == PARAM_NAME){
						scenario.setName(val[1]);
						j++;
					}
					else if(params.get(j) == PARAM_CONSUMER) {
                    flow.setConsumer(val[1]);
                    j++;
					}
					else if (params.get(j) == PARAM_PRODUCER){
						flow.setProducer(val[1]);
						j++;
					}
					else if (params.get(j) == PARAM_FREQUENCY ){
						flow.setProducer(val[1]);
					}
					else if (params.get(j) == PARAM_PROCESS_TIME){
						flow.setProcessTime(Double.parseDouble(val[1]));
					}
					else if (params.get(j) == PARAM_START_TIME){
						Double d = Double.parseDouble(val[1]);
						flow.setStart( d.intValue() );
					}
					else if (params.get(j) == PARAM_STOP_TIME){
						flow.setStop(Integer.parseInt(val[1]));
					}
					else if (params.get(j) == PARAM_MESSAGELOAD) {
						j = 0;
						flow.setMessageLoad(Integer.parseInt(val[1]));
						flows.add(flow);
					} else {
						j++;
					}
				}
			}
			scenario.setFlows(flows);	
			System.out.println(scenario.getName());
			System.out.println("Taille de la liste des flots"+scenario.getFlows().size());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("2"+scenario.getName());
		return scenario;
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ImportServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		WebUtils.forward(request, response, "import.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String description = request.getParameter("description"); // Retrieves
																	// <input
																	// type="text"
																	// name="description">
		Part filePart = request.getPart("file"); // Retrieves <input type="file"
													// name="file">
		String fileName = filePart.getSubmittedFileName();
		InputStream fileContent = filePart.getInputStream();
		Scenario scenario  = createScenarioFromFile(fileContent);
		
		if (VodichUtils.isNullOrEmpty(scenario.getName())) {
			request.setAttribute(ATT_ERROR_MSG, "The scenario name is not specified");
			WebUtils.forward(request, response, "create-scenario.jsp");
			return;
		}
		
		try {
			if (scenarioService.loadByName(scenario.getName()) != null) {
				request.setAttribute(ATT_ERROR_MSG, "Scenario name '" + scenario.getName()+ "' already exists");
				WebUtils.forward(request, response, "create-scenario.jsp");
				return;
			}
		} catch (DAOException e) {
			request.setAttribute(ATT_ERROR_MSG, "Database error : Check existing scenario failed");
			WebUtils.forward(request, response, "create-scenario.jsp");
			e.printStackTrace();
			return;
		}
		
		try {
			scenarioService.save(scenario);
		} catch (DAOException e) {
			request.setAttribute(ATT_ERROR_MSG, "Database error : Save scenario failed");
			WebUtils.forward(request, response, "create-scenario.jsp");
			return;
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		response.sendRedirect("default");

	}

}
