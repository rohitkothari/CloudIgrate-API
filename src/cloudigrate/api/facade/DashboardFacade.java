package cloudigrate.api.facade;

import cloudigrate.api.implementation.DashboardImpl;

public class DashboardFacade {

	DashboardImpl dashboardImpl = null;
	
	public DashboardFacade()
	{
		dashboardImpl = new DashboardImpl();
	}
	
	public String getDashboardData()
	{
		return dashboardImpl.getDashboardData();
	}

	public String getUserDashboard(String username) {
		return dashboardImpl.getUserDashboard(username);
	}

	public String getServiceDashboard(String service) {
		return dashboardImpl.getServiceDashboard(service);
	}
	
	public String getBillingDashboard() {
		return dashboardImpl.getBillingDashboard();
	}

	public String getServiceAverage(String userName) {
		// TODO Auto-generated method stub
		return dashboardImpl.getServiceAverage(userName);
	}

	public String getServiceCount(String userName) {
		// TODO Auto-generated method stub
		return dashboardImpl.getServiceCount(userName);
	}

	public String getLevelCount(String userName) {
		// TODO Auto-generated method stub
		return dashboardImpl.getLevelCount(userName);
	}
}
