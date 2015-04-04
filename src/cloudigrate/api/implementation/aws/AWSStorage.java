/*
 * Author: Rohit Kothari
 */

package cloudigrate.api.implementation.aws;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

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
	
	// Lowest-level downloadObject() method that makes an API call to AWS Console using S3Client
	public File downloadObject(String bucketName, String keyName, String downloadPath) throws FileNotFoundException, IOException
	{
		System.out.println("Inside AWSStorage - downloadObject()");
		File object = new File(downloadPath+"/"+keyName);
		// Note that the below methods belongs to AWS S3 Java SDK

		GetObjectRequest request = new GetObjectRequest(bucketName, keyName);		
		S3Object s3Object = s3Client.getObject(request);
		S3ObjectInputStream objectContent = s3Object.getObjectContent();
		IOUtils.copy(objectContent, new FileOutputStream(object));

		System.out.println("Inside AWSStorage - downloadObject() - downloaded file: "+object.getName());
		
		return object;

	}
	
}
