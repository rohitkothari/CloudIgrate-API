/*
 * Author: Vaibhav Bhor
 * */
package cloudigrate.api.implementation;

import java.io.IOException;
import java.security.GeneralSecurityException;

import com.google.api.services.datastore.client.DatastoreException;

import cloudigrate.api.domain.Platform;
import cloudigrate.api.implementation.aws.AWSNoSql;
import cloudigrate.api.implementation.google.GoogleNoSql;

public class NoSqlImpl {

	public void insertItem(String item, String tableName) throws GeneralSecurityException, IOException, DatastoreException {
		// TODO Auto-generated method stub
		
		switch(Platform.getInstance().getPlatformValue("nosql")) {

		case AWS: 
			System.out.println("Control going inside cloudigrate.api.implementation.aws");
			AWSNoSql awsNoSql = new AWSNoSql();
			awsNoSql.insertItem(item, tableName);
			break;

		case GOOGLE: 
			System.out.println("Control going inside cloudigrate.api.implementation.google");
			GoogleNoSql googleNoSql = new GoogleNoSql();
			googleNoSql.insertItem(item, tableName);
			break;

		default:
			System.out.println("You have entered invalid business decision for cloud platform");
			System.out.println("Please verify your FACADE for CloudPlatform enum");
			break;
		}
	}

	public void getItem(String tableName, String attributeName,
			String attributeValue) throws DatastoreException, GeneralSecurityException, IOException {
		switch(Platform.getInstance().getPlatformValue("nosql")) {

		case AWS: 
			System.out.println("Control going inside cloudigrate.api.implementation.aws");
			AWSNoSql awsNoSql = new AWSNoSql();
			awsNoSql.getItem(tableName, attributeName, attributeValue);
			break;

		case GOOGLE: 
			System.out.println("Control going inside cloudigrate.api.implementation.google");
			GoogleNoSql googleNoSql = new GoogleNoSql();
			googleNoSql.getItem(tableName, attributeName, attributeValue);
			break;

		default:
			System.out.println("You have entered invalid business decision for cloud platform");
			System.out.println("Please verify your FACADE for CloudPlatform enum");
			break;
		}
		// TODO Auto-generated method stub
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
