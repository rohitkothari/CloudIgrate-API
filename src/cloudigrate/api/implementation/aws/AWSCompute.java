package cloudigrate.api.implementation.aws;


import java.util.ArrayList;
import java.util.List;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.CreateTagsRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Reservation;
import com.amazonaws.services.ec2.model.RunInstancesRequest;
import com.amazonaws.services.ec2.model.RunInstancesResult;
import com.amazonaws.services.ec2.model.StopInstancesRequest;
import com.amazonaws.services.ec2.model.Tag;
import com.amazonaws.services.ec2.model.TerminateInstancesRequest;

public class AWSCompute {

	AWSCredentials credentials = null;
	public static AmazonEC2Client amazonEC2Client;
	public static String InstanceIdOfTheInstance = null;
	public static String defaultImageId = "ami-fe002cbb";
	public static String defaultInstanceType = "t1.micro";
	public static String sshKeyName = "aws-sjsu-key";



	public AWSCompute(){
		credentials = InitializeAWS.getCredentials();
		amazonEC2Client = new AmazonEC2Client(credentials);
		amazonEC2Client.setEndpoint("ec2.us-west-1.amazonaws.com");
	}

	public String createVM(String vmname, String vmDescription){
		System.out.println("inside create vm AWScompute");
		String instanceId = null;
	
		RunInstancesRequest runInstancesRequest = new RunInstancesRequest();
		runInstancesRequest.withImageId(defaultImageId)
		.withInstanceType(defaultInstanceType).withMinCount(1).withMaxCount(1);
		
		

		System.out
		.println("Instance with following details has been Initialized:  ");

		RunInstancesResult runInstances = amazonEC2Client.runInstances(runInstancesRequest);
		System.out.println(" runinstance "+runInstances.getReservation().getOwnerId());
		List<Instance> instances = runInstances.getReservation().getInstances();

		
		System.out.println("instance "+instances.isEmpty());
		System.out.println(" no of instances "+instances.size());
		
		for (Instance instance : instances) {
			CreateTagsRequest createTagsRequest = new CreateTagsRequest();
		
			
			createTagsRequest.withResources(instance.getInstanceId()) 
			.withTags(new Tag("Name", vmname)).withTags(new Tag("Description", vmDescription));
			amazonEC2Client.createTags(createTagsRequest);
				
			 instanceId = instance.getInstanceId();
		}
		
	
	return instanceId;
	}
	
	/*
	 * Stop VM function
	 */
	
	public void stopVM(String instanceId){
		System.out.println("inside vm stop");
		
		
	List<String> stopInstanceList = new ArrayList<String>();
		stopInstanceList.add(instanceId);
		StopInstancesRequest stopInstanceRequest = new StopInstancesRequest();
		stopInstanceRequest.setInstanceIds(stopInstanceList);
		stopInstanceRequest.setRequestCredentials(credentials);
		amazonEC2Client.stopInstances(stopInstanceRequest);
		System.out.println("Instance with ID "+ instanceId +"has been Stopped");
	
	}
	
	
/*
 * List of instances	
 */
public void listofinstances(){
	 
	DescribeInstancesResult describeInstanceResult = amazonEC2Client.describeInstances();
	List<Reservation> listOfReservations = describeInstanceResult.getReservations();
	for(Reservation reservation:listOfReservations){
		
		List<Instance> listOfInstances = reservation.getInstances();
		
		for(Instance instance: listOfInstances){
		//	List<Tag> t = instance.withTags();
		}
		
	}
}

/**
 * @param instanceId
 */
public void terminateVM(String instanceId) {
	// TODO Auto-generated method stub
	
	System.out.println("inside terminate");
	List<String> terminateInstanceList = new ArrayList<String>();
	terminateInstanceList.add(instanceId);
	TerminateInstancesRequest terminateInstanceRequest = new TerminateInstancesRequest();
	terminateInstanceRequest.setInstanceIds(terminateInstanceList);
	terminateInstanceRequest.setRequestCredentials(credentials);
	amazonEC2Client.terminateInstances(terminateInstanceRequest);
	System.out.println("Instance"+ instanceId +" has been terminated!!");
}
	

}
