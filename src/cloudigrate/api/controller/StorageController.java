/*
 * Author: Rohit Kothari
 */

package cloudigrate.api.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import cloudigrate.api.domain.Logger;
import cloudigrate.api.facade.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.*;

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
	Logger logger = null;

	/*
	 * GET WelcomePage at /storage
	 */
	@GET
	public Response welcomeCloudIgrate() {

		System.out.println("Welcome to CloudIgrate-Storage");
		return Response.status(200).entity("Welcome to CloudIgrate-Storage")
				.build();
	}

	/*
	 * Create a new bucket at /storage/{bucketName}
	 */
	@Path("{bucket}")
	@PUT
	public String createBucket(@PathParam("bucket") String bucket,
			@QueryParam("authKey") String authKey) {
		System.out.println("AuthKey from query: "+authKey);
		logger = Logger.getInstance();
		logger.setStart(new Date());
		AuthenticationFacade authFacade = new AuthenticationFacade();
		HashMap<String, String> myMap = null;
		Iterator iterator = null;
		int keyId = authFacade.isValidKey(authKey);
		if(keyId > 0)
		{
			myMap = authFacade.getLogInfo(keyId);
			
			System.out.println("Start is " + logger.getStart());
			System.out
				.println("Inside StorageController - createBucket() with params:"
						+ bucket);
			storageFacade.createBucket(bucket);
			logger.setEnd(new Date());
			System.out.println("ENd is " + logger.getEnd());
			
			iterator = myMap.entrySet().iterator();
		    while (iterator.hasNext()) {
		    	Map.Entry pair = (Map.Entry)iterator.next();
		        logger.writeLogger(pair.getKey().toString(), pair.getValue().toString(), "createBucket", "AWS", "PaaS");
		        iterator.remove(); // avoids a ConcurrentModificationException
		    }
			
			return "Bucket created successfully";
		}
		return "Key is not Valid";
	}

	/*
	 * REST Updated code: Upload a new object in a specific bucket}
	 */
	@Path("upload/{bucket}")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@POST
	public String uploadObject(@PathParam("bucket") String bucket,
			@FormDataParam("file") File fileobject) {
		logger = Logger.getInstance();
		logger.setStart(new Date());
		System.out.println("Start is " + logger.getStart());
		System.out
				.println("Inside StorageController - uploadObject() with params:"
						+ fileobject.getName() + "-->" + bucket);
		storageFacade.uploadObject(bucket, fileobject.getName(), fileobject);
		logger.setEnd(new Date());
		System.out.println("ENd is " + logger.getEnd());
		logger.writeLogger("Vab", "applicatioName", "createBucket", "AWS",
				"PaaS");
		return "Object uploaded successfully";
	}

	/*
	 * @Path("upload/{bucket}")
	 * 
	 * @PUT public String uploadObject(@PathParam("bucket") String bucket, File
	 * object) {
	 * 
	 * System.out.println("Inside StorageController - uploadObject() with params:"
	 * +object.getName()+"-->"+bucket); storageFacade.uploadObject(bucket,
	 * object.getName(), object); return "Object uploaded successfully"; }
	 */

	/*
	 * Download a specific object from a specific bucket to a specified user
	 * location}
	 */
	@Path("{bucket}")
	@GET
	public File downloadObject(@PathParam("bucket") String bucket,
			String keyName, String downloadPath) throws FileNotFoundException,
			IOException {
		logger = Logger.getInstance();
		logger.setStart(new Date());
		System.out.println("Start is " + logger.getStart());
		System.out
				.println("Inside StorageController - downloadObject() with params: downloading "
						+ keyName + " from " + bucket);
		File object = storageFacade.downloadObject(bucket, keyName,
				downloadPath);
		logger.setEnd(new Date());
		System.out.println("ENd is " + logger.getEnd());
		logger.writeLogger("Vab", "applicatioName", "createBucket", "AWS",
				"PaaS");
		return object;
	}

	/*
	 * Upload a new object in a specific bucket}
	 */
	@Path("{bucket}/{keyName}")
	@DELETE
	public String deleteObject(@PathParam("bucket") String bucket,
			@PathParam("keyName") String keyName) {
		logger = Logger.getInstance();
		logger.setStart(new Date());
		System.out.println("Start is " + logger.getStart());
		System.out
				.println("Inside StorageController - deleteObject() with params: deleting "
						+ keyName + "-->" + bucket);
		storageFacade.deleteObject(bucket, keyName);
		logger.setEnd(new Date());
		System.out.println("ENd is " + logger.getEnd());
		logger.writeLogger("Vab", "applicatioName", "createBucket", "AWS",
				"PaaS");
		return "Object deleted successfully";
	}
}