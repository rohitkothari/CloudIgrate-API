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
	
	SqlImpl sqlImpl = new SqlImpl();

	/*
	 *  Method to get UserName in the pre-configured cloud platform using CloudPlatform enum
	 */
	public String getUserName() {
		System.out.println("Inside SqlFacade - getUserName()");
		// Invoke SqlImpl's getUserName() method
		return  sqlImpl.getUserName();
	}	
	
	public String getUserPassword() {
		System.out.println("Inside SqlFacade - getUserPassword()");
		// Invoke SqlImpl's getUserPassword() method
		return sqlImpl.getUserPassword();		
	}
	
	public String getConnectionString() {
		System.out.println("Inside SqlFacade - getConnectionString()");
		// Invoke SqlImpl's createBucket() method
		return sqlImpl.getConnectionString();		
	}
}
