package com.lunarcodes.fetch.config;


/**
 * @author arun
 *
 */
public class SMTPConfig extends Config{

	private static final long serialVersionUID = 570685941953248222L;

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
	
	/*<property key="mailid" value="@gmail.com"/>
	<property key="password" value="xxxxxx"/>
	<property key="hostname" value="smtp.gmail.com"/>
	<property key="port" value="587"/>*/
}
