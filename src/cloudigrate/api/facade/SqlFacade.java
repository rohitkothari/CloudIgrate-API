/*
 * Author: Ashutosh Folane
 */

package cloudigrate.api.facade;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import cloudigrate.api.implementation.*;

public class SqlFacade {

	/* Part of business logic - To decide which cloud platform to use
	 *	 
	 *	Currently supporting the following Cloud Platform Providers-
	 *		1. Amazon Web Services
	 *		2. Google Cloud Platform
	 */

	// ENUM to restrict cloud platform provider - You can just add new Cloud Platform Provider here in future
	public enum CloudPlatform {
		AWS, GOOGLE
	}
	private static CloudPlatform cloudPlatform;


	// Constructor that sets a particular Cloud Platform Provider 
	public SqlFacade() {
		//this.cloudPlatform = cloudPlatform.AWS;
		this.cloudPlatform = cloudPlatform.GOOGLE;
	}

	SqlImpl sqlImpl = new SqlImpl();

	/*
	 *  Method to get UserName in the pre-configured cloud platform using CloudPlatform enum
	 */
	public String getUserName() {
		System.out.println("Inside SqlFacade - getUserName()");
		// Invoke SqlImpl's getUserName() method
		String userName = sqlImpl.getUserName(cloudPlatform);
		return userName;
	}	
	
	public String getUserPassword() {
		System.out.println("Inside SqlFacade - getUserPassword()");
		// Invoke SqlImpl's getUserPassword() method
		String userUserPassword = sqlImpl.getUserPassword(cloudPlatform);
		return userUserPassword;
	}
	
	public String getConnectionString() {
		System.out.println("Inside SqlFacade - getConnectionString()");
		// Invoke SqlImpl's createBucket() method
		String connectionString = sqlImpl.getConnectionString(cloudPlatform);
		return connectionString;
	}
}
