package com.lunarcodes.fetch.response;

import java.io.Serializable;

import javax.activation.DataSource;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.lunarcodes.fetch.request.RequestType;

public class ResponseVo implements Serializable {
	
	private ResponseType responseType;
	//FIXME kill me ! Kill me !! if i am acting foolish here. This is all the imagination i have right now
	private String stringResponseContent=null;
	private String url=null;
	private String toAddress;
	private String toNumber;
	private String toName;
	private String subject;
	
	private DataSource responseContent;
	
	public String getStringResponseContent() {
		return stringResponseContent;
	}
	public void setStringResponseContent(String stringResponseContent) {
		this.stringResponseContent = stringResponseContent;
	}
	@Override
	public String toString() {
		return (ReflectionToStringBuilder.toString(this));
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public ResponseType getResponseType() {
		return responseType;
	}
	public void setResponseType(ResponseType responseType) {
		this.responseType = responseType;
	}
	public DataSource getResponseContent() {
		return responseContent;
	}
	public void setResponseContent(DataSource responseContent) {
		this.responseContent = responseContent;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
}
