/*
 * Author: Vaibhav Bhor
 * */
package cloudigrate.api.implementation;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.services.datastore.client.DatastoreException;

import cloudigrate.api.domain.Platform;
import cloudigrate.api.facade.NoSqlFacade.CloudPlatform;
import cloudigrate.api.implementation.aws.AWSNoSql;
import cloudigrate.api.implementation.google.GoogleNoSql;

public class NoSqlImpl {

	public String insertItem(String attributeName, String attributeValue, CloudPlatform cloudPlatform) throws GeneralSecurityException, IOException, DatastoreException {
		// TODO Auto-generated method stub
		String key = null;
		//switch(Platform.getInstance().getPlatformValue("nosql")) {
		switch(cloudPlatform) {

		case AWS: 
			System.out.println("Control going inside cloudigrate.api.implementation.aws");
			AWSNoSql awsNoSql = new AWSNoSql();
			key = awsNoSql.insertItem(attributeName, attributeValue);
			break;

		case GOOGLE: 
			System.out.println("Control going inside cloudigrate.api.implementation.google");
			GoogleNoSql googleNoSql = new GoogleNoSql();
			googleNoSql.insertItem(attributeName, attributeValue);
			break;

		default:
			System.out.println("You have entered invalid business decision for cloud platform");
			System.out.println("Please verify your FACADE for CloudPlatform enum");
			break;
		}
		return key;
	}

	public String getItem(String keyValue, CloudPlatform cloudPlatform) throws DatastoreException, GeneralSecurityException, IOException {
		List<Map<String, AttributeValue>> attributes = null;
		String jsonString= null;
		//switch(Platform.getInstance().getPlatformValue("nosql")) {
		switch(cloudPlatform){

		case AWS: 
			System.out.println("Control going inside cloudigrate.api.implementation.aws");
			AWSNoSql awsNoSql = new AWSNoSql();
			attributes = awsNoSql.getItem(keyValue);
			ObjectMapper objMapper = new ObjectMapper();
			try {
				jsonString = objMapper.writeValueAsString(attributes);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//List<Map<String, AttributeValue>> getItem(String keyValue)
			break;

		case GOOGLE: 
			System.out.println("Control going inside cloudigrate.api.implementation.google");
			GoogleNoSql googleNoSql = new GoogleNoSql();
			googleNoSql.getItem("", "", keyValue);
			break;

		default:
			System.out.println("You have entered invalid business decision for cloud platform");
			System.out.println("Please verify your FACADE for CloudPlatform enum");
			break;
		}
		// TODO Auto-generated method stub
		return jsonString;
	}
	
	/*public void deleteItem(String tableName, String attributeName, String attributeValue, CloudPlatform cloudPlatform) throws GeneralSecurityException, IOException
	{
		switch(cloudPlatform){
		
		case AWS:
			System.out.println("Control going inside cloudigrate.api.implementation.aws");
			AWSNoSql awsNoSql = new AWSNoSql();
			awsNoSql.deleteItem(tableName, attributeName, attributeValue);
			break;
		
		case GOOGLE:
			System.out.println("Control going inside cloudigrate.api.implementation.google");
			GoogleNoSql googleNoSql = new GoogleNoSql();
			googleNoSql.deleteItem(tableName, attributeName);
			break;
			
		default:
			System.out.println("You have entered invalid business decision for cloud platform");
			System.out.println("Please verify your FACADE for CloudPlatform enum");
			break;
		}
	}*/
}
