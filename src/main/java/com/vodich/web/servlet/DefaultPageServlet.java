package com.vodich.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.elasticsearch.bootstrap.Elasticsearch;

import com.vodich.business.ScenarioService;
import com.vodich.business.ScenarioServiceImpl;
import com.vodich.core.bean.Scenario;
import com.vodich.dao.ElasticsearchUtils;
import com.vodich.core.util.WebUtils;

/**
 * Servlet implementation class DefaultServlet
 */
@WebServlet("/default")
public class DefaultPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ScenarioService scenarioService;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DefaultPageServlet() {
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
		request.getSession().setAttribute("scenarii",scenarioService.loadAll());
		WebUtils.forward(request, response, "default.jsp");
		//response.getWriter().append("Served at: "+a).append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
