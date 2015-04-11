/*
 * Author: Rohit Kothari
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
	
	public AWSSql()
	{
		final String userName = "";
		final String password = "";
		final String connectionString = "";
	}
		// Lowest-level getUserName() method that makes an API call to AWS Console using RDS endpoint
		public String getUserName()
		{
			System.out.println("Inside AWSSql - getUserName()");
			String userName = ""; 
			return userName;
			
		}
		
		public String getUserPassword()
		{
			System.out.println("Inside AWSSql - getUserPassword()");
			String userPassword = ""; 
			return userPassword;
			
		}
		
		public String getConnectionString()
		{
			System.out.println("Inside AWSSql - getConnectionString()");
			String connectionString = ""; 
			return connectionString;
			
		}
}