package com.lunarcodes.fetch.response;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.lunarcodes.fetch.request.RequestType;

public class ResponseVo implements Serializable {
	
	private RequestType requestType; 

	//FIXME kill me ! Kill me !! if i am acting foolish here. This is all the imagination i have right now
	private String stringResponseContent=null;
	private String urlResponseContent=null;
	private String toAddress;
	private String toNumber;
	private String toName;
	
	public String getStringResponseContent() {
		return stringResponseContent;
	}
	public void setStringResponseContent(String stringResponseContent) {
		this.stringResponseContent = stringResponseContent;
	}
	public String getUrlResponseContent() {
		return urlResponseContent;
	}
	public void setUrlResponseContent(String urlResponseContent) {
		this.urlResponseContent = urlResponseContent;
	}
	
	@Override
	public String toString() {
		return (ReflectionToStringBuilder.toString(this));
	}
	public RequestType getRequestType() {
		return requestType;
	}
	public void setRequestType(RequestType requestType) {
		this.requestType = requestType;
	}
	public String getToAddress() {
		return toAddress;
	}
	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}
	public String getToNumber() {
		return toNumber;
	}
	public void setToNumber(String toNumber) {
		this.toNumber = toNumber;
	}
	public String getToName() {
		return toName;
	}
	public void setToName(String toName) {
		this.toName = toName;
	}
}
