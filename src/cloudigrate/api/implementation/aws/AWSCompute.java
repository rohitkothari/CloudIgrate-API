package cloudigrate.api.implementation.aws;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.CreateImageRequest;
import com.amazonaws.services.ec2.model.CreateImageResult;

public class AWSCompute {

	AWSCredentials credentials = null;
	public static AmazonEC2Client amazonEC2Client;
	public AWSCompute(){
		credentials = InitializeAWS.getCredentials();
		amazonEC2Client = new AmazonEC2Client(credentials);
		amazonEC2Client.setEndpoint("ec2.us-west-1.amazonaws.com");
	}
	
	public void createVM(String vmname, String vmDescription){
		System.out.println("inside create vm AWScompute");
		
		CreateImageRequest createImageRequest = new CreateImageRequest();
		createImageRequest.setRequestCredentials(credentials);
		createImageRequest.setInstanceId("ami-df6a8b9b");
		createImageRequest.setName(vmname);
		createImageRequest.setDescription(vmDescription);
		CreateImageResult createImageResult = amazonEC2Client
				.createImage(createImageRequest);
	
		System.out.println("image id is: "+createImageResult.getImageId());
	
	}
	
}
