/*
 * Author: Rohit Kothari
 */

package cloudigrate.api.facade;

import cloudigrate.api.implementation.*;

public class StorageFacade {

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
	private static CloudPlatform cloudPlatform;;


	// Constructor that sets a particular Cloud Platform Provider 
	public StorageFacade() {
		this.cloudPlatform = cloudPlatform.AWS;
	}

	StorageImpl storageImpl = new StorageImpl();

	/*
	 *  Method to create a bucket in the pre-configured cloud platform using CloudPlatform enum
	 */
	public void createBucket(String bucketName) {
		System.out.println("Inside StorageFacade - createBucket() with params:"+bucketName);
		// Invoke StorageImpl's createBucket() method
		storageImpl.createBucket(bucketName, cloudPlatform);
	}
}
