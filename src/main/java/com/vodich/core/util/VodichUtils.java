package com.vodich.core.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.vodich.core.bean.Flow;

public class VodichUtils {

	public static final int NB_CONSUMER = 2;
	public static boolean isNullOrEmpty(String string) {
		return string == null || "".equals(string.trim());
	}
	
	public static String readResource(String fileName) throws FileNotFoundException {
			StringBuilder result = new StringBuilder("");
			//Get file from resources folder
			ClassLoader classLoader = VodichUtils.class.getClassLoader();
			File file;
			try {
				file = new File(classLoader.getResource(fileName).getFile());
			} catch(Throwable e) {
				throw new FileNotFoundException("Unable to load ressource file '" + fileName +"'");
			}
			try (Scanner scanner = new Scanner(file)) {
				while (scanner.hasNextLine()) {
					String line = scanner.nextLine();
					result.append(line).append("\n");
				}
				scanner.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return result.toString();
		  }
	
	public static Map<String,String> rooting(List<Flow> flows){
		return null;
	}

}
