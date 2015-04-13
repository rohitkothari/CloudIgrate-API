package cloudigrate.api.controller;


import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import cloudigrate.api.domain.Platform;
import cloudigrate.api.facade.DashboardFacade;

@Path("/admin")
public class AdminController {
	@Path("/platform")
	@GET
	public String getPlatform() {
		return "Get platform successfully";
	}
	
	@Path("/platform")
	@POST
	public String setPlatform(@PathParam("level") String level,
			@PathParam("value") String value) {
		Platform.getInstance().setPlatformValue(level, value);
		return "Setting platform successfully";
	}
	
	@Path("/dashboard")
	@GET
	public String getDashboard() {
		DashboardFacade dashboardFacade = new DashboardFacade();
		return dashboardFacade.getDashboardData();
		//return "Get dashboard successfully";
	}
	
}
