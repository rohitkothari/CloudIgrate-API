package cloudigrate.api.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

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
	
	@Path("dashboard/billing")
	@GET
	public String getBillingDashboard()
	{
		DashboardFacade dashboardFacade = new DashboardFacade();
		return dashboardFacade.getBillingDashboard();
	}
	
	@Path("dashboard/serviceaverage")
	@GET
	public String getServiceAverage(@QueryParam("userName") String userName)
	{
		DashboardFacade dashboardFacade = new DashboardFacade();
		return dashboardFacade.getServiceAverage(userName);
	}
	
	@Path("dashboard/servicecount")
	@GET
	public String getServiceCount(@QueryParam("userName") String userName)
	{
		DashboardFacade dashboardFacade = new DashboardFacade();
		return dashboardFacade.getServiceCount(userName);
	}
	
	@Path("dashboard/levelcount")
	@GET
	public String getLavelCount(@QueryParam("userName") String userName)
	{
		DashboardFacade dashboardFacade = new DashboardFacade();
		return dashboardFacade.getLevelCount(userName);
	}
}
