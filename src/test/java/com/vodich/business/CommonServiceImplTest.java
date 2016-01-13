package com.vodich.business;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class CommonServiceImplTest {

	private CommonServiceImpl service;

	@Before
	public void setUp() {
		service = new CommonServiceImpl();
	}

	@Test
	public void testGetConsumerNum() {
		assertEquals("For now the consumer num is fixed at 5", service.getConsumerNum(), 5);
	}

	@Test
	public void testGetProducerNum() {
		assertEquals("For now the producer num is fixed at 5", service.getProducerNum(), 5);
	}
}
