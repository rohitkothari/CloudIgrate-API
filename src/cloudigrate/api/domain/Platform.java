package cloudigrate.api.domain;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

public class Platform {
	
	private static Platform platform = null;
	HashMap<String, String> platformValues = null;
	
	public enum CloudPlatform {
		AWS, GOOGLE
	}
	
	protected Platform()
	{
		platformValues = new HashMap<String, String>();
		loadAllPlatforms();
	}
	
	public static Platform getInstance() {
	      if(platform == null) {
	    	  platform = new Platform();
	      }
	      return platform;
	   }
	
	public void setPlatformValue(String level, String platform)
	{
	Properties prop = new Properties();
	FileOutputStream output = null;
	try {
		output = new FileOutputStream("config.properties");
 
		// set the properties value
		prop.setProperty(level, platform);
		
		// save properties to project root folder
		prop.store(output, null);
 
	} catch (IOException io) {
		io.printStackTrace();
	} finally {
		if (output != null) {
			try {
				output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
 
	}
	}
	
	public void loadAllPlatforms()
	{
		Properties prop = new Properties();
		InputStream input = null;
	 
		try {
			input = new FileInputStream("config.properties");
	 
			// load a properties file
			prop.load(input);
	 
			// get the property value and print it out
			platformValues.put("sql", prop.getProperty("sql"));
			platformValues.put("storage", prop.getProperty("sql"));
			platformValues.put("nosql", prop.getProperty("sql"));
			platformValues.put("instance", prop.getProperty("sql"));
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public CloudPlatform getPlatformValue(String level)
	{
		if(platformValues.get(level).equals("AWS"))
			return CloudPlatform.AWS;
		else if(platformValues.get(level).equals("GOOGLE"))
			return CloudPlatform.GOOGLE;
		return null;
	}
}
