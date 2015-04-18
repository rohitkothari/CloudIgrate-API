package cloudigrate.api.controller;


import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.ClientResponse;

import cloudigrate.api.facade.AuthenticationFacade;
import cloudigrate.api.facade.DashboardFacade;

@Path("/admin")
public class AdminController {
	
	AuthenticationFacade authenticationFacade = new AuthenticationFacade();
	
	@Path("/platform")
	@GET
	public String getPlatform() {
		String jsonString = null;
		ObjectMapper objMapper = new ObjectMapper();
		try {
			jsonString = objMapper.writeValueAsString(authenticationFacade.getPlatformValues());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonString;
	}
	
	@Path("/platform")
	@POST
	public String setPlatform(@QueryParam("level") String level,
			@QueryParam("value") String value) {
		authenticationFacade.setPlatformValue(level, value);
		return "Setting platform successfully";
	}
	
	@Path("/dashboard")
	@GET
	public String getDashboard() {
		DashboardFacade dashboardFacade = new DashboardFacade();
		return dashboardFacade.getDashboardData();
	}
	
}
