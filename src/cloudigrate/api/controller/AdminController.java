package cloudigrate.api.controller;


import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/admin")
public class AdminController {
	
	@Path("/platform")
	@GET
	public String getPlatform() {
		return "Get platform successfully";
	}
	
	@Path("/platform")
	@POST
	public String setPlatform(@PathParam("platform") String platform) {
		return "Setting platform successfully";
	}

}
