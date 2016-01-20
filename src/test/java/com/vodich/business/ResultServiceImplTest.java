package com.vodich.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.vodich.core.bean.Result;
import com.vodich.core.util.JMSUtils;
import com.vodich.dao.ResultDAO;
import com.vodich.dao.ScenarioDAO;

@RunWith(MockitoJUnitRunner.class)
public class ResultServiceImplTest {

	@Mock
	ResultDAO resultDAOMock;
	
	@Mock
	JMSUtils jmsUtilsMock;
	
	@Mock
	private ScenarioDAO scenarioDAOMock;

	private ResultServiceImpl service;

	
	@Before
	public void setUp() {
		service = new ResultServiceImpl(resultDAOMock, jmsUtilsMock, scenarioDAOMock);
	}
	
	@Test
	public void loadResultTest() {
		Result r = new Result();
		r.setId("123");
		r.setLaunchTime(new Date());
		r.setFinishTime(new Date());
		r.setResult(new ArrayList<>());
		r.setScenarioId("42");
		when(resultDAOMock.load("123")).thenReturn(r);
		assertTrue(r.equals(service.load("123")));
	}
	
	@Test
	public void loadResultNotFoundTest() {
		when(resultDAOMock.load("123")).thenReturn(null);
		assertNull(service.load("123"));
	}
	
	@Test
	public void loadAllTest() {
		List<Result> mockList = new ArrayList<>();
		mockList.add(new Result());
		mockList.add(new Result());
		when(resultDAOMock.loadAll()).thenReturn(mockList);
		assertEquals(2, mockList.size());
	}
}
