package com.lunarcodes.fetch.processor;

import com.lunarcodes.fetch.exception.ProcessingException;
import com.lunarcodes.fetch.exception.TransportException;
import com.lunarcodes.fetch.response.ResponseVo;

public interface Processor {

	/**
	 * Analyses the RequestType, constructs responses via getResponseVo and transports responses to the user based on user request
	 * @throws ProcessingException
	 * @throws TransportException
	 */
	public void process() throws ProcessingException, TransportException;
	
	/**
	 * Constructs ResponseVo based on the various request parameters. 
	 * @return populated ResponseVo
	 */
	public ResponseVo getResponseVo();
}
