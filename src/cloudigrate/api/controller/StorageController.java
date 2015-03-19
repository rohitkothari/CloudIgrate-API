/*
 * Author: Rohit Kothari
 */

package cloudigrate.api.controller;

import cloudigrate.api.facade.*;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/*
 * Controller for delegating all requests pertaining to STORAGE component of CloudIgrate.net
 * Note: If you are working on another component like SQL, NOSQL, etc. please create your own controller 
 * 		 Also make sure you create your own flow from Controller to Implementation.
 * 
 * Normal Flow for request processing:
 * 		Controller -> Facade -> Implementation -> AWS or Google
 */
@Path("/storage")
public class StorageController {

	StorageFacade storageFacade = new StorageFacade();


	/*
	 *  GET WelcomePage at /storage
	 */
	@GET
	public Response welcomeCloudIgrate() {

		System.out.println("Welcome to CloudIgrate-Storage");
		return Response.status(200).entity("Welcome to CloudIgrate-Storage").build();
	}

	/*
	 * 	Create a new bucket at /storage/{bucketName}
	 */
	@Path("{bucket}")
	@PUT
	public String createBucket(@PathParam("bucket") String bucket) {

		System.out.println("Inside StorageController - createBucket() with params:"+bucket);

		storageFacade.createBucket(bucket);

		return "Bucket created successfully";
	}

}