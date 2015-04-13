package cloudigrate.api.implementation;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;

import cloudigrate.api.domain.LogEntry;

public class DashboardImpl {

	//C:\Users\Vaibhav
	
	public String getDashboardData()
	{
		//Windows log file
		//String csvFile = "C://Users//Vaibhav//log.txt";
		
		//Mac log file
		String csvFile = "/tmp/log.txt";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		ArrayList<LogEntry> logEntries = new ArrayList<LogEntry>();
		String jsonString = null;
	 
		try {
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
				System.out.println("Line is" + line);
				// use comma as separator
				String[] entries = line.split(cvsSplitBy);
				System.out.println("Count is"+ entries.length);
				if(entries.length > 2)
				logEntries.add(new LogEntry(entries[0], entries[1], entries[2], entries[3],entries[4], entries[5], Long.parseLong(entries[6]), entries[7]));
			}
		ObjectMapper objMapper = new ObjectMapper();
		jsonString = objMapper.writeValueAsString(logEntries);
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
		return jsonString;
	}
	
}
