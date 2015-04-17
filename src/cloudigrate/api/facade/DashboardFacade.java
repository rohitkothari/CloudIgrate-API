package cloudigrate.api.facade;

import cloudigrate.api.implementation.DashboardImpl;

public class DashboardFacade {

	DashboardImpl dashboardImpl = new DashboardImpl();
	
	public String getDashboardData()
	{
		return dashboardImpl.getDashboardData();
	}

	public String getUserDashboard(String username) {
		// TODO Auto-generated method stub
		return dashboardImpl.getUserDashboard(username);
	}

	public String getServiceDashboard(String service) {
		// TODO Auto-generated method stub
		return dashboardImpl.getServiceDashboard(service);
	}
	
	public String getBillingDashboard() {
		return dashboardImpl.getBillingDashboard();
	}
}
