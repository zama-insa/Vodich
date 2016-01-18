package com.vodich.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vodich.business.ResultService;
import com.vodich.business.ResultServiceImpl;
import com.vodich.core.bean.Result;
import com.vodich.core.util.VodichUtils;
import com.vodich.core.util.WebUtils;

/**
 * Servlet implementation class ResultServlet
 */
@WebServlet(urlPatterns={"/result"})
public class ResultServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String PARAM_RESULT_ID = "rid";
	private static final String ATT_ERROR_MSG = "error";
	private static final String ATT_RESULT_ID = PARAM_RESULT_ID;
	private static final String ATT_RESULT_LIST = "resultList";
	private static final String ATT_LIST_VIEW_MODE = "listView";
	private ResultService resultService;
       
   @Override
	public void init() throws ServletException {
	   resultService = ResultServiceImpl.getInstance();
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String resultId = request.getParameter(PARAM_RESULT_ID);
		if (VodichUtils.isNullOrEmpty(resultId)) {
			request.setAttribute(ATT_LIST_VIEW_MODE, true);
			request.setAttribute(ATT_RESULT_LIST, resultService.loadAll());
			WebUtils.forward(request, response, "result.jsp");
			return;
		} 
		
		Result result = resultService.load(resultId);
		if (result == null) {
			request.setAttribute(ATT_ERROR_MSG, "No result corresponding to id '" + resultId + "'");
			WebUtils.forward(request, response, "result.jsp");
			return;
		}
		request.setAttribute(ATT_RESULT_ID, resultId);
		WebUtils.forward(request, response, "result.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
