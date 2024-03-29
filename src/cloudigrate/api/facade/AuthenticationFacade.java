package cloudigrate.api.facade;

import java.util.HashMap;
import java.util.Map;

import cloudigrate.api.implementation.AuthenticationImpl;
import cloudigrate.api.implementation.MigrationImpl;

public class AuthenticationFacade {

	AuthenticationImpl authenticationImpl = new AuthenticationImpl();
	MigrationImpl migrationImpl= new MigrationImpl();
	String decodedKey = null;
	
	public int isValidKey(String key)
	{
		decodedKey = decodeKey(key);
		return authenticationImpl.isValidKey(decodedKey);
	}

	public HashMap<String, String> getLogInfo(int keyId) {
		// TODO Auto-generated method stub
		return authenticationImpl.getLogInfo(keyId);
	}
	
	
	public String decodeKey(String encodedKey)
	{
		// Decode data on other side, by processing encoded data
		byte[] valueDecoded= org.apache.commons.codec.binary.Base64.decodeBase64(encodedKey);
		System.out.println("Decoded value is " + new String(valueDecoded));
		return new String(valueDecoded);
	}

	public void setPlatformValue(String level, String value) {
		System.out.println("Inside AuthenticationFacade");
		if(level.equals("sql") && !value.equals(authenticationImpl.getPreference("sql").toString())){
			System.out.println("********* SQL MIGRATION TIME ***********" + value + " preferences value" + authenticationImpl.getPreference("nosql"));
			migrationImpl.migrateSQL(value);
		}else if(level.equals("nosql") && !(value.equals(authenticationImpl.getPreference("nosql").toString()))){
						System.out.println("********* NOSQL MIGRATION TIME ***********"+ value + " preferences value" + authenticationImpl.getPreference("nosql"));
						migrationImpl.migrateNOSQL(value);
		}else if(level.equals("storage") && !value.equals(authenticationImpl.getPreference("storage").toString())){
			System.out.println("********* STORAGE MIGRATION TIME ***********"+ value + " preferences value" + authenticationImpl.getPreference("nosql"));
			migrationImpl.migrateStorage(value);
		}else if(level.equals("instance") && !value.equals(authenticationImpl.getPreference("instance").toString())){
			System.out.println("********* INSTANCE MIGRATION TIME ***********"+ value + " preferences value" + authenticationImpl.getPreference("nosql"));
			migrationImpl.migrateInstance(value);
		}
		
		authenticationImpl.setPreference(level, value);
	}

	public Map<String, String> getPlatformValues() {
		// TODO Auto-generated method stub
		return authenticationImpl.getPreference();
	}
}
