package cloudigrate.api.implementation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import cloudigrate.api.domain.Platform;
import cloudigrate.api.domain.Platform.CloudPlatform;

import com.mysql.jdbc.Statement;

public class AuthenticationImpl {
	
	private static String connectionString = "jdbc:mysql://cloudserviceslab.cizvqwfgh78q.us-west-1.rds.amazonaws.com:3306/CloudIgrate";
	private static String dbUsername = "clouduser";
	private static String dbPassword = "clouduser";
	
	public enum CloudPlatform {
		AWS, GOOGLE
	}
		
	private Connection getConnection()
	{
		Connection connection = null;
		try {
			 
				Class.forName("com.mysql.jdbc.Driver");			
				connection = DriverManager.getConnection(connectionString, dbUsername ,dbPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
		return connection;
	}
	
	public int isValidKey(String key) {
		System.out.println("Checking if key is valid");
		 	String query;
		    Connection connection = null;
		    int keyId = 0;
		    try {
		    	connection = getConnection();
		        Statement statement = (Statement) connection.createStatement();
		        query = "SELECT id FROM CloudIgrate.keys WHERE keyValue='" + key + "';";
		        statement.executeQuery(query);
		        ResultSet resultSet = statement.getResultSet(); 
		        if (resultSet.next()) {
		        //isValid = rs.first(); //rs.first();
	        	keyId = Integer.parseInt(resultSet.getString("id"));	
		        connection.close();
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		 
		return keyId;
	}
	

		public HashMap<String, String> getLogInfo(int keyId)
		{
			
			Connection connection = null;
			String query = null;
			HashMap<String, String> logMap = new HashMap<String, String>();
			int userId = 0;
			String applicationName = null;
			try {
				connection = getConnection();
			        Statement statement = (Statement) connection.createStatement();
			        query = "SELECT * FROM CloudIgrate.applications where keyId ="+ keyId +";"; 
			        statement.executeQuery(query);
			        ResultSet resultSet = statement.getResultSet();
			        if (resultSet.next()) {
			        	 userId = Integer.parseInt(resultSet.getString("userid"));
						 applicationName = resultSet.getString("name");
						 connection.close();
					     logMap.put(getUserNameForId(userId), applicationName);
			        }
			    } catch (SQLException e) {
			        e.printStackTrace();
			    }
			 
			return logMap;
		}
	
		private String getUserNameForId(int id)
		{
			Connection connection = null;
			String query = null;
			String userName = null;
			try {
				connection = getConnection();
			        Statement statement = (Statement) connection.createStatement();
			        query = "SELECT email FROM CloudIgrate.users WHERE id=" + id + ";"; 
			        statement.executeQuery(query);
			        ResultSet resultSet = statement.getResultSet();
			        if (resultSet.next()) {
			        	  userName = resultSet.getString("email");
					        connection.close();
			        }
			    } catch (SQLException e) {
			        e.printStackTrace();
			    }
			
			return userName;
			
		}

		public CloudPlatform getPreference(String service) {
			// TODO Auto-generated method stub
			Connection connection = null;
			String query = null;
			String provider = null;
			try {
				connection = getConnection();
			        Statement statement = (Statement) connection.createStatement();
			        query = "SELECT provider FROM CloudIgrate.preferences WHERE service='" + service + "';"; 
			        statement.executeQuery(query);
			        ResultSet resultSet = statement.getResultSet();
			        if (resultSet.next()) {
			        	provider = resultSet.getString("provider");
					        connection.close();
			        }
			    } catch (SQLException e) {
			        e.printStackTrace();
			    }
			
			return getPlatformValue(provider);
		}


		public CloudPlatform getPlatformValue(String level)
		{
			if(level.equals("AWS"))
				return CloudPlatform.AWS;
			else if(level.equals("GOOGLE"))
				return CloudPlatform.GOOGLE;
			return null;
		}
		
		public void setPreference(String service, String provider) {
			// TODO Auto-generated method stub
			Connection connection = null;
			String query = null;
			try {
				connection = getConnection();
				Statement statement = (Statement) connection.createStatement();
				query = "UPDATE CloudIgrate.preferences SET provider='" + provider + "' WHERE service= '"+ service +"';";
				// Update CloudIgrate.preferences set provider = 'GOOGLE' where service = 'sql';
				statement.executeUpdate(query);
			    } catch (SQLException e) {
			        e.printStackTrace();
			    }
		}

		public HashMap<String, String> getPreference() {
			// TODO Auto-generated method stub
			Connection connection = null;
			String query = null;
			HashMap<String, String> preferencesMap = new HashMap<String, String>();
			ArrayList<Platform> allPreferences = new ArrayList<Platform>();
			Platform p = null;
			try {
					connection = getConnection();
			        Statement statement = (Statement) connection.createStatement();
			        query = "SELECT * FROM CloudIgrate.preferences;"; 
			        statement.executeQuery(query);
			        ResultSet resultSet = statement.getResultSet();
			        int counter = 0;
			        while(resultSet.next()){
			        	System.out.println(counter++);
			        	preferencesMap.put(resultSet.getString("service"), resultSet.getString("provider"));
			        	//allPreferences.add(new Platform());
			        	//Platform(String storage, String sql, String nosql, String instance)
			        }
			        connection.close();
			    } catch (SQLException e) {
			        e.printStackTrace();
			    }
			return preferencesMap;
		}
}
