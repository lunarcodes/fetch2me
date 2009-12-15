package com.lunarcodes.fetch.processor;

import com.lunarcodes.fetch.exception.ProcessingException;
import com.lunarcodes.fetch.exception.TransportException;
import com.lunarcodes.fetch.response.ResponseVo;

public interface Processor {

	public void process() throws ProcessingException, TransportException;
	
	public ResponseVo getResponseVo();
}
