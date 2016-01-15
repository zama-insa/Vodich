package com.vodich.web.servlet;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;



public class ImportServletTest {

	private HttpServletRequest request;   
	private HttpServletResponse response;  
	ImportServlet servlet;
	
    @Before
    public void setUp() {
    	servlet = new ImportServlet();
        request = mock(HttpServletRequest.class);   
        response = mock(HttpServletResponse.class); 
    }
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
