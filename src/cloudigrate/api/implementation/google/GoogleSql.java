/*
 * Author: Ashutosh Folane
 */

package cloudigrate.api.implementation.google;

/*
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
*/
	
/*
 *  Lower level implementation class for SQL - Google (CloudSQL)
 */
public class GoogleSql {
	
	String userName;
	String userPassword ;
	String connectionString;
	
	public GoogleSql()
	{
		userName = "cloudigrate";
		userPassword = "cloudigrate";
		connectionString = "173.194.107.103";
	}
		// Lowest-level getUserName() method that makes an API call to AWS Console using RDS endpoint
		public String getUserName()
		{
			System.out.println("Inside GoogleSql - getUserName()");				
			return userName;			
		}
		
		public String getUserPassword()
		{
			System.out.println("Inside GoogleSql - getUserPassword()");			
			return userPassword;
		}
		
		public String getConnectionString()
		{
			System.out.println("Inside GoogleSql - getConnectionString()");			
			return connectionString;
			
		}
}