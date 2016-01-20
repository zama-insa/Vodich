package com.vodich.dao;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.elasticsearch.action.index.IndexResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.vodich.core.bean.Result;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ElasticsearchUtils.class)
public class ResultDAOImplTest {
	
	private static ResultDAO resultDAO = ResultDAOImpl.getInstance();
	private Result res;
	
	
	@Before
	public void setUpMethod() {
		PowerMockito.mockStatic(ElasticsearchUtils.class);
		res = new Result();
		res.setId("34");
		Date date = new Date();
		res.setLaunchTime(date);
		res.setFinishTime(date);
		res.setScenarioId("453");
	}
	
	@Test
	public void saveResultTest() {
		IndexResponse response = new IndexResponse();
		when(ElasticsearchUtils.saveScenarioResult(res)).thenReturn(response);
		assertEquals(resultDAO.save(res), response.getId());
	}
	
	@Test
	public void loadTest() {
		when(ElasticsearchUtils.loadScenarioResult("42")).thenReturn(res);
		assertEquals(resultDAO.load("42"), res);
	}
	
	@Test
	public void loadAllTest() {
		List<Result> results = new ArrayList<>();
		results.add(res);
		results.add(res);
		when(ElasticsearchUtils.loadAllScenarioResults()).thenReturn(results);
		assertEquals(resultDAO.loadAll().size(), 2);
	}
	
	

}
