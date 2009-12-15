package com.lunarcodes.fetch.config;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;


/**
 * @author arun
 *
 */
public class ImapConfig extends Config{

	private static final long serialVersionUID = 1L;
	
	public String getMailid() {
		return getMandatoryValueForKey("mailid");
	}
	public String getPassword() {
		return getMandatoryValueForKey("password");
	}
	public String getHostname() {
		return getMandatoryValueForKey("hostname");
	}
	public int getPort() {
		String stringPort=getMandatoryValueForKey("port");
		return Integer.parseInt(stringPort);
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
	
}
