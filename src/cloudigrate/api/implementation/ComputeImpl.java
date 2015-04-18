package cloudigrate.api.implementation;
import cloudigrate.api.implementation.aws.AWSCompute;

public class ComputeImpl {
	
AuthenticationImpl authenticationImpl = null;
AWSCompute awscompute = null;

	// ENUM to restrict cloud platform provider - You can just add new Cloud Platform Provider here in future
			public enum CloudPlatform {
				AWS, GOOGLE
			}
			
			public ComputeImpl()
			{
				authenticationImpl = new AuthenticationImpl();
				awscompute = new AWSCompute();
			}
	 
/*
 * Create VM function
 */
	public String createVM(String vmname,String vmdescription){
		System.out.println("inside computeimpl of createVM");


		switch(authenticationImpl.getPreference("nosql")) {

		case AWS: 
			System.out.println("computing for AWS"+" for vm "+vmname+" with description "+vmdescription);
			return awscompute.createVM(vmname, vmdescription);
		
		default:
			System.out.println("You have entered invalid business decision for cloud platform");
			System.out.println("Please verify your FACADE for CloudPlatform enum");
			break;
		}
		
		return vmdescription;
	}

/*
 * Function for stopping Virtual Machine 
 */
	public void stopVM(String instanceId){
		System.out.println(" inside stop vm computeimpl");

		switch(authenticationImpl.getPreference("nosql")) {

		case AWS: System.out.println("stopping for AWS"+" for vm "+instanceId);
		awscompute.stopVM(instanceId);
		default:
			System.out.println("You have entered invalid business decision for cloud platform");
			System.out.println("Please verify your FACADE for CloudPlatform enum");
			break;
		}
	}

	/**
	 * @param instanceId
	 * @param cloudPlatform
	 */
	public void terminateInstance(String instanceId) {
		// TODO Auto-generated method stub
		System.out.println(" inside terminate vm computeimpl");

		switch(authenticationImpl.getPreference("nosql")) {

		case AWS: System.out.println("stopping for AWS"+" for vm "+instanceId);
		awscompute.terminateVM(instanceId);
		default:
			System.out.println("You have entered invalid business decision for cloud platform");
			System.out.println("Please verify your FACADE for CloudPlatform enum");
			break;

		}
		
	}
}
