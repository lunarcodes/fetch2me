package com.lunarcodes.fetch.config.test.ConfigBuilderTest;

import junit.framework.TestCase;

import com.lunarcodes.fetch.config.ConfigBuilder;
import com.lunarcodes.fetch.config.SMTPConfig;
import com.lunarcodes.fetch.exception.ConfigurationException;

public class ConfigBuilderTest extends TestCase {
	ConfigBuilder builder=null;
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		builder=ConfigBuilder.getInstance();
	}
	public void testGetSMTPConfig() {
		
		/*<property key="mailid" value="@gmail.com"/>
		<property key="password" value=""/>
		<property key="hostname" value="smtp.gmail.com"/>
		<property key="port" value="587"/>*/
		try {
			SMTPConfig config=builder.getSMTPConfig();
			assertEquals("smtp.gmail.com", config.getHostname());
			assertEquals("fetch2metwin@gmail.com", config.getMailid());
			assertEquals(587, config.getPort());
		} catch (ConfigurationException e) {
			fail ("Configuration Exception "+e.getMessage());
			e.printStackTrace();
		}
		
	}

	public void testGetImapConfig() {
		
		/*<property key="mailid" value="@gmail.com"/>
		<property key="password" value=""/>
		<property key="hostname" value="smtp.gmail.com"/>
		<property key="port" value="587"/>*/
		try {
			SMTPConfig config=builder.getSMTPConfig();
			assertEquals("smtp.gmail.com", config.getHostname());
			assertEquals("fetch2metwin@gmail.com", config.getMailid());
			assertEquals(587, config.getPort());
		} catch (ConfigurationException e) {
			fail ("Configuration Exception "+e.getMessage());
			e.printStackTrace();
		}
		
	}
}
