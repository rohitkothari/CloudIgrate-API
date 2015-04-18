package cloudigrate.api.domain;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

public class Platform {
	
	public Platform(String storage, String sql, String nosql, String instance)
	{
		this.instance = instance;
		this.storage = storage;
		this.nosql = nosql;
		this.sql =sql;
	}
	
	String storage;
	public String getStorage() {
		return storage;
	}

	public void setStorage(String storage) {
		this.storage = storage;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public String getNosql() {
		return nosql;
	}

	public void setNosql(String nosql) {
		this.nosql = nosql;
	}

	public void setInstance(String instance) {
		this.instance = instance;
	}

	String sql;
	String nosql;
	String instance;
	
	
	public enum CloudPlatform {
		AWS, GOOGLE
	}
	
	public Platform()
	{
		//platformValues = new HashMap<String, String>();
		//loadAllPlatforms();
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
}
