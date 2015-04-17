/*
 * Author: Vaibhav Bhor
 * */
package cloudigrate.api.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import cloudigrate.api.domain.Logger;
import cloudigrate.api.domain.Platform;
import cloudigrate.api.facade.AuthenticationFacade;
import cloudigrate.api.facade.NoSqlFacade;

@Path("/nosql")
public class NoSqlController {
	NoSqlFacade noSqlFacade = new NoSqlFacade();
	AuthenticationFacade authFacade = new AuthenticationFacade();
	Logger logger = null;
	HashMap<String, String> myMap = null;
	Iterator iterator = null;
	int keyId = 0;
	Map.Entry pair = null;
	/*
	 * Put new Item in no SQL
	 * */	
	@Path("insert")
	@PUT
	public String insertItem(@QueryParam("authKey") String authKey){
		System.out.println("AuthKey from query: "+authKey);
		keyId = authFacade.isValidKey(authKey);
		if(keyId > 0)
		{
		logger = Logger.getInstance();
		logger.setStart(new Date());
			myMap = authFacade.getLogInfo(keyId);
			
			System.out.println("Start is " + logger.getStart());
		//	System.out
		//		.println("Inside NoSQLController - insertItem() with params:" + item);
		String item = "test";
		String tableName = "test";
		noSqlFacade.insertItem(item, tableName);
		logger.setEnd(new Date());
		System.out.println("ENd is " + logger.getEnd());
		
		iterator = myMap.entrySet().iterator();
	    while (iterator.hasNext()) {
	    	pair = (Map.Entry)iterator.next();
	        logger.writeLogger(pair.getKey().toString(), pair.getValue().toString(), "insertItem", "AWS", "nosql", "PaaS");
	        iterator.remove(); // avoids a ConcurrentModificationException
	    }
		return "Item inserted successfuly";
		}
		return "Key is Invalid";
	}
	
	/*
	 * Get new Item from no SQL
	 * */
	@Path("{item}")
	@GET
	public String getItem(@PathParam("tableName") String tableName,
			@PathParam("attributeName") String attributeName,
			@PathParam("attributeValue") String attributeValue,
			@QueryParam("authKey") String authKey) {
		keyId = authFacade.isValidKey(authKey);
		if(keyId > 0)
		{
		logger = Logger.getInstance();
		logger.setStart(new Date());
			myMap = authFacade.getLogInfo(keyId);
			
			System.out.println("Start is " + logger.getStart());
			System.out
				.println("Inside NoSQLController - insertItem() with params:" + tableName);
				noSqlFacade.getItem(tableName, attributeName, attributeValue);
				logger.setEnd(new Date());
				System.out.println("ENd is " + logger.getEnd());
				
				iterator = myMap.entrySet().iterator();
			    while (iterator.hasNext()) {
			    	pair = (Map.Entry)iterator.next();
			        logger.writeLogger(pair.getKey().toString(), pair.getValue().toString(), "getItem", "AWS", "nosql", "PaaS");
			        iterator.remove(); // avoids a ConcurrentModificationException
			    }
				return "Getting item successfully";
	}
	return "Key is Invalid";
}
	
	/*@Path("{item}")
	@DELETE
	public String deleteItem(@PathParam("tableName") String tableName,
			@PathParam("attributeName") String attributeName,
			@PathParam("attributeValue") String attributeValue)
			{
				noSqlFacade.deleteItem(tableName, attributeName, attributeValue);
				return null;
			}*/
}		
