package cloudigrate.api.implementation;
import cloudigrate.api.facade.ComputeFacade.CloudPlatform;
import cloudigrate.api.implementation.aws.AWSCompute;

public class ComputeImpl {
	
	
	public void createVM(String vmname,String vmdescription,CloudPlatform cloudPlatform){
		System.out.println("inside computeimpl of createVM");
		
		
		
		switch(cloudPlatform) {
		
		case AWS: System.out.println("computing for AWS"+" for vm "+vmname+" with description "+vmdescription);
					AWSCompute awscompute = new AWSCompute();
					awscompute.createVM(vmname, vmdescription);
					break;
		
		default:
			System.out.println("You have entered invalid business decision for cloud platform");
			System.out.println("Please verify your FACADE for CloudPlatform enum");
			break;
		}
		
		
	}

	
	
}
