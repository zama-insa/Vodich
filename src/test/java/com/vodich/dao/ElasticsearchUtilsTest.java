package com.vodich.dao;

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
		scenario.setId("42");
		ElasticsearchUtils.saveScenario(scenario);
		try {
			Thread.sleep(2000); // we have to wait 1 sec, time needed for the
								// scenario to be indexed and searchable
			assertTrue(ElasticsearchUtils.deleteScenario("42").isFound());
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

	@Test
	public void deleteScenarioTestNotFound() {
		assertNull(ElasticsearchUtils.deleteScenario("166"));
	}

	@Test
	public void saveThenLoadScenarioById() throws DAOException {
		Scenario scenario = new Scenario();
		scenario.setName("42");
		String scenarioId = ElasticsearchUtils.saveScenario(scenario);
		try {
			Thread.sleep(1000); // we have to wait 1 sec, time needed for the
								// scenario to be indexed and searchable
			assertNotNull(ElasticsearchUtils.load(scenarioId));
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

	@Test
	public void loadScenarioTestNotFound() throws DAOException {
		// we have to wait 1 sec, time needed for the scenario to be indexed and
		// searchable
		assertNull(ElasticsearchUtils.load("46"));
	}

	@AfterClass
	public static void close() {
		ElasticsearchUtils.close();
	}

}