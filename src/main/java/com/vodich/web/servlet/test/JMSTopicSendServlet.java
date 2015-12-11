package com.vodich.web.servlet.test;

import java.io.IOException;
import java.util.Arrays;

import javax.jms.JMSException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vodich.core.util.JMSUtils;

/**
 * Servlet implementation class JMSTopicSendServlet
 */
@WebServlet("/test/jms/send/*")
public class JMSTopicSendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	JMSUtils jmsUtils;

	@Override
	public void init() throws ServletException {
		try {
			
			jmsUtils = JMSUtils.getInstance();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		int topic;
		try {
			String[] path = pathInfo.split("/");
			topic = Integer.parseInt(path[1]);
		} catch (NumberFormatException e) {
			response.getWriter().append("The path must looks like this: /test/jms/send/2");
			return;
		}
		response.getWriter().append("Sending to topic : " + topic);
		try {
			jmsUtils.startConnection();
			//jmsUtils.send(topic, "To " + topic + ": this is Vodich. Over");
			jmsUtils.stopConnection();
			response.getWriter().append("<br>Message sent.");
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
