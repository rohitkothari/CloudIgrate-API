package cloudigrate.api.facade;

import java.util.HashMap;

import cloudigrate.api.implementation.AuthenticationImpl;

public class AuthenticationFacade {

	AuthenticationImpl authenticationImpl = new AuthenticationImpl();
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
}
