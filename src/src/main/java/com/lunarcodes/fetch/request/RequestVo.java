package com.lunarcodes.fetch.request;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.lunarcodes.fetch.transport.TransportType;

public class RequestVo implements Serializable {

	//What kind of request is this?
	private RequestType requestType;
	
	//What is the targetted transport
	private TransportType transportType;
	
	//TODO add needed header information after analyzing the Mail header specs
	private String requestSubject;
	private String requestContent;
	private String fromAddress;
	private String fromName;
	
	//Internal to program
	private int messageId;
	
	public RequestType getRequestType() {
		return requestType;
	}
	public void setRequestType(RequestType requestType) {
		this.requestType = requestType;
	}
	public String getRequestSubject() {
		return requestSubject;
	}
	public void setRequestSubject(String requestSubject) {
		this.requestSubject = requestSubject;
	}
	public String getRequestContent() {
		return requestContent;
	}
	public void setRequestContent(String requestContent) {
		this.requestContent = requestContent;
	}
	public String getFromAddress() {
		return fromAddress;
	}
	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}
	
	@Override
	public String toString() {
		return (ReflectionToStringBuilder.toString(this));
	}
	public int getMessageId() {
		return messageId;
	}
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}
	public TransportType getTransportType() {
		return transportType;
	}
	public void setTransportType(TransportType transportType) {
		this.transportType = transportType;
	}
	public String getFromName() {
		return fromName;
	}
	public void setFromName(String fromName) {
		this.fromName = fromName;
	}
	
}
