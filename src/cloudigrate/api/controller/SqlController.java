/*
 * Author: Ashutosh Folane
 */

package cloudigrate.api.controller;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import cloudigrate.api.domain.Logger;
import cloudigrate.api.facade.*;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/*
 * Normal Flow for request processing:
 * 		Controller -> Facade -> Implementation -> AWS or Google
 */
@Path("/sql")
public class SqlController {

	SqlFacade sqlFacade = new SqlFacade();
	AuthenticationFacade authFacade = new AuthenticationFacade();
	Logger logger = null;
	HashMap<String, String> myMap = null;
	Iterator iterator = null;
	int keyId = 0;
	Map.Entry pair = null;

	/*
	 *  GET WelcomePage at /sql
	 */
	@GET
	public Response welcomeCloudIgrate() {

		System.out.println("Welcome to CloudIgrate-Sql");
		return Response.status(200).entity("Welcome to CloudIgrate-Sql").build();
	}
	
	/*
	 *  GET UserName at /sql
	 */

	@Path("/username")
	@GET
	public String getUserName(@QueryParam("authKey") String authKey) {
		keyId = authFacade.isValidKey(authKey);
		String username = null;
		if(keyId > 0)
		{
		logger = Logger.getInstance();
		logger.setStart(new Date());
			myMap = authFacade.getLogInfo(keyId);
			
			System.out.println("Start is " + logger.getStart());
			System.out.println("Inside SqlController - getUserName()");
			username = sqlFacade.getUserName();
			logger.setEnd(new Date());
			System.out.println("ENd is " + logger.getEnd());
			
			iterator = myMap.entrySet().iterator();
		    while (iterator.hasNext()) {
		    	pair = (Map.Entry)iterator.next();
		        logger.writeLogger(pair.getKey().toString(), pair.getValue().toString(), "getUserName", "AWS", "sql", "PaaS");
		        iterator.remove(); // avoids a ConcurrentModificationException
		    }
		}
		return username;
	}
	
	/*
	 *  GET UserPassword at /sql
	 */

	@Path("/password")
	@GET
	public String getUserPasword(@QueryParam("authKey") String authKey) {
		keyId = authFacade.isValidKey(authKey);
		String password = null;
		if(keyId > 0)
		{
		logger = Logger.getInstance();
		logger.setStart(new Date());
			myMap = authFacade.getLogInfo(keyId);
			
			System.out.println("Start is " + logger.getStart());
		System.out.println("Inside SqlController - getUserPassword()");
		password = sqlFacade.getUserPassword();
		iterator = myMap.entrySet().iterator();
	    while (iterator.hasNext()) {
	    	pair = (Map.Entry)iterator.next();
	        logger.writeLogger(pair.getKey().toString(), pair.getValue().toString(), "getUserPasword", "AWS", "sql", "PaaS");
	        iterator.remove(); // avoids a ConcurrentModificationException
	    }
		}
		return password;
	}
	
	/*
	 *  GET ConnectionString at /sql
	 */

	@Path("/connectionstring")
	@GET
	public String getConnectionString(@QueryParam("authKey") String authKey) {
		keyId = authFacade.isValidKey(authKey);
		String connectionString = null;
		if(keyId > 0)
		{
		logger = Logger.getInstance();
		logger.setStart(new Date());
			myMap = authFacade.getLogInfo(keyId);
			
			System.out.println("Start is " + logger.getStart());
		System.out.println("Inside SqlController - getConnectionString()");
		connectionString=  sqlFacade.getConnectionString();		
		iterator = myMap.entrySet().iterator();
	    while (iterator.hasNext()) {
	    	pair = (Map.Entry)iterator.next();
	        logger.writeLogger(pair.getKey().toString(), pair.getValue().toString(), "getConnectionString", "AWS", "sql", "PaaS");
	        iterator.remove(); // avoids a ConcurrentModificationException
	    }
		}
		return connectionString;
	}
}