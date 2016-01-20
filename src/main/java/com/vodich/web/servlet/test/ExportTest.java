package com.vodich.web.servlet.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExportTest {
	
	public ExportTest(){}
	
	public void createFile(){
		try {
		      File file = new File("/home/febroshka/git/Vodich/export1");		      
		      FileWriter fw = new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);
				String content ="yoyoyo";
				bw.write(content);
				bw.close();
		      
	    	} catch (IOException e) {
		      e.printStackTrace();
	    	}
	}
	
	
	public static void main(String[] args){
		
		ExportTest test = new ExportTest();
		test.createFile();
		
	}

}
