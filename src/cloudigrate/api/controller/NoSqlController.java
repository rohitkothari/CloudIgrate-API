/*
 * Author: Vaibhav Bhor
 * */
package cloudigrate.api.controller;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import cloudigrate.api.domain.Platform;
import cloudigrate.api.facade.NoSqlFacade;

@Path("/nosql")
public class NoSqlController {
	NoSqlFacade noSqlFacade = new NoSqlFacade();
	/*
	 * Put new Item in no SQL
	 * */	
	@Path("{item}")
	@PUT
	public String insertItem(@PathParam("item") String item,
			@PathParam("tableName") String tableName)
	{
		noSqlFacade.insertItem(item, tableName);
		return "Item inserted successfuly";
	}
	
	/*
	 * Get new Item from no SQL
	 * */
	@Path("{item}")
	@GET
	public String getItem(@PathParam("tableName") String tableName,
			@PathParam("attributeName") String attributeName,
			@PathParam("attributeValue") String attributeValue)
			{
				noSqlFacade.getItem(tableName, attributeName, attributeValue);
				return "Getting item successfully";
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
