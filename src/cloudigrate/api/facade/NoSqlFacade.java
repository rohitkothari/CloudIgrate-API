/*
 * Author: Vaibhav Bhor
 * */
package cloudigrate.api.facade;

import java.io.IOException;
import java.security.GeneralSecurityException;

import cloudigrate.api.domain.Platform;

import javax.ws.rs.PathParam;

import com.google.api.services.datastore.client.DatastoreException;

import cloudigrate.api.implementation.AuthenticationImpl;
import cloudigrate.api.implementation.NoSqlImpl;

public class NoSqlFacade {
		NoSqlImpl noSqlImpl = null;
		public NoSqlFacade()
		{
			 noSqlImpl = new NoSqlImpl();
		}
		
		public String insertItem(String item, String tableName){
			String key = null;
			try {
				key = noSqlImpl.insertItem(item, tableName);
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
			return key;
		}

		public String getItem(String keyValue) {
			String jsonItems = null;
			// TODO Auto-generated method stub
			try {
				jsonItems = noSqlImpl.getItem(keyValue);
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
			return jsonItems;
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
