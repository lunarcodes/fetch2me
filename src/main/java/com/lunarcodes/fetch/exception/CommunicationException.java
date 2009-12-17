package com.lunarcodes.fetch.exception;

/**
 * @author arun
 *
 */
public class CommunicationException extends RuntimeException {
	
	public CommunicationException() {
		super();
	}

	public CommunicationException(String message) {
		super(message);
	}
	public CommunicationException(String message, Exception exception) {
		super(message,exception);
	}
}
