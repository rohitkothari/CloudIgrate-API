/*
 * Author: Rohit Kothari
 */

package cloudigrate.api.implementation.google;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


public class GoogleStorage {
	// Yet to write tested implementation here, however it will be similar to AWSStorage.java
	
	public void createBucket(String bucketName)
	{

	}
	
	public void uploadObject(String bucketName, String keyName, File object)
	{
		
	}
	
	public File downloadObject(String bucketName, String keyName, String downloadPath) throws FileNotFoundException, IOException
	{
		return null;
	}

	public void deleteObject(String bucketName, String keyName) {
		
		
	}
}
