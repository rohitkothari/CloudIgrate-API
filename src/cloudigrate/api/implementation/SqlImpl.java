/*
 * Author: Ashutosh Folane
 */

package cloudigrate.api.implementation;
import cloudigrate.api.implementation.aws.*;
import cloudigrate.api.implementation.google.GoogleSql;

public class SqlImpl {

	AuthenticationImpl authenticationImpl = null;
	
	// ENUM to restrict cloud platform provider - You can just add new Cloud Platform Provider here in future
			public enum CloudPlatform {
				AWS, GOOGLE
			}
			
			public SqlImpl()
			{
				authenticationImpl = new AuthenticationImpl();
			}
			
			//authenticationImpl.getPreference("nosql")
			
	public String getUserName(){

		String userName=null;
		/* Business logic implementation
		 * Switch-case usage to implement operations related to cloud platform
		 */

		switch(authenticationImpl.getPreference("sql")) {

		case AWS: 
			System.out.println("Control going inside cloudigrate.api.implementation.aws");
			AWSSql awsSql = new AWSSql();
			return awsSql.getUserName();					

		case GOOGLE: 
			System.out.println("Control going inside cloudigrate.api.implementation.google");
			GoogleSql googleSql = new GoogleSql();
			return googleSql.getUserName();

		default:
			System.out.println("You have entered invalid business decision for cloud platform");
			System.out.println("Please verify your FACADE for CloudPlatform enum");
			break;
		}
		return userName;
	}

	public String getUserPassword() {

		String userPassword=null;
		/* Business logic implementation
		 * Switch-case usage to implement operations related to cloud platform
		 */

		switch(authenticationImpl.getPreference("sql")) {

		case AWS: 
			System.out.println("Control going inside cloudigrate.api.implementation.aws");
			AWSSql awsSql = new AWSSql();
			return awsSql.getUserPassword();					

		case GOOGLE: 
			System.out.println("Control going inside cloudigrate.api.implementation.google");
			GoogleSql googleSql = new GoogleSql();
			return googleSql.getUserPassword();					

		default:
			System.out.println("You have entered invalid business decision for cloud platform");
			System.out.println("Please verify your FACADE for CloudPlatform enum");
			break;
		}
		return userPassword;
	}

	public String getConnectionString() {
		String connectionString=null;
		/* Business logic implementation
		 * Switch-case usage to implement operations related to cloud platform
		 */

		switch(authenticationImpl.getPreference("sql")) {

		case AWS: 
			System.out.println("Control going inside cloudigrate.api.implementation.aws");
			AWSSql awsSql = new AWSSql();
			return awsSql.getConnectionString();					

		case GOOGLE: 
			System.out.println("Control going inside cloudigrate.api.implementation.google");
			GoogleSql googleSql = new GoogleSql();
			return googleSql.getConnectionString();		

		default:
			System.out.println("You have entered invalid business decision for cloud platform");
			System.out.println("Please verify your FACADE for CloudPlatform enum");
			break;
		}
		return connectionString;
	}
}
