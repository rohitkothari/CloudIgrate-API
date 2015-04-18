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
		if(level.equals("sql") && !value.equals(authenticationImpl.getPreference("sql"))){
			System.out.println("********* SQL MIGRATION TIME ***********" + value);
		}else if(level.equals("nosql") && !value.equals(authenticationImpl.getPreference("nosql"))){
			System.out.println("********* NOSQL MIGRATION TIME ***********"+ value);
		}else if(level.equals("storage") && !value.equals(authenticationImpl.getPreference("storage"))){
			System.out.println("********* STORAGE MIGRATION TIME ***********"+ value);
		}else if(level.equals("instance") && !value.equals(authenticationImpl.getPreference("instance"))){
			System.out.println("********* INSTANCE MIGRATION TIME ***********"+ value);
		}
		
		authenticationImpl.setPreference(level, value);
	}

	public Map<String, String> getPlatformValues() {
		// TODO Auto-generated method stub
		return authenticationImpl.getPreference();
	}
}
