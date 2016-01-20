package com.vodich.web.servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vodich.core.bean.Scenario;

public class TestImportJSON {
	
	private Scenario ob = new Scenario();
	
	public TestImportJSON(){}
	
	public void testDesktopPath() throws IOException{
		String path  = System.getProperty("user.home") + "/Desktop/";
		System.out.println(path);
		File file = new File(path+"truc.txt");
	    if (file.createNewFile()){
	    	System.out.println("File created");
	    }
		
	}
	
	public void test() throws IOException{
		String thisLine ="";
		String fileContent="";
		
	
	 BufferedReader br = new BufferedReader(new FileReader("/home/febroshka/git/Vodich/export678"));
     while ((thisLine = br.readLine()) != null) {
    	// System.out.println(thisLine);
    	 fileContent = fileContent+thisLine+"\n";
     }
     ob = new ObjectMapper().readValue(fileContent, Scenario.class);
     }
	
	public static void main(String[] args) throws IOException
	{
		TestImportJSON tijs = new TestImportJSON();
		tijs.testDesktopPath();
		//tijs.test();
		//System.out.println("Nom : "+tijs.ob.getName()+"Consumer :"+tijs.ob.getFlows().get(0).getFrequency());
	}
	

}

