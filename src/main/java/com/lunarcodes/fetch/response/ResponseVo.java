package com.lunarcodes.fetch.response;

import java.io.Serializable;

import javax.activation.DataSource;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.lunarcodes.fetch.util.Constants;

public class ResponseVo implements Serializable {
	
	private ResponseType responseType;
	//FIXME kill me ! Kill me !! if i am acting foolish here. This is all the imagination i have right now
	private String message=Constants.SUCCESS;
	private String url=null;
	private String toAddress;
	private String toNumber;
	private String toName;
	private String subject;
	//Flag which tells us whether there is a mail attachment or not so that we can avoid creating Multipart portion for the mail message
	private boolean multipart=false;
	
	private DataSource responseContent;
	
	
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isMultipart() {
		return multipart;
	}

	public void setMultipart(boolean multipart) {
		this.multipart = multipart;
	}
}
