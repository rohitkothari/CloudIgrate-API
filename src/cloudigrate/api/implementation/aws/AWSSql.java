/*
 * Author: Ashutosh Folane
 */

package cloudigrate.api.implementation.aws;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;

import com.amazonaws.auth.AWSCredentials;

import cloudigrate.api.implementation.aws.InitializeAWS;

/*
 *  Lower level implementation class for SQL - AWS (RDS)
 */
public class AWSSql {
	
	String userName;
	String userPassword;
	String connectionString;
	
	public AWSSql()
	{
		userName = "cloudigrate";
		userPassword = "cloudigrate";
		connectionString = "cloudigrate.c7babyv2jgia.us-west-2.rds.amazonaws.com:3306/airbox";
	}
		// Lowest-level getUserName() method that makes an API call to AWS Console using RDS endpoint
		public String getUserName()
		{
			System.out.println("Inside AWSSql - getUserName()");				
			return userName;			
		}
		
		public String getUserPassword()
		{
			System.out.println("Inside AWSSql - getUserPassword()");			
			return userPassword;
			
		}
		
		public String getConnectionString()
		{
			System.out.println("Inside AWSSql - getConnectionString()");			
			return connectionString;			
		}
}