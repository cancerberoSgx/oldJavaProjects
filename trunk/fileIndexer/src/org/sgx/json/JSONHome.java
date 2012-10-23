package org.sgx.json;

public class JSONHome {
	private static JSONHome instance;

	private JSONHome() {
	}

	public static JSONHome getInstance() {
		if (null == instance) {
			instance = new JSONHome();
		}
		return instance;
	}

	public JSONObject parseJSON(String s) throws JSONParseException {
		try{
			return JSONObjectSimpleImpl.parse(s);
		}catch (Exception e) {
			throw new JSONParseException(e);
		}
	}

}
