package com.vodich.web.servlet;

import java.io.IOException;
import java.io.PrintStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vodich.business.CommonService;
import com.vodich.business.CommonServiceImpl;
import com.vodich.business.ScenarioService;
import com.vodich.business.ScenarioServiceImpl;
import com.vodich.core.bean.Flow;
import com.vodich.core.bean.Scenario;
import com.vodich.core.util.VodichUtils;
import com.vodich.dao.DAOException;
import com.vodich.dao.ScenarioDAO;
import com.vodich.dao.ScenarioDAOImpl;
import com.vodich.core.util.WebUtils;

/**
 * Servlet implementation class CreateScenarioServlet
 */
@WebServlet("/create")
public class CreateScenarioServlet extends HttpServlet {
	private static final String PARAM_NAME = "name";
	private static final String PARAM_STOP_TIME = "stoptime";
	private static final String PARAM_START_TIME = "starttime";
	private static final String PARAM_FREQUENCY = "frequency";
	private static final String PARAM_PROCESS_TIME = "processtime";
	private static final String PARAM_PRODUCER = "producer";
	private static final String PARAM_CONSUMER = "consumer";

	private static final String ATT_ERROR_MSG = "error";
	private static final String ATT_FLOW_COUNT = "flowcount";
	// attribute that enables parameters saving
	private static final String ATT_MAP = "map";
	// parameters from business service
	private static final String ATT_PRODUCER_NUM = "PRODUCER_NUM";
	private static final String ATT_CONSUMER_NUM = "CONSUMER_NUM";
	private static final long serialVersionUID = 1L;
	private static final String ATT_CURRENT_FLOW_VIEW = "currentflowview";
	private ScenarioService scenarioService;

	@Override
	public void init() throws ServletException {
		scenarioService = ScenarioServiceImpl.getInstance();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().setAttribute(ATT_CONSUMER_NUM, CommonServiceImpl.getInstance().getConsumerNum());
		request.getSession().setAttribute(ATT_PRODUCER_NUM, CommonServiceImpl.getInstance().getConsumerNum());
		Integer flowCount = (Integer) request.getSession().getAttribute(ATT_FLOW_COUNT);
		if (flowCount == null) flowCount = 1;
		saveUserInputs(request, flowCount);
		request.getSession().setAttribute(ATT_FLOW_COUNT, flowCount);
		request.getSession().setAttribute(ATT_CURRENT_FLOW_VIEW, 1);
		WebUtils.forward(request, response, "create-scenario.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = (String) request.getParameter("action");
		if (VodichUtils.isNullOrEmpty(action)) {
			actionCreate(request, response);
		} else if ("addFlow".equals(action)) {
			actionAddFlow(request,response);
		} else if ("removeFlow".equals(action)) {
			actionRemoveFlow(request,response);
		}
	}

	private void actionRemoveFlow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int flowCount = Integer.parseInt(request.getParameter(ATT_FLOW_COUNT));
		if (flowCount > 1) {
			flowCount -= 1;
			request.getSession().setAttribute(ATT_FLOW_COUNT, flowCount);
		}
		saveUserInputs(request, flowCount);
		WebUtils.forward(request, response, "create-scenario.jsp");
	}

	private void actionAddFlow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int flowCount = Integer.parseInt(request.getParameter(ATT_FLOW_COUNT));
		flowCount += 1;
		saveUserInputs(request, flowCount);
		request.getSession().setAttribute(ATT_FLOW_COUNT, flowCount);
		WebUtils.forward(request, response, "create-scenario.jsp");
	}

	private void actionCreate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int flowCount = Integer.parseInt(request.getParameter(ATT_FLOW_COUNT));
		saveUserInputs(request, flowCount);
		String scenarioName = request.getParameter(PARAM_NAME);
		if (VodichUtils.isNullOrEmpty(scenarioName)) {
			request.setAttribute(ATT_ERROR_MSG, "The scenario name is not specified");
			WebUtils.forward(request, response, "create-scenario.jsp");
			return;
		}
		List<Flow> flows = new ArrayList<>();
		for (int i = 1; i <= flowCount; i++) {
			Flow flow = new Flow();
			flow.setConsumer(request.getParameter(PARAM_CONSUMER + i));
			flow.setProducer(request.getParameter(PARAM_PRODUCER + i));
			try {
				flow.setProcessTime(Double.parseDouble(request.getParameter(PARAM_PROCESS_TIME + i)));
				flow.setFrequency(Double.parseDouble(request.getParameter(PARAM_FREQUENCY + i)));
				flow.setStart(Integer.parseInt(request.getParameter(PARAM_START_TIME + i)));
				flow.setStop(Integer.parseInt(request.getParameter(PARAM_STOP_TIME + i)));
			} catch (NumberFormatException|NullPointerException e) {
				request.setAttribute(ATT_ERROR_MSG, "Empty or invalid format on Flow" + i + ": " + e.getMessage());
				WebUtils.forward(request, response, "create-scenario.jsp");
				return;
			}
			flows.add(flow);
		}
		Scenario scenario = new Scenario();
		scenario.setId(scenarioName);
		scenario.setFlows(flows);
		try {
			scenarioService.save(scenario);
		} catch (DAOException e) {
			request.setAttribute(ATT_ERROR_MSG, "Database error : Save scenario failed");
			WebUtils.forward(request, response, "create-scenario.jsp");
			return;
		}
		response.sendRedirect("default");
	}
	
	// Save parameters as request attributes to keep the form parameters
	private void saveUserInputs(HttpServletRequest request, int flowCount) {
		request.setAttribute(PARAM_NAME, request.getParameter(PARAM_NAME));
		request.setAttribute(ATT_FLOW_COUNT, flowCount);
		Map<String, List<Object>> map= new HashMap();
		map.put(PARAM_CONSUMER, new ArrayList<Object>());
		map.put(PARAM_PRODUCER, new ArrayList<Object>());
		map.put(PARAM_PROCESS_TIME, new ArrayList<Object>());
		map.put(PARAM_FREQUENCY, new ArrayList<Object>());
		map.put(PARAM_START_TIME, new ArrayList<Object>());
		map.put(PARAM_STOP_TIME, new ArrayList<Object>());
		for (int i = 1; i <= flowCount; i++){
			map.get(PARAM_CONSUMER).add(request.getParameter(PARAM_CONSUMER + i));
			map.get(PARAM_PRODUCER).add(request.getParameter(PARAM_PRODUCER + i));
			map.get(PARAM_PROCESS_TIME).add(request.getParameter(PARAM_PROCESS_TIME + i));
			map.get(PARAM_FREQUENCY).add(request.getParameter(PARAM_FREQUENCY + i));
			map.get(PARAM_START_TIME).add(request.getParameter(PARAM_START_TIME + i));
			map.get(PARAM_STOP_TIME).add(request.getParameter(PARAM_STOP_TIME + i));
		}
		request.getSession().setAttribute(ATT_MAP, map);
	}

}
