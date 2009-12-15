package com.lunarcodes.fetch.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.xpath.XPath;

import com.lunarcodes.fetch.exception.ConfigurationException;
import com.lunarcodes.fetch.util.Constants;

/**
 * @author arun
 * 
 * This class should do the following : 
 * 
 * Step 1. parse the XML file to a Dom Document
 * Step 2. populate different configuration into different config Vo
 * Step 3. Cache them for use by the configfactory class
 *
 */
public class ConfigBuilder {
	

	private HashMap<ConfigType, Config> configCache=new HashMap<ConfigType, Config>();
	//Boilerplate for singleton
	private ConfigBuilder(){};
	
	private static ConfigBuilder configBuilder;
	
	public static ConfigBuilder getInstance(){
		if (configBuilder==null){
			configBuilder=new ConfigBuilder();
		}
		return configBuilder;
	}

	protected final static Logger LOG = Logger.getLogger(ConfigBuilder.class);
	
/*	private Config getConfig(ConfigType configType) throws ConfigurationException{
		
		switch (configType){
		case SMTP:
			return getSMTPConfig();
		case IMAP:
			return null;
		case SMS:
			return null;
		}
	}*/
	
	//TODO If you have a look at most of the methods here, you can derive a pattern among a few pairs of methods. 
	//like... you do something at the beginning of the method etc.. i think we can move these into 
	//an aspect
	public SMTPConfig getSMTPConfig() throws ConfigurationException{
		if (configCache.get(ConfigType.SMTP)==null){
			populateConfigFromXML(this.getClass().getClassLoader().getResourceAsStream(Constants.CONFIG_FILE_NAME));
		}
		return ((SMTPConfig)configCache.get(ConfigType.SMTP));
	}
	
	public ImapConfig getImapConfig() throws ConfigurationException{
		if (configCache.get(ConfigType.IMAP)==null){
			populateConfigFromXML(this.getClass().getClassLoader().getResourceAsStream(Constants.CONFIG_FILE_NAME));
		}
		return ((ImapConfig)configCache.get(ConfigType.IMAP));
	}
	
	private void populateConfigFromXML(InputStream configFileStream) throws ConfigurationException{
		Document configDoc=parse(configFileStream);
		SMTPConfig smtpConfig=populateSMTPConfigs(configDoc, Constants.SMTP_XPATH);
		ImapConfig imapConfig=populateImapConfigs(configDoc, Constants.IMAP_XPATH);
		LOG.debug("IMAP CONFIG "+imapConfig);
		configCache.put(ConfigType.SMTP, smtpConfig);
		configCache.put(ConfigType.IMAP, imapConfig);
	}
	
	//TODO following two methods has the same logic. need to refactor. i feel so sleepy
	private SMTPConfig populateSMTPConfigs(Document configDoc, String xPath) throws ConfigurationException{
		
		List<Element> smtpPropertyNodes;
		try {
			smtpPropertyNodes = XPath.selectNodes(configDoc, xPath);
		} catch (JDOMException e) {
			LOG.error(e.getMessage(), e);
			throw new ConfigurationException(e.getMessage(), e);
		}

		SMTPConfig smtpConfig=new SMTPConfig();

		String key=null;
		String value=null;
		for (Element propertyElement:smtpPropertyNodes){

			key=propertyElement.getAttributeValue(Constants.KEY);
			value=propertyElement.getAttributeValue(Constants.VALUE);
			
			smtpConfig.store(key, value);
		}
		
		return smtpConfig;
	}
	
	private ImapConfig populateImapConfigs(Document configDoc, String xPath) throws ConfigurationException{
		List<Element> smtpPropertyNodes;
		try {
			smtpPropertyNodes = XPath.selectNodes(configDoc, xPath);
		} catch (JDOMException e) {
			LOG.error(e.getMessage(), e);
			throw new ConfigurationException(e.getMessage(), e);
		}

		ImapConfig imapConfig=new ImapConfig();

		String key=null;
		String value=null;
		for (Element propertyElement:smtpPropertyNodes){

			key=propertyElement.getAttributeValue(Constants.KEY);
			value=propertyElement.getAttributeValue(Constants.VALUE);
			imapConfig.store(key, value);
		}
		
		return imapConfig;
	}
	
	/**
	 * Step 1. parse the XML file to a Dom Document
	 * @param configPath
	 * @return
	 * @throws ConfigurationException
	 */
	private Document parse(InputStream configFileStream) throws ConfigurationException{
		
		Document xmlDoc = new Document();
		SAXBuilder saxBuilder = new SAXBuilder();
		try {
			xmlDoc=saxBuilder.build(configFileStream);
		} catch (JDOMException e) {
			LOG.error(e.getMessage(),e);
			throw new ConfigurationException ("JDOMException in configbuilder", e);
		} catch (IOException e) {
			LOG.error(e.getMessage(),e);
			throw new ConfigurationException ("IOException in configbuilder", e);
		}
		
		return xmlDoc;
	}
	
	
	
}
