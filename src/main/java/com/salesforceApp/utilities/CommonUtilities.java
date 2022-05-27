package com.salesforceApp.utilities;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;



public class CommonUtilities {
	
	public static String getPropertyValue(String key) throws IOException{
		
		String path = Constants.APPLICATION_PROPERTIES_PATH;
		FileInputStream fin = new FileInputStream(path);
		Properties ob = new Properties();
		ob.load(fin);
		String value = ob.getProperty(key);
		fin.close();
		return value;
	}
	
	public static void setClipboardData(String path) {
		
		// StringSelection is a class that can be used for copy and paste operations.
		StringSelection stringSelection = new StringSelection(path);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
	}

}
