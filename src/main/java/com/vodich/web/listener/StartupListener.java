package com.vodich.web.listener;

import java.io.IOException;

import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Topic;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.vodich.core.util.JMSUtils;
import com.vodich.dao.ElasticsearchUtils;

@WebListener
public class StartupListener implements ServletContextListener {
	
	@Resource(name="jms/myQueue")
	private Queue queue;
	
	@Resource(name="jms/ConnectionFactory")
	private ConnectionFactory connectionFactory;
	
	@Resource(name="jms/myTopic")
	private Topic topic;
	
    public void contextDestroyed(ServletContextEvent arg0)  {
    	ElasticsearchUtils.close();
    }

    public void contextInitialized(ServletContextEvent arg0)  { 
        // Let's get our app ready to be VO DICH !!
    	System.out.println("Starting Vodich app..");
    	System.out.println("Starting Elasticsearch..");
    	ElasticsearchUtils.init();
    	System.out.println("Starting JMS Service..");
    	try {
			JMSUtils.init(connectionFactory, queue, topic);
		} catch (Exception e) {
			System.out.println("Unable to start JMS : ");
			e.printStackTrace();
		}
    	System.out.println("Vodich app ready!");
    }
	
}
