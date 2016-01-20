package com.vodich.web.servlet;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.vodich.core.bean.Flow;
import com.vodich.core.bean.Scenario;
import com.vodich.dao.DAOException;

public class TestExport {
	Scenario scenario;
	Flow flow;

	public TestExport(){}
	
	public void init(){
		scenario = new Scenario();
		scenario.setName("426");
		ArrayList<Flow> flows = new ArrayList<Flow>();
		
		flow = new Flow();
		flow.setConsumer("1");
		flow.setProducer("1");
		flow.setFrequency(20.0);
		flow.setProcessTime(4.0);
		flow.setMessageLoad(0);
		flow.setStart(0);
		flow.setStop(10);
		flows.add(flow);
		scenario.setFlows(flows);
	}
	
	public void export() throws IOException{
		
	      File file = new File("/home/febroshka/git/Vodich/export"+scenario.getName());
	      ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
	    	String json = ow.writeValueAsString(scenario);
	    	System.out.println(json);	     
	        FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(json);
			bw.close();
	}
	/*
			for (Flow flow : scenario.getFlows()) {
				String json = mapper.writeValueAsString(flow);
			}
	*/
	
	public static void main( String[] args) throws IOException{
		TestExport te = new TestExport();
		te.init();
		te.export();
		
	}

}
