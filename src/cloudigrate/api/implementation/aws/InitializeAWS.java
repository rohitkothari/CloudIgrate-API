/*
 * Author: Rohit Kothari
 */

package cloudigrate.api.implementation.aws;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;

/*
 *  The purpose of this class is to do all initial processing for AWS cloud that includes:
 *  	- Authentication to AWS Cloud
 *  	- 
 */
public class InitializeAWS {

	public static AWSCredentials getCredentials()
	{
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
		return credentials;
	}
}
