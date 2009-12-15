package com.lunarcodes.fetch.exception;

/**
 * @author arun
 *
 */
public class TransportException extends RuntimeException {
	
	public TransportException() {
		super();
	}

	public TransportException(String message) {
		super(message);
	}
	public TransportException(String message, Exception exception) {
		super(message,exception);
	}
}
