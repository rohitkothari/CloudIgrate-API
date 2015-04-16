package cloudigrate.api.implementation;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cloudigrate.api.domain.LogEntry;

public class DashboardImpl {

	//C:\Users\Vaibhav
	ArrayList<LogEntry> logEntries = null;
	
	public DashboardImpl()
	{
		logEntries = new ArrayList<LogEntry>();
		//Windows log file
				String csvFile = "C://Users//Vaibhav//log.txt";
				
				//Mac log file
				//String csvFile = "/tmp/log.txt";
				BufferedReader br = null;
				String line = "";
				String cvsSplitBy = ",";
				
				String jsonString = null;
			 
				try {
					br = new BufferedReader(new FileReader(csvFile));
					while ((line = br.readLine()) != null) {
						System.out.println("Line is" + line);
						// use comma as separator
						String[] entries = line.split(cvsSplitBy);
						System.out.println("Count is"+ entries.length);
						if(entries.length > 2)
						logEntries.add(new LogEntry(entries[0], entries[1], entries[2], entries[3],entries[4], entries[5], Long.parseLong(entries[6]), entries[7], entries[8]));
					}
				
			}
				catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if (br != null) {
						try {
							br.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			 
				System.out.println("Done");
	}
	
	public String getDashboardData()
	{
		String jsonString = null;
		ObjectMapper objMapper = new ObjectMapper();
		try {
			jsonString = objMapper.writeValueAsString(logEntries);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonString;
	}

	public String getUserDashboard(String username) {
		// TODO Auto-generated method stub
		for (Iterator<LogEntry> it=logEntries.iterator(); it.hasNext();) {
		    if (!it.next().getUserName().equals(username))
		        it.remove(); // NOTE: Iterator's remove method, not ArrayList's, is used.
		}
		
		String jsonString = null;
		ObjectMapper objMapper = new ObjectMapper();
		try {
			jsonString = objMapper.writeValueAsString(logEntries);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonString;
	}

	public String getServiceDashboard(String service) {
		// TODO Auto-generated method stub
		
		for(Iterator<LogEntry> it=logEntries.iterator();it.hasNext();){
			if(!it.next().getService().equals(service))
				it.remove();
		}
		
		String jsonString = null;
		ObjectMapper objMapper = new ObjectMapper();
		try {
			jsonString = objMapper.writeValueAsString(logEntries);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonString;
	}
	
}
