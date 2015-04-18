/*
 * Author: Rohit Kothari
 */

package cloudigrate.api.facade;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import cloudigrate.api.implementation.*;

public class StorageFacade {

	/* Part of business logic - To decide which cloud platform to use
	 *	 
	 *	Currently supporting the following Cloud Platform Providers-
	 *		1. Amazon Web Services
	 *		2. Google Cloud Platform
	 */

	StorageImpl storageImpl = new StorageImpl();

	/*
	 *  Method to create a bucket in the pre-configured cloud platform using CloudPlatform enum
	 */
	public void createBucket(String bucketName) {
		System.out.println("Inside StorageFacade - createBucket() with params:"+bucketName);
		// Invoke StorageImpl's createBucket() method
		storageImpl.createBucket(bucketName);
	}
	
	/*
	 *  Method to upload an object in the pre-configured cloud platform using CloudPlatform enum
	 */
	public void uploadObject(String bucketName, String keyName, File object) {
		System.out.println("Inside StorageFacade - uploadObject() with params:"+bucketName);
		// Invoke StorageImpl's createBucket() method
		storageImpl.uploadObject(bucketName, keyName, object);
		
	}
	
	/*
	 *  Method to download an object from the pre-configured cloud platform using CloudPlatform enum
	 */
	public File downloadObject(String bucketName, String keyName, String downloadPath) throws FileNotFoundException, IOException {
		System.out.println("Inside StorageFacade - downloadObject() with params: downloading "+keyName+" from "+bucketName);
		// Invoke StorageImpl's downloadObject() method
		File object = storageImpl.downloadObject(bucketName, keyName, downloadPath);
		return object;
	}
	
	/*
	 *  Method to upload an object in the pre-configured cloud platform using CloudPlatform enum
	 */
	public void deleteObject(String bucketName, String keyName) {
		System.out.println("Inside StorageFacade - deleteObject() with params: deleting "+keyName+" from "+bucketName);
		// Invoke StorageImpl's createBucket() method
		storageImpl.deleteObject(bucketName, keyName);
		
	}
}
