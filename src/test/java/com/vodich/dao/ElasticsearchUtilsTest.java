package com.vodich.dao;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.vodich.core.bean.Scenario;

public class ElasticsearchUtilsTest {
	
	@BeforeClass
	public static void init() {
		ElasticsearchUtils.init();
	}
	
	@Test
	public void saveScenarioTest() {
		ElasticsearchUtils.saveScenario(new Scenario());
	}
	
	@AfterClass
	public static void close() {
		ElasticsearchUtils.close();
	}

}
