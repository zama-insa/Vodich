package com.vodich.util;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.Topic;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.vodich.core.util.JMSUtils;
import com.vodich.core.util.VodichUtils;
import com.vodich.dao.DAOException;

public class JMSUtilTest {

	@Before
	public void init(){
		try{
			InitialContext init = new InitialContext();
			ConnectionFactory connectionFactory =
		            (ConnectionFactory) init.lookup("ConnectionFactory");
		    javax.jms.Queue queue = (javax.jms.Queue) init.lookup("TEST.FOO");
		    javax.jms.Topic topic = (javax.jms.Topic) init.lookup("TEST.BAR");
			Topic[] topics = new Topic[2];
			topics[0]=topic;
			
			JMSUtils.init(connectionFactory, queue, topics);
			JMSUtils jmsUtils = JMSUtils.getInstance();
			jmsUtils.startConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testGetInstance(){
			JMSUtils jmsUtils;
			jmsUtils = JMSUtils.getInstance();
				assertNotNull(jmsUtils.getMsgConsumer());
				assertNotNull(jmsUtils.getSession());
				assertNotNull(jmsUtils.getMsgProducer());
				assertNotNull(jmsUtils.getTopics());



	}
	
	@Test()
	public void jmsUtilsTestReceiveTimeOut() throws JMSException{
		JMSUtils jmsUtils;
		jmsUtils = JMSUtils.getInstance();
		long start = System.currentTimeMillis();
		String totest = jmsUtils.receive(100);
		long end = System.currentTimeMillis();
		assertEquals(end-start, 100, 10);

	}
	
	@After
	public void finish(){
		try {
			JMSUtils.getInstance().stopConnection();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}

	}
}
