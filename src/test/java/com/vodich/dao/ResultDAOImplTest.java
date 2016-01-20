package com.vodich.dao;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.elasticsearch.action.index.IndexResponse;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.vodich.core.bean.Result;

public class ResultDAOImplTest {
	
	private static ResultDAO rdao;
	private static Result res;
	 
	@BeforeClass
	public static void setUp() throws IOException {
		rdao = ResultDAOImpl.getInstance();
		FileUtils.deleteDirectory(new File(ElasticsearchUtils.getProperties().getProperty("dataPath")));
		ElasticsearchUtils.init();
		res = new Result();
		res.setId("34");
		Date date = new Date();
		res.setLaunchTime(date);
		res.setFinishTime(date);
		res.setScenarioId("453");
	}
	
	@AfterClass
	public static void close(){
		ElasticsearchUtils.close();
	}
	
	@Test
	public void saveTest() {
		// TODO Auto-generated method stub
		IndexResponse a = ElasticsearchUtils.saveScenarioResult(res);
		}
	
	@Test
	public void loadTest() {
		// TODO Auto-generated method stub
		Result a = ElasticsearchUtils.loadScenarioResult(res.getId());
		assertNotNull(a);
	}

	
	

}
