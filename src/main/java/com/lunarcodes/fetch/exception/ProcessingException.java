package com.lunarcodes.fetch.exception;

/**
 * @author arun
 *
 */
public class ProcessingException extends Exception {
	
	public ProcessingException() {
		super();
	}

	public ProcessingException(String message) {
		super(message);
	}
	public ProcessingException(String message, Exception exception) {
		super(message,exception);
	}
}
