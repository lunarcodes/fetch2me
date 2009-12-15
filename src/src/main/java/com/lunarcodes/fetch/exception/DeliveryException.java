package com.lunarcodes.fetch.exception;

/**
 * @author arun
 *
 */
public class DeliveryException extends Exception {
	
	public DeliveryException() {
		super();
	}

	public DeliveryException(String message) {
		super(message);
	}
	public DeliveryException(String message, Exception exception) {
		super(message,exception);
	}
}
