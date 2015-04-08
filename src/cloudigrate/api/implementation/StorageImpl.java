/*
 * Author: Rohit Kothari
 */

package cloudigrate.api.implementation;

import cloudigrate.api.facade.StorageFacade.CloudPlatform;
import cloudigrate.api.implementation.aws.*;
import cloudigrate.api.implementation.google.GoogleStorage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class StorageImpl {


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

	public File downloadObject(String bucketName, String keyName, String downloadPath, CloudPlatform cloudPlatform) throws FileNotFoundException, IOException{

		System.out.println("Inside StorageImpl - uploadObject() with params:"+bucketName+","+cloudPlatform);
		File object = null;
		/* Business logic implementation
		 * Switch-case usage to implement operations related to cloud platform
		 */

		switch(cloudPlatform) {

		case AWS: 
			System.out.println("Control going inside cloudigrate.api.implementation.aws");
			AWSStorage awsStorage = new AWSStorage();
			object = awsStorage.downloadObject(bucketName, keyName, downloadPath);
			break;

		case GOOGLE: 
			System.out.println("Control going inside cloudigrate.api.implementation.google");
			//GoogleStorage googleStorage = new GoogleStorage();
			//googleStorage.uploadObject(bucketName, keyName, object);
			break;

		default:
			System.out.println("You have entered invalid business decision for cloud platform");
			System.out.println("Please verify your FACADE for CloudPlatform enum");
			break;
		}
		
		return object;
	}
	
	public void deleteObject(String bucketName, String keyName, CloudPlatform cloudPlatform){

		System.out.println("Inside StorageImpl - deleteObject() with params:"+bucketName+","+keyName+","+cloudPlatform);

		/* Business logic implementation
		 * Switch-case usage to implement operations related to cloud platform
		 */

		switch(cloudPlatform) {

		case AWS: 
			System.out.println("Control going inside cloudigrate.api.implementation.aws");
			AWSStorage awsStorage = new AWSStorage();
			awsStorage.deleteObject(bucketName, keyName);
			break;

		case GOOGLE: 
			System.out.println("Control going inside cloudigrate.api.implementation.google");
			GoogleStorage googleStorage = new GoogleStorage();
			googleStorage.deleteObject(bucketName, keyName);
			break;

		default:
			System.out.println("You have entered invalid business decision for cloud platform");
			System.out.println("Please verify your FACADE for CloudPlatform enum");
			break;
		}
	}
}
