package com.vodich.core.util;

import java.io.IOException;

import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Connection;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

public class JMSUtils {

	private Connection connection;
	private Session session;
	private MessageConsumer msgConsumer;
	private MessageProducer msgProducer;
	private Topic topic;
	
	private JMSUtils(ConnectionFactory connectionFactory, Queue queue, Topic topic) throws IOException {
		this.topic = topic;
		try {
			connection = connectionFactory.createConnection();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			msgConsumer = session.createConsumer(queue);
			msgProducer = session.createProducer(topic);
		} catch (Exception e) {
			System.out.println(e.toString());
			throw new IOException(e);
		}
	}
	
	/**
	 * Must be called before any send or read operation
	 * @throws JMSException
	 */
	public void startConnection() throws JMSException {
		connection.start();
	}
	/**
	 * Should be called after local messaging operations are finished to save resources
	 * @throws JMSException
	 */
	public void stopConnection() throws JMSException {
		connection.stop();
	}
	/**
	 * Send a topic message
	 * @param message
	 * @param topicId
	 * @throws JMSException
	 */
	public void send(int topicId, String message) throws JMSException {
		TextMessage textMessage = session.createTextMessage(message);
		msgProducer.send(topic, textMessage);
	}
	
	/**
	 * Read a message from queue
	 * @param timeout the time in ms after which the read 
	 * @return
	 * @throws JMSException 
	 */
	public String receive(long timeout) throws JMSException {
		TextMessage textMessage = (TextMessage) msgConsumer.receive(timeout);
		return textMessage.getText();
	}
	
	/**
	 * Read message from queue
	 * This method is blocking until new message arrives
	 * @return
	 * @throws JMSException 
	 */
	public String receive() throws JMSException {
		TextMessage textMessage = (TextMessage) msgConsumer.receive();
		return textMessage.getText();
	}


	public static void init(ConnectionFactory connectionFactory, Queue queue, Topic topic) throws IOException {
		instance = new JMSUtils(connectionFactory, queue, topic);
	}
	private static JMSUtils instance;
	public static JMSUtils getInstance() throws IOException {
		if (instance == null) 
			throw new IOException("Creation error: static init() method must be called before");
		return instance;
		
	}
}
