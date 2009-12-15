package com.lunarcodes.fetch.processor;

import org.apache.log4j.Logger;

import com.lunarcodes.fetch.request.RequestType;
import com.lunarcodes.fetch.request.RequestVo;

public class ProcessorFactory {

protected final static Logger LOG = Logger.getLogger(ProcessorFactory.class);
	
	private ProcessorFactory(){}
	
	private static ProcessorFactory processorFactoryInstance=null;
	
	public static ProcessorFactory getInstance(){
		if (processorFactoryInstance==null){
			processorFactoryInstance=new ProcessorFactory();
		}
		return processorFactoryInstance;
	}
	
	public Processor getProcessor(RequestVo requestVo){

		Processor processor=null;
		RequestType requestType=resolveRequestType(requestVo);
		//Strips off the prefix entries and sets the RequestContent with only the needed information. Also sets the RequestType to the request Vo
		
		switch (requestType){
		case SEARCH:
			requestVo.setRequestType(RequestType.SEARCH);
			processor=new SearchProcessor(requestVo);
			break;
		case URL:
			requestVo.setRequestType(RequestType.URL);
			processor=new URLProcessor(requestVo);
			break;
		case WIKI:
			requestVo.setRequestType(RequestType.WIKI);
			processor=new WikiProcessor(requestVo);
			break;
		}
		return processor;
	}
	
	
	
	public RequestType resolveRequestType(RequestVo requestVo){
		RequestType requestType=null;
		String content=requestVo.getRequestContent();
		if (content.toLowerCase().startsWith("search")){
			requestType=RequestType.SEARCH;
		}
		else if (content.toLowerCase().startsWith("wiki")){
			requestType=RequestType.WIKI;
		}
		else{
			//The content just has the url. No prefix information like wiki: or search: is available
			//TODO Rajesh was suggesting that we accept request as <wiki>Jesus Christ</wiki> and <url>http://www.google.com</url>. Need to implement it
			requestType=RequestType.URL;
		}
		return requestType;
		
	}
}
