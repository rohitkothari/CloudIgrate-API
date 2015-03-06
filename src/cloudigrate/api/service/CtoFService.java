package cloudigrate.api.service;

import cloudigrate.api.impl.StorageImpl;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
 
@Path("/storage")
public class CtoFService {
	/*@GET
	@Produces("application/xml")
	public String convertCtoF() {
 
		Double fahrenheit;
		Double celsius = 36.8;
		fahrenheit = ((celsius * 9) / 5) + 32;
		System.out.println("Test");
		String result = "@Produces(\"application/xml\") Output: \n\nC to F Converter Output: \n\n" + fahrenheit;
		return "<ctofservice>" + "<celsius>" + celsius + "</celsius>" + "<ctofoutput>" + result + "</ctofoutput>" + "</ctofservice>";
	} */
	
	@GET
	public Response convertCtoF() {
 
		System.out.println("Welcome to CloudIgrate-Storage");
		return Response.status(200).entity("Welcome to CloudIgrate-Storage").build();
	}
	
 /*
	@Path("{c}")
	@GET
	@Produces("application/xml")
	public String convertCtoFfromInput(@PathParam("c") Double c) {
		Double fahrenheit;
		Double celsius = c;
		fahrenheit = ((celsius * 9) / 5) + 32;
 
		String result = "@Produces(\"application/xml\") Output: \n\nC to F Converter Output: \n\n" + fahrenheit;
		return "<ctofservice>" + "<celsius>" + celsius + "</celsius>" + "<ctofoutput>" + result + "</ctofoutput>" + "</ctofservice>";
	}*/
	
	@Path("{bucket}")
	@GET
	public String createBucket(@PathParam("bucket") String bucket) {
		
		System.out.println("Inside bucket:"+bucket);
		StorageImpl storage = new StorageImpl();
		storage.authenticateAWS();
		storage.createAWSBucket(bucket);
		return "Bucket created successfully";
	}
}