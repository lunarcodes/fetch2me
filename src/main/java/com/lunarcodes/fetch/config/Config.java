package com.lunarcodes.fetch.config;

import java.util.HashMap;

import com.lunarcodes.fetch.exception.ConfigurationException;



/**
 * @author arun
 *
 */
public abstract class Config extends HashMap<String, String>{

	private static final long serialVersionUID = 6293538793155706559L;
	
	public String getMandatoryValueForKey(String key) throws ConfigurationException{
		String value=get(key);
		if (value==null){
			throw new ConfigurationException ("Null value found for a Not nullable field");
		}
		return value;
	}
	
	public String getValueForKey(String key) throws ConfigurationException{
		return get(key);
	}
	
	public void store(String key, String value) {
		put (key, value);
	}
	
}
