package com.vodich.web.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Locale;

import org.junit.Before;
import org.junit.Test;

public class ChangeLangageServletTest {
	
	HttpServletRequest request;
	HttpServletResponse response;
	HttpSession session;
	
	@Before
	public void SetUp(){
		request = mock(HttpServletRequest.class);
		response = mock(HttpServletResponse.class);
		session = request.getSession(true);
	}

	
	@Test 
	public void testServletEnglish() throws Exception{
		
		Locale locale = (Locale) Config.get(session, Config.FMT_LOCALE);
		when(request.getParameter("language")).thenReturn("en");
		//System.out.println("Mise de la langue en anglais);
		locale = new Locale(request.getParameter("language"));
		//Config.set(session, Config.FMT_LOCALE, locale);
		assertEquals(locale.getLanguage(),new Locale("en").getLanguage());	
	}
        
        @Test 
    	public void testServletFrench() throws Exception{
        	Locale locale2 = (Locale) Config.get(session, Config.FMT_LOCALE);
        	//System.out.println("Mise de la langue en français);
    		when(request.getParameter("language")).thenReturn("fr");
    		locale2 = new Locale(request.getParameter("language"));
           // Config.set(session, Config.FMT_LOCALE, locale);
            assertEquals(locale2.getLanguage(),new Locale("fr").getLanguage());
	
	}
	
	

}
