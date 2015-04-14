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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/*
 * Normal Flow for request processing:
 * 		Controller -> Facade -> Implementation -> AWS or Google
 */
@Path("/sql")
public class SqlController {

	SqlFacade sqlFacade = new SqlFacade();

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
			System.out.println("Inside SqlController - getUserName()");
			return sqlFacade.getUserName();			
		}
	
	/*
	 *  GET UserPassword at /sql
	 */

	@Path("/userpassword")
	@GET
	public String getUserPasword(@QueryParam("authKey") String authKey) {
		System.out.println("Inside SqlController - getUserPassword()");
		return sqlFacade.getUserPassword();		
	}
	
	/*
	 *  GET ConnectionString at /sql
	 */

	@Path("/connectionstring")
	@GET
	public String getConnectionString(@QueryParam("authKey") String authKey) {
		System.out.println("Inside SqlController - getPassword()");
		return sqlFacade.getConnectionString();		
	}
}