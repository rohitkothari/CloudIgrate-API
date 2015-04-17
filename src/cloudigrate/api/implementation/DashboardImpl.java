package cloudigrate.api.implementation;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cloudigrate.api.domain.AWSBillItem;
import cloudigrate.api.domain.AWSServiceBillItem;
import cloudigrate.api.domain.AWSServiceRateItem;
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
	
	public String getBillingDashboard() {
		
		String [] FILE_HEADER_MAPPING = {"ProductName","UsageType","operation","availabilityZone","usageQuantity","cost","resourceId"};

		Double totalCost = 0.0;
		int MULTIPLIER = 1;
		String result[] = new String[3];
		String result1 = null;
//		public void readCsvFile(String fileName) {

			FileReader fileReader = null;
			CSVParser csvFileParser = null;
			int recordCounter=1;

			//Create the CSVFormat object with the header mapping
			CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader(FILE_HEADER_MAPPING);
			try {

				//Create a new list of student to be filled by CSV file data 
				List<AWSBillItem> awsBillItemList = new ArrayList<AWSBillItem>();
				ArrayList<AWSServiceBillItem> awsServiceCostItemList = new ArrayList<AWSServiceBillItem>();
				ArrayList<AWSServiceRateItem> awsServiceRateItemList = new ArrayList<AWSServiceRateItem>();


				HashMap<String, AWSServiceBillItem> awsServiceBillItemMap = new HashMap<String, AWSServiceBillItem>();
				HashMap<String, AWSServiceRateItem> awsServiceRateItemMap = new HashMap<String, AWSServiceRateItem>();
				//initialize FileReader object
				fileReader = new FileReader("/Users/rohietkothari/Desktop/aws-billing-vb.csv");

				//initialize CSVParser object
				csvFileParser = new CSVParser(fileReader, csvFileFormat);

				//Get a list of CSV file records
				List<CSVRecord> csvRecords = csvFileParser.getRecords(); 

				//Read the CSV file records starting from the second record to skip the header
				for (int i = 1; i < csvRecords.size()-3; i++) {
					CSVRecord record = csvRecords.get(i);
					
					AWSBillItem awsBillItem = new AWSBillItem();
					AWSServiceBillItem awsServiceCostItem = new AWSServiceBillItem();
					AWSServiceRateItem awsServiceRateItem = new AWSServiceRateItem();

					awsBillItem.setProductName(record.get(5));
					awsServiceCostItem.setProductName(record.get(5));

					awsBillItem.setUsageType(record.get(9));
					awsBillItem.setOperation(record.get(10));

					if(record.get(5).equalsIgnoreCase("Amazon Elastic Compute Cloud") || 
							record.get(5).equalsIgnoreCase("Amazon Simple Storage Service") ||
							record.get(5).equalsIgnoreCase("Amazon DynamoDB") ||
							record.get(5).equalsIgnoreCase("Amazon RDS Service")) {

						if (record.get(10).equalsIgnoreCase("RunInstances") || 
								record.get(10).equalsIgnoreCase("StandardStorage") ||
								record.get(10).equalsIgnoreCase("CommittedThroughput") ||
								record.get(10).equalsIgnoreCase("CreateDBInstance")) {

							if(record.get(5).equalsIgnoreCase("Amazon Elastic Compute Cloud")) {
								awsServiceRateItem.setProductName("Compute Service");
							   }
							   else if(record.get(5).equalsIgnoreCase("Amazon Simple Storage Service")) {
								   awsServiceRateItem.setProductName("Storage Service");
							   }
							   else if(record.get(5).equalsIgnoreCase("Amazon DynamoDB")) {
								   awsServiceRateItem.setProductName("NoSQL Service");
							   }
							   else if(record.get(5).equalsIgnoreCase("Amazon RDS Service")) {
								   awsServiceRateItem.setProductName("SQL Service");
							   }
							
							//awsServiceRateItem.setProductName(record.get(5));
							awsServiceRateItem.setOperation(record.get(10));
							if(record.get(17) != null && record.get(17).length() > 0) {
								awsServiceRateItem.setRate((Math.abs(Double.parseDouble(record.get(17))))*MULTIPLIER);
							}
							
							else {
								awsServiceRateItem.setRate(0.0);
							}

							if(!awsServiceRateItemMap.containsKey(record.get(5))) {
								awsServiceRateItemMap.put(record.get(5), awsServiceRateItem);
								awsServiceRateItemList.add(awsServiceRateItem);
							}

						}

					}

					if(record.get(11) != null) {
						awsBillItem.setAvailabilityZone(record.get(11));
					}
					
					else {
						awsBillItem.setAvailabilityZone("NA");
					}

					if(record.get(16) != null && record.get(16).length() > 0) {
						awsBillItem.setUsageQuantity(Math.abs(Double.parseDouble(record.get(16))));

					}
					
					else {
						awsBillItem.setUsageQuantity(0.0);
					}

					if(record.get(18) != null && record.get(18).length() > 0) {
						awsBillItem.setCost(Math.abs(Double.parseDouble(record.get(18))));
						awsServiceCostItem.setCost(Math.abs(Double.parseDouble(record.get(18))*MULTIPLIER));
						
						if(awsServiceBillItemMap.get(record.get(5)) == null) {
							awsServiceBillItemMap.put(record.get(5), awsServiceCostItem);	
						}
						
						else {
							awsServiceCostItem.setCost(Math.abs(awsServiceBillItemMap.get(record.get(5)).getCost()) + Math.abs(Double.parseDouble(record.get(18))*MULTIPLIER));
							awsServiceBillItemMap.put(record.get(5), awsServiceCostItem);
						}	

					}
					else {
						awsBillItem.setCost(0.0);
						awsServiceCostItem.setCost(0.0);
					}

					if(record.get(19) != null) {
						awsBillItem.setResourceId(record.get(19));
					}
					else {
						awsBillItem.setResourceId("none");

					}

					/*System.out.println("Testing: "+record.get(5));
					System.out.println("Testing: "+record.get(9));
					System.out.println("Testing: "+record.get(10));
					System.out.println("Testing: "+record.get(11));
					System.out.println("Testing: "+record.get(16));
					System.out.println("Testing: "+record.get(18));
					System.out.println("###: "+record.get(19));

					System.out.println("ProductName: "+awsBillItem.getProductName());
					System.out.println("Operation: "+awsBillItem.getOperation());
					System.out.println("UsageType: "+awsBillItem.getUsageType());
					System.out.println("AvailabilityZone: "+awsBillItem.getAvailabilityZone());
					System.out.println("UsageQuantity: "+awsBillItem.getUsageQuantity());
					System.out.println("Cost: "+awsBillItem.getCost());
					System.out.println("ResourceId: "+awsBillItem.getResourceId());
					System.out.println(" ");*/

					totalCost += awsBillItem.getCost();
					recordCounter++;
					awsBillItemList.add(awsBillItem);
					//awsServiceCostItemList.add(awsServiceCostItem);

					//				}

				}

				

				/*awsBillItemList.remove(count1-1);
				awsBillItemList.remove(count1-2);

				for (AWSServiceRateItem rateItem : awsServiceRateItemList) {
					System.out.println("==================================");
					System.out.println("Product: "+rateItem.getProductName());
					System.out.println("Operation: "+rateItem.getOperation());
					System.out.println("Usage: "+billItem.getUsageQuantity());
					System.out.println("Rate: "+rateItem.getRate());
					System.out.println("==================================");
				}*/

				/*for (String prodName2 : awsServiceRateItemMap.keySet()) {
					System.out.println("------------------------------------------------");
					   System.out.println("Iterating or looping map using java5 foreach loop");
					   System.out.println("ProdName: " + prodName2); 
					   System.out.println("Operation: " + awsServiceRateItemMap.get(prodName2).getOperation());
					   System.out.println("Rate: " + awsServiceRateItemMap.get(prodName2).getRate());
				}*/

				for (String prodName : awsServiceBillItemMap.keySet()) {
					   System.out.println("------------------------------------------------");
					   System.out.println("Iterating or looping map using java5 foreach loop");
					   System.out.println("key: " + prodName + " value: " + awsServiceBillItemMap.get(prodName).getCost());
					   if(prodName.equalsIgnoreCase("Amazon Elastic Compute Cloud") || 
							   prodName.equalsIgnoreCase("Amazon Simple Storage Service") ||
							   prodName.equalsIgnoreCase("Amazon DynamoDB") ||
							   prodName.equalsIgnoreCase("Amazon RDS Service")) {

						   AWSServiceBillItem aggregateServiceBill = new AWSServiceBillItem();
						   
						   if(prodName.equalsIgnoreCase("Amazon Elastic Compute Cloud")) {
							   aggregateServiceBill.setProductName("Compute Service");
						   }
						   else if(prodName.equalsIgnoreCase("Amazon Simple Storage Service")) {
							   aggregateServiceBill.setProductName("Storage Service");
						   }
						   else if(prodName.equalsIgnoreCase("Amazon DynamoDB")) {
							   aggregateServiceBill.setProductName("NoSQL Service");
						   }
						   else if(prodName.equalsIgnoreCase("Amazon RDS Service")) {
							   aggregateServiceBill.setProductName("SQL Service");
						   }
						   
						   aggregateServiceBill.setCost(awsServiceBillItemMap.get(prodName).getCost());
						   awsServiceCostItemList.add(aggregateServiceBill);

					   }   
				}
				ObjectMapper objMapper = new ObjectMapper();
				String jsonAwsServiceCostItemList = objMapper.writeValueAsString(awsServiceCostItemList);
				String jsonAwsServiceRateItemList = objMapper.writeValueAsString(awsServiceRateItemList);
				
				
				result[0] = jsonAwsServiceCostItemList;
				result[1] = jsonAwsServiceRateItemList;
				result[2] = totalCost.toString();
//				result1.concat(jsonAwsServiceRateItemList).concat(jsonAwsServiceCostItemList).concat(totalCost.toString());
				result1 = result[0]+'#'+result[1]+'#'+result[2];
				System.out.println("Total records parsed: "+recordCounter);
				System.out.println("Your total bill amount: "+totalCost);
				System.out.println(jsonAwsServiceCostItemList);
				System.out.println(jsonAwsServiceRateItemList);
				System.out.println("Result: "+result1);

			} 

			catch (Exception e) {
				System.out.println("Error in CsvFileReader !!!");
				e.printStackTrace();
			} finally {
				try {
					fileReader.close();
					csvFileParser.close();
				} catch (IOException e) {
					System.out.println("Error while closing fileReader/csvFileParser !!!");
					e.printStackTrace();
				}
			}

		
		
		
		return result1;
	}
}
