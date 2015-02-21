package cloudigrate.api.impl;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

public class StorageImpl {
	AmazonS3 s3 = null;
	
	public void authenticateAWS(){
		AWSCredentials credentials = null;
		System.out.println("Authenticating AWS...");
        try {
            credentials = new ProfileCredentialsProvider("default").getCredentials();
        } catch (Exception e) {
            throw new AmazonClientException(
                    "Cannot load the credentials from the credential profiles file. " +
                    "Please make sure that your credentials file is at the correct " +
                    "location (C:\\Users\\{username}\\.aws\\credentials), and is in valid format.",
                    e);
        }
        s3 = new AmazonS3Client(credentials);
        Region usWest2 = Region.getRegion(Regions.US_WEST_1);
        s3.setRegion(usWest2);
	}
	
	public void createAWSBucket(String bucketName){
		//String bucketName = "testcirk" + UUID.randomUUID();
        

        
        System.out.println("Creating bucket " + bucketName + "\n");
        
        /*
         * Api for creating bucket
         */
        s3.createBucket(bucketName);
	}
}
