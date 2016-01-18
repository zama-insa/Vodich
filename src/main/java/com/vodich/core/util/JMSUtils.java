package com.vodich.core.util;

import java.io.IOException;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

public class JMSUtils {

	private Connection connection;
	private Session session;
	private MessageConsumer msgConsumer;
	private MessageProducer[] msgProducer= new MessageProducer[VodichUtils.NB_CONSUMER];
	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public MessageConsumer getMsgConsumer() {
		return msgConsumer;
	}

	public void setMsgConsumer(MessageConsumer msgConsumer) {
		this.msgConsumer = msgConsumer;
	}

	public MessageProducer[] getMsgProducer() {
		return msgProducer;
	}

	public void setMsgProducer(MessageProducer[] msgProducer) {
		this.msgProducer = msgProducer;
	}

	public Topic[] getTopics() {
		return topics;
	}

	public void setTopics(Topic[] topics) {
		this.topics = topics;
	}
	private Topic[] topics;
	
	private JMSUtils(ConnectionFactory connectionFactory, Queue queue, Topic[] topics) throws IOException {
		this.topics = topics;
		try {
			connection = connectionFactory.createConnection();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			msgConsumer = session.createConsumer(queue);
			for(int i = 0 ; i<topics.length;i++){
				msgProducer[i] = session.createProducer(topics[i]);
			}
			
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
	public void send(int topicId, String message, int consumer) throws JMSException {
		TextMessage textMessage = session.createTextMessage(message);
		msgProducer[consumer-1].send(topics[consumer-1], textMessage);
	}
	
	/**
	 * Read a message from queue
	 * @param timeout the time in ms after which the read 
	 * @return
	 * @throws JMSException 
	 */
	public String receive(long timeout) throws JMSException {
			try{
				TextMessage textMessage = (TextMessage) msgConsumer.receive(timeout);
				return textMessage.getText();

			}catch(java.lang.NullPointerException e){
				return "";
			}
		
		
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


	public static void init(ConnectionFactory connectionFactory, Queue queue, Topic[] topics) throws IOException {
		instance = new JMSUtils(connectionFactory, queue, topics);
	}
	private static JMSUtils instance;
	public static JMSUtils getInstance() {
		if (instance == null) 
			throw new RuntimeException("Creation error: static init() method must be called before");
		return instance;
		
	}
}
