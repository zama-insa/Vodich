package com.vodich.dao;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

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
		ElasticsearchUtils.saveScenario(new Scenario());
	}
	
	@AfterClass
	public static void close() {
		ElasticsearchUtils.close();
	}

}
