package cloudigrate.api.facade;

import cloudigrate.api.implementation.ComputeImpl;


/**
 * @author sumantmurke
 * 
 */

public class ComputeFacade {
	
	/* Part of business logic - To decide which cloud platform to use
	 *	 
	 *	Currently supporting the following Cloud Platform Providers-
	 *		1. Amazon Web Services
	 *		2. Google Cloud Platform
	 */
	
	// ENUM to restrict cloud platform provider - You can just add new Cloud Platform Provider here in future
	public enum CloudPlatform {
		AWS, GOOGLE
	}
	private static CloudPlatform cloudPlatform;
	
	
	public ComputeFacade() {
		this.cloudPlatform = cloudPlatform.AWS;
		//this.cloudPlatform = cloudPlatform.GOOGLE;
	}
	
	ComputeImpl computeimpl = new ComputeImpl();
	
	
	public String createVM(String vmname, String vmdescription){
		System.out.println("inside computefacade of createVM");
		return computeimpl.createVM(vmname,vmdescription,cloudPlatform);
		
	}
	
	public void stopVM(String instanceId){
		System.out.println("inside computefacade of stop vm");
		computeimpl.stopVM(instanceId, cloudPlatform);
	}

	/**
	 * @param instanceId
	 */
	public void terminateInstance(String instanceId) {
		// TODO Auto-generated method stub
		System.out.println("inside computefacade of instance terminate vm");
		computeimpl.terminateInstance(instanceId, cloudPlatform);
		
	}

}
