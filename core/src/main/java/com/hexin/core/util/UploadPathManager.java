package com.hexin.core.util;
import java.util.Properties;
import java.util.ResourceBundle;

import com.hexin.core.util.properties.Property;


public class UploadPathManager {
	public static String getUploadPhysicalPath() {
		Properties props=System.getProperties();
		String osName = props.getProperty("os.name").toString();
		if(osName.toLowerCase().indexOf("windows") > -1) {
			return Property.getProperty("fileUploadWindowsPath");
		}
		else {
			return Property.getProperty("fileUploadLinuxPath");
		}
	}	
	
	public static String getUploadWebPath(){
		Properties props=System.getProperties();			
		String osName = props.getProperty("os.name").toString();
		if(osName.toLowerCase().indexOf("windows") > -1) {
			return Property.getProperty("fileUploadUrlLocal");
		}
		else {
			return Property.getProperty("fileUploadUrl");
		}
	}
	
}
