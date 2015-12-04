package com.vodich.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class StartupListener implements ServletContextListener {

    public void contextDestroyed(ServletContextEvent arg0)  {}

    public void contextInitialized(ServletContextEvent arg0)  { 
        // Let's get our app ready to be VO DICH !!
    	System.out.println("Starting Vodich app..");
    	//ElasticsearchUtils.init();
    }
	
}
