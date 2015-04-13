package cloudigrate.api.facade;

import java.util.HashMap;

import cloudigrate.api.implementation.AuthenticationImpl;

public class AuthenticationFacade {

	AuthenticationImpl authenticationImpl = new AuthenticationImpl();
	
	public int isValidKey(String key)
	{
		return authenticationImpl.isValidKey(key);
	}

	public HashMap<String, String> getLogInfo(int keyId) {
		// TODO Auto-generated method stub
		return authenticationImpl.getLogInfo(keyId);
	}
}
