package com.vodich.web.servlet;

import java.io.IOException;
import java.io.PrintStream;
import java.text.ParseException;
import java.util.ArrayList;
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
import com.vodich.core.bean.Flow;
import com.vodich.core.bean.Scenario;
import com.vodich.dao.DAOException;
import com.vodich.dao.ScenarioDAO;
import com.vodich.dao.ScenarioDAOImpl;
import com.vodich.web.util.WebUtils;

/**
 * Servlet implementation class CreateScenarioServlet
 */
@WebServlet("/create")
public class CreateScenarioServlet extends HttpServlet {
	private static final String ATT_ERROR_MSG = "error";
	private static final String PARAM_NAME = "name";
	private static final String PARAM_FLOW_COUNT = "flowcount";
	private static final String PARAM_STOP_TIME = "stoptime";
	private static final String PARAM_START_TIME = "starttime";
	private static final String PARAM_FREQUENCY = "frequency";
	private static final String PARAM_PROCESS_TIME = "processtime";
	private static final String PARAM_PRODUCER = "producer";
	private static final String PARAM_CONSUMER = "consumer";
	// attribute that enables parameters saving
	private static final String ATT_MAP = "map";
	// parameters from business service
	private static final String ATT_PRODUCER_NUM = "PRODUCER_NUM";
	private static final String ATT_CONSUMER_NUM = "CONSUMER_NUM";
	private static final long serialVersionUID = 1L;
	private ScenarioDAO scenarioDao;

	@Override
	public void init() throws ServletException {
		scenarioDao = new ScenarioDAOImpl();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute(ATT_MAP, null);
		request.setAttribute(ATT_CONSUMER_NUM, CommonServiceImpl.getInstance().getConsumerNum());
		request.setAttribute(ATT_PRODUCER_NUM, CommonServiceImpl.getInstance().getConsumerNum());
		request.setAttribute(PARAM_FLOW_COUNT, 1);
		WebUtils.forward(request, response, "create-scenario.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute(ATT_CONSUMER_NUM, CommonServiceImpl.getInstance().getConsumerNum());
		request.setAttribute(ATT_PRODUCER_NUM, CommonServiceImpl.getInstance().getConsumerNum());
		
		int flowCount = Integer.parseInt(request.getParameter(PARAM_FLOW_COUNT));
		saveAttributes(request, flowCount);
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
		scenario.setId(request.getParameter(PARAM_NAME));
		try {
			scenarioDao.save(scenario);
		} catch (DAOException e) {
			request.setAttribute(ATT_ERROR_MSG, e.getErrorMessage() + "<br/>" + e.getCause().getMessage());
			WebUtils.forward(request, response, "create-scenario.jsp");
			return;
		}
		// TODO : forward to the page with list of scenarii
		WebUtils.forward(request, response, "post-create-scenario.jsp");
	}
	
	// Save parameters as request attributes to keep the form parameters
	private void saveAttributes(HttpServletRequest request, int flowCount) {
		request.setAttribute(PARAM_NAME, request.getParameter(PARAM_NAME));
		request.setAttribute(PARAM_FLOW_COUNT, flowCount);
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
		request.setAttribute(ATT_MAP, map);
	}

}
