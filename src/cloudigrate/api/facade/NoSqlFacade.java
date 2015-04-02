/*
 * Author: Vaibhav Bhor
 * */
package cloudigrate.api.facade;

import javax.ws.rs.PathParam;

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
			noSqlImpl.insertItem(item, tableName, this.cloudPlatform);
			return item;
		}

		public void getItem(String tableName, String attributeName,
				String attributeValue) {
			// TODO Auto-generated method stub
			noSqlImpl.getItem(tableName, attributeName, attributeValue, this.cloudPlatform);
			
		}

}
