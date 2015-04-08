/*
 * Author: Rohit Kothari
 */

package cloudigrate.api.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import cloudigrate.api.facade.*;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
	
	/*
	 * 	Upload a new object in a specific bucket}
	 */
	@Path("upload/{bucket}")
	@PUT
	public String uploadObject(@PathParam("bucket") String bucket, File object) {

		System.out.println("Inside StorageController - uploadObject() with params:"+object.getName()+"-->"+bucket);
		storageFacade.uploadObject(bucket, object.getName(), object);
		return "Object uploaded successfully";
	}
	
	/*
	 * 	Download a specific object from a specific bucket to a specified user location}
	 */
	@Path("{bucket}")
	@GET
	public File downloadObject(@PathParam("bucket") String bucket, String keyName, String downloadPath) throws FileNotFoundException, IOException {

		System.out.println("Inside StorageController - downloadObject() with params: downloading "+keyName+" from "+bucket);
		File object = storageFacade.downloadObject(bucket, keyName, downloadPath);
		return object;
	}

	/*
	 * 	Upload a new object in a specific bucket}
	 */
	@Path("{bucket}/{keyName}")
	@DELETE
	public String deleteObject(@PathParam("bucket") String bucket, @PathParam("keyName") String keyName) {

		System.out.println("Inside StorageController - deleteObject() with params: deleting "+keyName+"-->"+bucket);
		storageFacade.deleteObject(bucket, keyName);
		return "Object deleted successfully";
	}
}