/*
 * Author: Rohit Kothari
 */

package cloudigrate.api.implementation;

import java.io.File;

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

import cloudigrate.api.facade.StorageFacade.CloudPlatform;
import cloudigrate.api.implementation.aws.*;
import cloudigrate.api.implementation.google.GoogleStorage;

public class StorageImpl {

	//AWSStorage awsStorage = new AWSStorage();


	public void createBucket(String bucketName, CloudPlatform cloudPlatform){

		System.out.println("Inside StorageImpl - createBucket() with params:"+bucketName+","+cloudPlatform);

		/* Business logic implementation
		 * Switch-case usage to implement operations related to cloud platform
		 */

		switch(cloudPlatform) {

		case AWS: 
			System.out.println("Control going inside cloudigrate.api.implementation.aws");
			AWSStorage awsStorage = new AWSStorage();
			awsStorage.createBucket(bucketName);
			break;

		case GOOGLE: 
			System.out.println("Control going inside cloudigrate.api.implementation.google");
			GoogleStorage googleStorage = new GoogleStorage();
			googleStorage.createBucket(bucketName);
			break;

		default:
			System.out.println("You have entered invalid business decision for cloud platform");
			System.out.println("Please verify your FACADE for CloudPlatform enum");
			break;
		}
	}
	
	public void uploadObject(String bucketName, String keyName, File object, CloudPlatform cloudPlatform){

		System.out.println("Inside StorageImpl - uploadObject() with params:"+bucketName+","+cloudPlatform);

		/* Business logic implementation
		 * Switch-case usage to implement operations related to cloud platform
		 */

		switch(cloudPlatform) {

		case AWS: 
			System.out.println("Control going inside cloudigrate.api.implementation.aws");
			AWSStorage awsStorage = new AWSStorage();
			awsStorage.uploadObject(bucketName, keyName, object);
			break;

		case GOOGLE: 
			System.out.println("Control going inside cloudigrate.api.implementation.google");
			GoogleStorage googleStorage = new GoogleStorage();
			googleStorage.uploadObject(bucketName, keyName, object);
			break;

		default:
			System.out.println("You have entered invalid business decision for cloud platform");
			System.out.println("Please verify your FACADE for CloudPlatform enum");
			break;
		}
	}

}
