package cloudigrate.api.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import cloudigrate.api.facade.DashboardFacade;

@Path("/user")
public class UserController {

	@Path("dashboard/username/{username}")
	@GET
	public String getUserDashboard(@PathParam("username") String username)
	{
		DashboardFacade dashboardFacade = new DashboardFacade();
		return dashboardFacade.getUserDashboard(username);
	}
	
	@Path("dashboard/service/{service}")
	@GET
	public String getServiceDashboard(@PathParam("service") String service)
	{
		DashboardFacade dashboardFacade = new DashboardFacade();
		return dashboardFacade.getServiceDashboard(service);
	}
}
