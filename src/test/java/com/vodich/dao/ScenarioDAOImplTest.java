package com.vodich.dao;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.bootstrap.Elasticsearch;
import org.elasticsearch.client.Client;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vodich.core.bean.Scenario;
import com.vodich.core.util.VodichUtils;

public class ScenarioDAOImplTest {
	private static ScenarioDAO dao;
 
	@BeforeClass
	public static void setUp() throws IOException {
		dao = ScenarioDAOImpl.getInstance();
		FileUtils.deleteDirectory(new File(ElasticsearchUtils.getProperties().getProperty("dataPath")));
		ElasticsearchUtils.init();
	}
	
	@AfterClass
	public static void close(){
		ElasticsearchUtils.close();
	}

	@Test(expected=DAOException.class)
	public void saveTestScenarioNameNull() throws DAOException{
		Scenario sc = new Scenario ();
		dao.save(sc);	
	}
	@Test(expected=DAOException.class)
	public void saveTestScenarioNameEmptyl() throws DAOException{
		Scenario sc = new Scenario ();
		sc.setName("");
		dao.save(sc);	
	}
	
	@Test
	public void saveTestScenarioWithName(){
		Scenario sc = new Scenario ();
		sc.setName("test");	
	}
	
	@Test
	public void deleteNonExistingScenario() throws DAOException{
		assertFalse(dao.delete("57GKIJH883"));	
	}
	
	@Test
	public void deleteExistingScenario() throws InterruptedException, DAOException{
		Scenario sc = new Scenario();
		sc.setName("test");
		String scenarioId = ElasticsearchUtils.saveScenario(sc);
		sc.setId(scenarioId);
		Thread.sleep(2000);
		assertTrue(dao.delete(sc.getId()));
	}
	

	
}
