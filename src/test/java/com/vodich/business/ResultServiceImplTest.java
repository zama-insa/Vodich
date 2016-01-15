package com.vodich.business;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.vodich.core.bean.Result;
import com.vodich.dao.ResultDAO;

@RunWith(MockitoJUnitRunner.class)
public class ResultServiceImplTest {

	@Mock
	ResultDAO resultDAOMock;
	
	private ResultServiceImpl service;
	
	@Before
	public void setUp() {
		service = new ResultServiceImpl(resultDAOMock);
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
}
