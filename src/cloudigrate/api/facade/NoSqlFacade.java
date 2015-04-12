/*
 * Author: Vaibhav Bhor
 * */
package cloudigrate.api.facade;

import java.io.IOException;
import java.security.GeneralSecurityException;

import javax.ws.rs.PathParam;

import com.google.api.services.datastore.client.DatastoreException;

import cloudigrate.api.implementation.NoSqlImpl;

public class NoSqlFacade {

	// ENUM to restrict cloud platform provider - You can just add new Cloud Platform Provider here in future
		public enum CloudPlatform {
			AWS, GOOGLE
		}
		
		private static CloudPlatform cloudPlatform;;

		// Constructor that sets a particular Cloud Platform Provider 
		public NoSqlFacade() {
			this.cloudPlatform = cloudPlatform.AWS;
		}
		
		NoSqlImpl noSqlImpl = new NoSqlImpl();
		
		public String insertItem(String item, String tableName){
			try {
				noSqlImpl.insertItem(item, tableName, this.cloudPlatform);
			} catch (GeneralSecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DatastoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return item;
		}

		public void getItem(String tableName, String attributeName,
				String attributeValue) {
			// TODO Auto-generated method stub
			try {
				noSqlImpl.getItem(tableName, attributeName, attributeValue, this.cloudPlatform);
			} catch (DatastoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (GeneralSecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		/*public void deleteItem(String tableName, String attributeName, String attributeValue)
		{
				try {
					noSqlImpl.deleteItem(tableName, attributeName, attributeValue, this.cloudPlatform);
				} catch (GeneralSecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}*/
}
