package cloudigrate.api.domain;

public class LogEntry {
	String userName;
	String function;
	String platform;
	String level;
	String start;
	String end;
	long timestamp;
	
	public LogEntry(String u, String f, String p, String l, String s, String e, long t)
	{
		userName = u;
		function = f;
		platform = p;
		level = l;
		start = s;
		end = e;
		timestamp = t;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFunction() {
		return function;
	}
	public void setFunction(String function) {
		this.function = function;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
	
}
