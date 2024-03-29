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
	@Path("item")
	@PUT
	public String insertItem(@QueryParam("authKey") String authKey,
			@QueryParam("attributeName") String attributeName,
			@QueryParam("attributeValue") String attributeValue){
		System.out.println("AuthKey from query: "+authKey);
		String noSqlKey = null;
		keyId = authFacade.isValidKey(authKey);
		if(keyId > 0)
		{
		logger = Logger.getInstance();
		logger.setStart(new Date());
			myMap = authFacade.getLogInfo(keyId);
			
			System.out.println("Start is " + logger.getStart());
		//	System.out
		//		.println("Inside NoSQLController - insertItem() with params:" + item);
		noSqlKey= noSqlFacade.insertItem(attributeName, attributeValue);
		logger.setEnd(new Date());
		System.out.println("ENd is " + logger.getEnd());
		
		iterator = myMap.entrySet().iterator();
	    while (iterator.hasNext()) {
	    	pair = (Map.Entry)iterator.next();
	        logger.writeLogger(pair.getKey().toString(), pair.getValue().toString(), "insertItem", "AWS", "nosql", "PaaS");
	        iterator.remove(); // avoids a ConcurrentModificationException
	    }
		return noSqlKey;
		}
		return "Key is Invalid";
	}
	
	/*
	 * Get new Item from no SQL
	 * */
	@Path("item")
	@GET
	public String getItem(@QueryParam("keyValue") String keyValue,
			@QueryParam("authKey") String authKey) {
		keyId = authFacade.isValidKey(authKey);
		String jsonItems = null;
		if(keyId > 0)
		{
		logger = Logger.getInstance();
		logger.setStart(new Date());
			myMap = authFacade.getLogInfo(keyId);
			
			System.out.println("Start is " + logger.getStart());
			System.out
				.println("Inside NoSQLController - insertItem() with params:" + keyValue);
				jsonItems = noSqlFacade.getItem(keyValue);
				logger.setEnd(new Date());
				System.out.println("ENd is " + logger.getEnd());
				
				iterator = myMap.entrySet().iterator();
			    while (iterator.hasNext()) {
			    	pair = (Map.Entry)iterator.next();
			        logger.writeLogger(pair.getKey().toString(), pair.getValue().toString(), "getItem", "AWS", "nosql", "PaaS");
			        iterator.remove(); // avoids a ConcurrentModificationException
			    }
				return jsonItems;
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
