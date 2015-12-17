package com.vodich.dao;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;
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
		IndexResponse test1 = ElasticsearchUtils.saveScenario(scenario); 		
		try {
		    Thread.sleep(1000);  // we have to wait 1 sec, time needed for the scenario to be indexed and searchable 
	        assertTrue (ElasticsearchUtils.deleteScenario("42").isFound());
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
	}
	
	@Test
	public void deleteScenarioTestNotFound() {	
		 assertNull(ElasticsearchUtils.deleteScenario("166"));
	}
	
	@Test
	public void loadScenarioTestFound() {
		Scenario scenario = new Scenario();
		scenario.setId("42");		
		ElasticsearchUtils.saveScenario(scenario); 
		try {
		    Thread.sleep(1000);  // we have to wait 1 sec, time needed for the scenario to be indexed and searchable 
		    assert(scenario.equals(ElasticsearchUtils.load("42")));
		} catch(InterruptedException ex){
		    Thread.currentThread().interrupt();
		}
	}
	
	@Test
	public void loadScenarioTestNotFound() {
		 // we have to wait 1 sec, time needed for the scenario to be indexed and searchable 
		    assertNull(ElasticsearchUtils.load("46"));
	}
	
	
	
	@AfterClass
	public static void close() {
		ElasticsearchUtils.close();
	}

}