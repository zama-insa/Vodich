package com.vodich.web.servlet;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;

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

	
	private HttpServlet servlet; 
	private HttpServletRequest request;   
    private HttpServletResponse response;   

    @Before
    public void setUp() {
        servlet = new CreateScenarioServlet();
        request = mock(HttpServletRequest.class);   
        response = mock(HttpServletResponse.class); 
    }
	@Test
	public void test(){
    when(request.getParameter(PARAM_NAME)).thenReturn("42");
	when(request.getParameter(PARAM_CONSUMER)).thenReturn("1");
	when(request.getParameter(PARAM_PRODUCER)).thenReturn("1");
	when(request.getParameter(PARAM_START_TIME)).thenReturn("30");
	when(request.getParameter(PARAM_STOP_TIME)).thenReturn("44");
	when(request.getParameter(PARAM_FREQUENCY)).thenReturn("50");
	when(request.getParameter(PARAM_PROCESS_TIME)).thenReturn("60");
	when(request.getParameter(PARAM_MESSAGELOAD)).thenReturn("60");
	}
	
	@Test
	public void testInit() {
		fail("Not yet implemented");
	}

	@Test
	public void testDoGetHttpServletRequestHttpServletResponse() {
		fail("Not yet implemented");
	}

	@Test
	public void testDoPostHttpServletRequestHttpServletResponse() {
		fail("Not yet implemented");
	}

}
