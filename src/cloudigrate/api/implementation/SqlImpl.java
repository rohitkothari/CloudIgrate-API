/*
 * Author: Ashutosh Folane
 */

package cloudigrate.api.implementation;

import cloudigrate.api.facade.SqlFacade.CloudPlatform;
import cloudigrate.api.implementation.aws.*;
import cloudigrate.api.implementation.google.GoogleSql;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SqlImpl {

	public String getUserName(CloudPlatform cloudPlatform){

		System.out.println("Inside SqlImpl - getUserName() "+cloudPlatform);
		String userName=null;
		/* Business logic implementation
		 * Switch-case usage to implement operations related to cloud platform
		 */

		switch(cloudPlatform) {

		case AWS: 
			System.out.println("Control going inside cloudigrate.api.implementation.aws");
			AWSSql awsSql = new AWSSql();
			userName = awsSql.getUserName();
			return userName;
		

		case GOOGLE: 
			System.out.println("Control going inside cloudigrate.api.implementation.google");
			GoogleSql googleSql = new GoogleSql();
			userName = googleSql.getUserName();
			return userName;
		

		default:
			System.out.println("You have entered invalid business decision for cloud platform");
			System.out.println("Please verify your FACADE for CloudPlatform enum");
			break;
		}
		return userName;
	}

	public String getUserPassword(CloudPlatform cloudPlatform) {

		System.out.println("Inside SqlImpl - getUserPassword() "+cloudPlatform);
		String userPassword=null;
		/* Business logic implementation
		 * Switch-case usage to implement operations related to cloud platform
		 */

		switch(cloudPlatform) {

		case AWS: 
			System.out.println("Control going inside cloudigrate.api.implementation.aws");
			AWSSql awsSql = new AWSSql();
			userPassword = awsSql.getUserPassword();
			return userPassword;
		

		case GOOGLE: 
			System.out.println("Control going inside cloudigrate.api.implementation.google");
			GoogleSql googleSql = new GoogleSql();
			userPassword = googleSql.getUserPassword();
			return userPassword;
		

		default:
			System.out.println("You have entered invalid business decision for cloud platform");
			System.out.println("Please verify your FACADE for CloudPlatform enum");
			break;
		}
		return userPassword;
	}

	public String getConnectionString(CloudPlatform cloudPlatform) {
		System.out.println("Inside SqlImpl - getConnectionString() "+cloudPlatform);
		String connectionString=null;
		/* Business logic implementation
		 * Switch-case usage to implement operations related to cloud platform
		 */

		switch(cloudPlatform) {

		case AWS: 
			System.out.println("Control going inside cloudigrate.api.implementation.aws");
			AWSSql awsSql = new AWSSql();
			connectionString = awsSql.getConnectionString();
			return connectionString;
		

		case GOOGLE: 
			System.out.println("Control going inside cloudigrate.api.implementation.google");
			GoogleSql googleSql = new GoogleSql();
			connectionString = googleSql.getConnectionString();
			return connectionString;
		

		default:
			System.out.println("You have entered invalid business decision for cloud platform");
			System.out.println("Please verify your FACADE for CloudPlatform enum");
			break;
		}
		return connectionString;
	}
}
