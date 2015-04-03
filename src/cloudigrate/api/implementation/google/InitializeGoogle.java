package cloudigrate.api.implementation.google;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.services.datastore.client.DatastoreOptions;

public class InitializeGoogle {

	private static final String ACCOUNT_ID_PROPERTY = "";
	private static final String PRIVATE_KEY_PATH_PROPERTY = "";

	public static Credential getCredentials() throws GeneralSecurityException, IOException
	{
		HttpTransport httpTransport = new NetHttpTransport();
		JacksonFactory jsonFactory = new JacksonFactory();
		Credential credential = new GoogleCredential.Builder()
				.setTransport(httpTransport)
				.setJsonFactory(jsonFactory)
				.setServiceAccountId(ACCOUNT_ID_PROPERTY)
				.setServiceAccountScopes(DatastoreOptions.SCOPES)
				.setServiceAccountPrivateKeyFromP12File(
						new File(PRIVATE_KEY_PATH_PROPERTY)).build();
		return credential;
	}
}
