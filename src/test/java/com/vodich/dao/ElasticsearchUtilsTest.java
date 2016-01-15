package com.vodich.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.vodich.core.bean.Result;
import com.vodich.core.bean.Scenario;

public class ElasticsearchUtilsTest {

	/* IMPORTANT :
	 * For each change in elasticsearch, 1 sec is the time needed for the
	 * change to be effective
	 */
	
	@BeforeClass
	public static void init() throws IOException {
		// Delete data folder so that there's no document initially. Yay !
		FileUtils.deleteDirectory(new File(ElasticsearchUtils.getProperties().getProperty("dataPath")));
		ElasticsearchUtils.init();
	}
	
	@AfterClass
	public static void close() {
		ElasticsearchUtils.close();
	}

	@Test
	public void deleteScenarioTestFound() throws InterruptedException {
		Scenario scenario = new Scenario();
		scenario.setName("42");
		String scenarioId = ElasticsearchUtils.saveScenario(scenario);
		System.out.println(scenarioId);
		Thread.sleep(1000);
		assertTrue(ElasticsearchUtils.deleteScenario(scenarioId).isFound());
		Thread.sleep(1000);
		assertNull(ElasticsearchUtils.load(scenarioId));
	}

	@Test
	public void deleteScenarioTestNotFound() {
		assertFalse(ElasticsearchUtils.deleteScenario("166").isFound());
	}

	@Test
	public void saveThenLoadScenarioById() throws DAOException, InterruptedException {
		Scenario scenario = new Scenario();
		scenario.setName("42");
		String scenarioId = ElasticsearchUtils.saveScenario(scenario);
		Thread.sleep(1000);
		assertNotNull(ElasticsearchUtils.load(scenarioId));
		
		// Ensure the test is clean (leave no trail)
		ElasticsearchUtils.deleteScenario(scenarioId);
	}

	@Test
	public void loadScenarioTestNotFound() throws DAOException {
		assertNull(ElasticsearchUtils.load("46"));
	}
	

	@Test
	public void saveScenarioTest() {
		Scenario s = new Scenario();
		String scenarioId = ElasticsearchUtils.saveScenario(new Scenario());
		assertNotNull(scenarioId);
		// Ensure the test is clean (leave no trail)
		ElasticsearchUtils.deleteScenario(scenarioId);
	}
	
	@Test
	public void loadByNameTest() throws InterruptedException, DAOException {
		Scenario s = new Scenario();
		s.setName("loadByName_scenario");
		s.setCreatedAt(new Date());
		String sid = ElasticsearchUtils.saveScenario(s);
		s.setId(sid);
		Thread.sleep(1000);
		Scenario loaded = ElasticsearchUtils.loadByName("loadByName_scenario");
		assertTrue(s.equals(loaded));
		
		// Ensure the test is clean (leave no trail)
		ElasticsearchUtils.deleteScenario(sid);
	}
	
	@Test
	public void loadScenariiTest() throws InterruptedException {
		String[] sids = new String[10];
		for (int i = 0; i < 10; i++) {
			sids[i] = ElasticsearchUtils.saveScenario(new Scenario());
		}
		Thread.sleep(1000);
		assertEquals(10, ElasticsearchUtils.loadScenarii().size());
	}
	
	@Test
	public void saveScenarioResultWithoutDataTest() {
		Result result = new Result();
		assertTrue(ElasticsearchUtils.saveScenarioResult(result).isCreated());
	}
	
	@Test
	public void saveScenarioResultWithValidDataTest() {
		Result result = new Result();
		result.setScenarioId("123");
		result.setLaunchTime(new Date());
		List<Object> resultList = new ArrayList<>();
		Map<String, Object> a1 = new HashMap<>(); a1.put("id", "1"); a1.put("time", "2");
		Map<String, Object> a2 = new HashMap<>(); a2.put("id", "3"); a2.put("time", "4");
		resultList.add(a1); resultList.add(a2);
		result.setResult(resultList);
		assertTrue(ElasticsearchUtils.saveScenarioResult(result).isCreated());
	}

	@Test
	public void saveScenarioResultWithInvalidDataTest() {
		Result result = new Result();
		List<Object> resultList = new ArrayList<>();
		Map<String, Object> a1 = new HashMap<>(); a1.put("id", "1");
		resultList.add(a1);
		result.setResult(resultList);
		assertTrue(ElasticsearchUtils.saveScenarioResult(result).isCreated());
	}
}