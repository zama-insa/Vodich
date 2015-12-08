package com.vodich.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vodich.core.util.JMSUtils;

@WebServlet("/test/jms")
public class JMSTestServlet extends HttpServlet {

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
	public void doGet(HttpServletRequest request,
			HttpServletResponse response)
					throws IOException, ServletException
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String body = "";

		try {
			jmsUtils.startConnection();
			body = jmsUtils.read();
			jmsUtils.stopConnection();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.println("<html>");
		out.println("<head>");

		String title = "JMS Test";

		out.println("<title>" + title + "</title>");
		out.println("</head>");
		out.println("<body bgcolor=\"white\">");

		out.println("<h1>" + title + "</h1>");

		out.println("<p>The message contains \"" + body + "\".</p>");

		out.println("</body>");
		out.println("</html>");
	}
}
