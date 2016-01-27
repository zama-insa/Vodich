package com.vodich.web.servlet;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vodich.business.ScenarioService;
import com.vodich.core.bean.Flow;
import com.vodich.core.bean.Scenario;
import com.vodich.core.util.VodichUtils;
import com.vodich.core.util.WebUtils;
import com.vodich.dao.DAOException;

/**
 * Servlet implementation class ImportJSONServlet
 */
@WebServlet("/importjson")
public class ImportJSONServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ScenarioService scenarioService;
	private static final String ATT_ERROR_MSG = "error";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ImportJSONServlet() {
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
		WebUtils.forward(request, response, "importjson.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String description = request.getParameter("description"); // Retrieves
		// <input
		// type="text"
		// name="description">
		// System.out.println("Description"+description);
		String contentType = request.getContentType();
		// System.out.println("ContentType"+contentType);
		Part filePart = request.getPart("file"); // Retrieves <input type="file"
		// name="file">
		String fileName = filePart.getSubmittedFileName();
		InputStream fileContent = filePart.getInputStream();
		Scenario scenario = saveObjectFromUploadedFile(fileContent);
		if (VodichUtils.isNullOrEmpty(scenario.getName())) {
			request.setAttribute(ATT_ERROR_MSG, "The scenario name is not specified");
			WebUtils.forward(request, response, "default.jsp");
			return;
		}
		
		try {
			if (scenarioService.loadByName(scenario.getName()) != null) {
				request.setAttribute(ATT_ERROR_MSG, "Scenario name '" + scenario.getName()+ "' already exists");
				WebUtils.forward(request, response, "default.jsp");
				return;
			}
		} catch (DAOException e) {
			request.setAttribute(ATT_ERROR_MSG, "Database error : Check existing scenario failed");
			WebUtils.forward(request, response, "importjson.jsp");
			e.printStackTrace();
			return;
		}
		
		try {
			scenarioService.save(scenario);
		} catch (DAOException e) {
			request.setAttribute(ATT_ERROR_MSG, "Database error : Save scenario failed");
			WebUtils.forward(request, response, "importjson.jsp");
			return;
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		response.sendRedirect("default");

	
	}
	
	private Scenario saveObjectFromUploadedFile(InputStream fileContent) {
   Scenario scenario = new Scenario();
   String thisLine ="";
   String file ="";
	BufferedReader reader = new BufferedReader(new InputStreamReader(fileContent));
	 BufferedReader br;
	try {
		br = new BufferedReader(new FileReader("/home/febroshka/git/Vodich/export678"));
		  while ((thisLine = br.readLine()) != null) {
		    	// System.out.println(thisLine);
		    	 file = file+thisLine+"\n";
		     }
		  
    return scenario = new ObjectMapper().readValue(file, Scenario.class);
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return scenario;
   
    
	
	}
		


}
