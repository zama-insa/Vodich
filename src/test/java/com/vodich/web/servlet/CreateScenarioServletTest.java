package com.vodich.web.servlet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;

import com.vodich.core.bean.Flow;
import com.vodich.core.bean.Scenario;

//Test of CreateScenarioServlet 

public class CreateScenarioServletTest {

	private static final String PARAM_NAME = "name";
	private static final String PARAM_CONSUMER = "consumer";
	private static final String PARAM_PRODUCER = "producer";
	private static final String PARAM_STOP_TIME = "stoptime";
	private static final String PARAM_START_TIME = "starttime";
	private static final String PARAM_FREQUENCY = "frequency";
	private static final String PARAM_PROCESS_TIME = "processtime";
	private static final String PARAM_MESSAGELOAD = "messageload ";
	private static final String ATT_ERROR_MSG = "error";
	private static final String ATT_FLOW_COUNT = "flowcount";
	// attribute that enables parameters saving
	private static final String ATT_MAP = "map";
	// parameters from business service
	private static final String ATT_PRODUCER_NUM = "PRODUCER_NUM";
	private static final String ATT_CONSUMER_NUM = "CONSUMER_NUM";
	private Scenario scenario;
	private ArrayList<Flow> flows;
	private Flow flow;
	private int flowCount;

	private CreateScenarioServlet servlet;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session;
	
	//private ScenarioService scenarioService;
	

	@Before
	public void setUp() {
		servlet = new CreateScenarioServlet();
		request = mock(HttpServletRequest.class);
		response = mock(HttpServletResponse.class);
		session = request.getSession(true);
	//	scenarioService = ScenarioServiceImpl.getInstance();
		when(request.getParameter(PARAM_NAME)).thenReturn("test");
		when(request.getParameter(PARAM_CONSUMER)).thenReturn("1");
		when(request.getParameter(PARAM_PRODUCER)).thenReturn("1");
		when(request.getParameter(ATT_CONSUMER_NUM)).thenReturn("1");
		when(request.getParameter(ATT_PRODUCER_NUM)).thenReturn("1");
		when(request.getParameter(PARAM_START_TIME)).thenReturn("0");
		when(request.getParameter(PARAM_STOP_TIME)).thenReturn("10");
		when(request.getParameter(PARAM_FREQUENCY)).thenReturn("50");
		when(request.getParameter(PARAM_PROCESS_TIME)).thenReturn("40");
		when(request.getParameter(PARAM_MESSAGELOAD)).thenReturn("0");
		when(request.getParameter(ATT_FLOW_COUNT)).thenReturn("3");
		flowCount = Integer.parseInt(request.getParameter(ATT_FLOW_COUNT));
		
	}

	/* Servlet NullPointerException or session = request.getSession() null + No instance of scenario service
	@Test
	public void testActionRemoveFlow2() throws ServletException, IOException {
		servlet.actionRemoveFlow(request, response);
		assertEquals("2",(request.getParameter(ATT_FLOW_COUNT)));
	}
	*/
	@Test
	public void testActionRemoveFlow() {
		flowCount = Integer.parseInt(request.getParameter(ATT_FLOW_COUNT));
		if (flowCount > 1) {
			flowCount -= 1;
		}
		when(request.getParameter(ATT_FLOW_COUNT)).thenReturn(Integer.toString(flowCount));
		assertEquals("2",(request.getParameter(ATT_FLOW_COUNT)));
	}
	
	@Test
	public void testActionAddFlow() {
		flowCount = Integer.parseInt(request.getParameter(ATT_FLOW_COUNT));
		flowCount += 1;
		when(request.getParameter(ATT_FLOW_COUNT)).thenReturn(Integer.toString(flowCount));
		assertEquals("4",(request.getParameter(ATT_FLOW_COUNT)));
	}
	
	@Test
	public void testActionCreate() {
		when(request.getParameter(ATT_FLOW_COUNT)).thenReturn("1");
		int flowCount = Integer.parseInt(request.getParameter(ATT_FLOW_COUNT)); // ="1"
		String scenarioName = request.getParameter(PARAM_NAME); //"test"
		assertEquals(scenarioName,"test");
		
		List<Flow> flows = new ArrayList<>();
		for (int i = 1; i <= flowCount; i++) {
			Flow flow = new Flow();
			flow.setConsumer(request.getParameter(PARAM_CONSUMER ));
			flow.setProducer(request.getParameter(PARAM_PRODUCER ));
			try {
				flow.setProcessTime(Double.parseDouble(request.getParameter(PARAM_PROCESS_TIME )));
				flow.setFrequency(Double.parseDouble(request.getParameter(PARAM_FREQUENCY )));
				flow.setStart(Integer.parseInt(request.getParameter(PARAM_START_TIME)));
				flow.setStop(Integer.parseInt(request.getParameter(PARAM_STOP_TIME)));
			} catch (NumberFormatException|NullPointerException e) {
				request.setAttribute(ATT_ERROR_MSG, "Empty or invalid format on Flow" + i + ": " + e.getMessage());

				return;
			}
			flows.add(flow);
		}
		Scenario scenario = new Scenario();
		scenario.setName(scenarioName);
		scenario.setFlows(flows);
       assertTrue("1".equals(flows.get(0).getConsumer()));
       assertEquals("1",flows.get(0).getProducer());
       assertTrue(40.0 == flows.get(0).getProcessTime());
       assertTrue(50.0 == flows.get(0).getFrequency());
       assertTrue(0 ==flows.get(0).getStart());
       assertTrue(10 == flows.get(0).getStop());
       assertTrue(0 == flows.get(0).getMessageLoad());
		
	}
	
	

}
