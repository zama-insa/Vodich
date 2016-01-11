package com.vodich.dao;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.vodich.core.bean.Scenario;

public class ElasticsearchUtilsTest {

	@BeforeClass
	public static void init() throws IOException {
		// Delete data folder so that there's no document initially. Yay !
		FileUtils.deleteDirectory(new File(ElasticsearchUtils.getProperties().getProperty("dataPath")));
		ElasticsearchUtils.init();
	}

	@Test
	public void saveScenarioTest() {
		assertNotNull(ElasticsearchUtils.saveScenario(new Scenario()));
	}

	@Test
	public void deleteScenarioTestFound() {
		Scenario scenario = new Scenario();
		scenario.setName("42");
		String scenarioId = ElasticsearchUtils.saveScenario(scenario);
		System.out.println(scenarioId);
		try {
			Thread.sleep(1000); // we have to wait 1 sec, time needed for the
								// scenario to be indexed and searchable
			assertTrue(ElasticsearchUtils.deleteScenario(scenarioId).isFound());
			Thread.sleep(1000);
			assertNull(ElasticsearchUtils.load(scenarioId));
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

	@Test
	public void deleteScenarioTestNotFound() {
		assertFalse(ElasticsearchUtils.deleteScenario("166").isFound());
	}

	@Test
	public void saveThenLoadScenarioById() throws DAOException {
		Scenario scenario = new Scenario();
		scenario.setName("42");
		String scenarioId = ElasticsearchUtils.saveScenario(scenario);
		try {
			Thread.sleep(1000);
			assertNotNull(ElasticsearchUtils.load(scenarioId));
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

	@Test
	public void loadScenarioTestNotFound() throws DAOException {
		assertNull(ElasticsearchUtils.load("46"));
	}

	@AfterClass
	public static void close() {
		ElasticsearchUtils.close();
	}

}