/*
 * Author: Rohit Kothari
 */

package cloudigrate.api.implementation.google;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.InputStreamContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.storage.Storage;
import com.google.api.services.storage.StorageScopes;
import com.google.api.services.storage.model.Bucket;
import com.google.api.services.storage.model.StorageObject;

import java.io.FileNotFoundException;
import java.io.IOException;


public class GoogleStorage {
	// Yet to write tested implementation here, however it will be similar to AWSStorage.java
	private static Storage storage;
	private static String APPLICATION_NAME = "cloudigrate-poc-gcs";
	private static String PROJECT_ID = "cloudigrate-poc-storage";
	
	public GoogleStorage() {
		if (storage == null) {
			
			APPLICATION_NAME = "cloudigrate-poc-gcs";
			PROJECT_ID = "cloudigrate-poc-storage";
			
			HttpTransport httpTransport = new NetHttpTransport();
			JsonFactory jsonFactory = new JacksonFactory();

			List<String> scopes = new ArrayList<String>();
			scopes.add(StorageScopes.DEVSTORAGE_FULL_CONTROL);

			Credential credential = null;
			try {
				credential = InitializeGoogle.getCredentials();
			} catch (GeneralSecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			storage = new Storage.Builder(httpTransport, jsonFactory,
					credential).setApplicationName(APPLICATION_NAME)
					.build();
		}

	}
	
	public void createBucket(String bucketName)
	{
		Bucket bucket = new Bucket();
		bucket.setName(bucketName);
		System.out.println("Creating a bucket in Google Storage");
		try {
			System.out.println("Entering try block");
			storage.buckets().insert(APPLICATION_NAME, bucket).execute();
			//storage.buckets().insert(PROJECT_ID, bucket).execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void uploadObject(String bucketName, String keyName, File object)
	{
		
		StorageObject storageObject = new StorageObject();
		storageObject.setBucket(bucketName);
		InputStream stream = null;
		
		try {
			System.out.println("Uploading an object from: "+object.getCanonicalPath());
			File file = new File(object.getCanonicalPath());

			stream = new FileInputStream(file);
			String contentType = URLConnection
					.guessContentTypeFromStream(stream);
			InputStreamContent content = new InputStreamContent(contentType,
					stream);

			Storage.Objects.Insert insert = storage.objects().insert(
					bucketName, null, content);
			insert.setName(file.getName());

			insert.execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
//		finally {
//			stream.close();
//		}
	}
	
	public File downloadObject(String bucketName, String keyName, String downloadPath) throws FileNotFoundException, IOException
	{
		File directory = new File(downloadPath);
		if(!directory.isDirectory()) {
			try {
				throw new Exception("Provided destinationDirectory path is not a directory");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		File file = new File(directory.getAbsolutePath() + "/" + keyName);
		
		
		
		Storage.Objects.Get get = storage.objects().get(bucketName, keyName);
		FileOutputStream stream = new FileOutputStream(file);
		try {
			get.executeAndDownloadTo(stream);
		} finally {
			stream.close();
		}
		return file;
	}

	public void deleteObject(String bucketName, String keyName) {
		try {
			storage.objects().delete(bucketName, keyName).execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
