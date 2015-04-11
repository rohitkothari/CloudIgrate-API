/*
 * Author: Ashutosh Folane
 */

package cloudigrate.api.controller;

import java.io.File;

import cloudigrate.api.facade.*;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/*
 * Normal Flow for request processing:
 * 		Controller -> Facade -> Implementation -> AWS or Google
 */
@Path("/sql")
public class SqlController {

	StorageFacade storageFacade = new StorageFacade();

	/*
	 *  GET Username at /sql
	 */
	@GET
	public Response welcomeCloudIgrate() {

		System.out.println("Welcome to CloudIgrate-Sql");
		return Response.status(200).entity("Welcome to CloudIgrate-Sql").build();
	}

	/*
	 *  GET Password at /sql
	 */
	@GET
	public Response welcomeCloudIgrate() {

		System.out.println("Welcome to CloudIgrate-Sql");
		return Response.status(200).entity("Welcome to CloudIgrate-Sql").build();
	}

	/*
	 *  GET String at /sql
	 */
	@GET
	public Response welcomeCloudIgrate() {

		System.out.println("Welcome to CloudIgrate-Sql");
		return Response.status(200).entity("Welcome to CloudIgrate-Sql").build();
	}

}