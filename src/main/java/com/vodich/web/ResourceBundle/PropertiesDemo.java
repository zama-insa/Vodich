package com.vodich.web.ResourceBundle;
import com.vodich.*;

import java.io.File;
import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

public class PropertiesDemo {
	
	public PropertiesDemo(){}
		
	Locale[] supportedLocales = {
			Locale.ENGLISH,
			Locale.FRENCH	    
		};

	public void testKey(){
		ResourceBundle labels = ResourceBundle.getBundle("MessagesBundle", Locale.ENGLISH);
		Enumeration<String> bundleKeys = labels.getKeys();
		System.out.println("  1er langage ");
		
		while (bundleKeys.hasMoreElements()) {
		    String key = (String)bundleKeys.nextElement();
		    String value = labels.getString(key);
		    System.out.println("key = " + key + ", " + "value = " + value);
		}
		
		System.out.println(" 2eme langage ");
		
		
		ResourceBundle labels2 = ResourceBundle.getBundle("MessagesBundle", Locale.FRENCH);
		Enumeration<String> bundleKeys2 = labels2.getKeys();
		
		while (bundleKeys2.hasMoreElements()) {
		    String key = (String)bundleKeys2.nextElement();
		    String value = labels2.getString(key);
		    System.out.println("key = " + key + ", " + "value = " + value);
		}
	}
	
	public static void main(String[] args){
		
		PropertiesDemo d = new PropertiesDemo();
		d.testKey();
		
	}

}
