package cloudigrate.api.implementation.google;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.services.datastore.client.DatastoreOptions;
import com.google.api.services.storage.StorageScopes;

public class InitializeGoogle {

	private static final String ACCOUNT_ID_PROPERTY = "559680496685-uih56shoold7b7olrvt5smc01cbcdgu3@developer.gserviceaccount.com";
	private static final String PRIVATE_KEY_PATH_PROPERTY = "/Users/rohietkothari/cloudigrate-poc-gcs-a9273c6661b4.p12";

	public static Credential getCredentials() throws GeneralSecurityException, IOException
	{
		List<String> scopes = new ArrayList<String>();
		scopes.add(StorageScopes.DEVSTORAGE_FULL_CONTROL);
		
		HttpTransport httpTransport = new NetHttpTransport();
		JacksonFactory jsonFactory = new JacksonFactory();
		Credential credential = new GoogleCredential.Builder()
				.setTransport(httpTransport)
				.setJsonFactory(jsonFactory)
				.setServiceAccountId(ACCOUNT_ID_PROPERTY)
				//.setServiceAccountScopes(DatastoreOptions.SCOPES)
				.setServiceAccountScopes(scopes)
				.setServiceAccountPrivateKeyFromP12File(
						new File(PRIVATE_KEY_PATH_PROPERTY)).build();
		return credential;
	}
}
