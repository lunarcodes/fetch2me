package com.lunarcodes.fetch.processor;

import org.apache.log4j.Logger;

import com.lunarcodes.fetch.request.RequestVo;
import com.lunarcodes.fetch.response.ResponseVo;
import com.lunarcodes.fetch.util.ImapUtils;

public abstract class AbstractProcessor implements Processor {
	
	protected final static Logger LOG = Logger.getLogger(AbstractProcessor.class);
	
	private RequestVo requestVo;

	public AbstractProcessor(RequestVo requestVo){
		this.requestVo=requestVo;
	}
	public abstract ResponseVo getResponseVo();
	
	public RequestVo getRequestVo() {
		return requestVo;
	}
	public void setRequestVo(RequestVo requestVo) {
		this.requestVo = requestVo;
	}
}
