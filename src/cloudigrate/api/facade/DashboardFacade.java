package cloudigrate.api.facade;

import cloudigrate.api.implementation.DashboardImpl;

public class DashboardFacade {

	DashboardImpl dashboardImpl = new DashboardImpl();
	
	public String getDashboardData()
	{
		return dashboardImpl.getDashboardData();
	}
	
}
