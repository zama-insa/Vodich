package com.vodich.web.ResourceBundle;

import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

public class TextPropertiesDemo {
	
	public void testKey(){
		ResourceBundle labels = ResourceBundle.getBundle("text", Locale.ENGLISH);
		Enumeration<String> bundleKeys = labels.getKeys();
		System.out.println("  1er langage ");
		
		while (bundleKeys.hasMoreElements()) {
		    String key = (String)bundleKeys.nextElement();
		    String value = labels.getString(key);
		    System.out.println("key = " + key + ", " + "value = " + value);
		}
		
	}
	
	public static void main(String[] args){
		
		TextPropertiesDemo d = new TextPropertiesDemo();
		d.testKey();
		
	}

}
