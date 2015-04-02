/*
 * Author: Rohit Kothari
 */

package cloudigrate.api.implementation.aws;

import java.io.File;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectRequest;

import cloudigrate.api.implementation.aws.InitializeAWS;

/*
 *  Lower level implementation class for Storage - AWS (S3)
 */
public class AWSStorage {

	// We need S3Client to communicate with actual AWS Console 
	AmazonS3Client s3Client = null;
	AWSCredentials credentials = null;

	// Constructor that initializes the AWS Storage component - See InitializeAWS.java for more details
	public AWSStorage()
	{
		credentials = InitializeAWS.getCredentials();
		s3Client = new AmazonS3Client(credentials);
	}

	// Lowest-level createBucket() method that makes an API call to AWS Console using S3Client
	public void createBucket(String bucketName)
	{
		System.out.println("Inside AWSStorage - createBucket()");
		// Note that the below createBucket(bucketName) method belongs to AWS S3 Java SDK
		s3Client.createBucket(bucketName);
	}
	
	// Lowest-level uploadObject() method that makes an API call to AWS Console using S3Client
	public void uploadObject(String bucketName, String keyName, File object)
	{
		System.out.println("Inside AWSStorage - uploadObject()");
		// Note that the below putObject() method belongs to AWS S3 Java SDK
		s3Client.putObject(new PutObjectRequest(bucketName, keyName, object));
	}
}
