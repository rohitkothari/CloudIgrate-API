package cloudigrate.api.domain;

import java.util.HashMap;
import java.util.Map;

public class NoSqlItem {
	private String tableName;
	Map<String, String> attributes = null;
	
	public NoSqlItem()
	{
		attributes =  new HashMap<String, String>();
	}
	
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	public void addAttribute(String key, String value)
	{
		attributes.put(key, value);
	}
	
	public void removeAttribute(String key)
	{
		attributes.remove(key);
	}
}
