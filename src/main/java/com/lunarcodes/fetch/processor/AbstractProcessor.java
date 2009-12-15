package com.lunarcodes.fetch.processor;

import com.lunarcodes.fetch.request.RequestVo;
import com.lunarcodes.fetch.response.ResponseVo;

public abstract class AbstractProcessor implements Processor {
	
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
