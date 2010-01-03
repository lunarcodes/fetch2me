package com.lunarcodes.fetch.response;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

public class GoogleJsonVo {
	
	private ResponseData responseData;
	private String responseStatus;
	
	public static class ResponseData{
		private List<GoogleResult> googleResults=new ArrayList<GoogleResult>();

		public List<GoogleResult> getGoogleResults() {
			return googleResults;
		}

		public void setGoogleResults(List<GoogleResult> googleResults) {
			this.googleResults = googleResults;
		}
		@Override
		public String toString() {
			return ReflectionToStringBuilder.toString(this);
		}
		
	}

	public ResponseData getResponseData() {
		return responseData;
	}

	public void setResponseData(ResponseData responseData) {
		this.responseData = responseData;
	}

	public String getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(String responseStatus) {
		this.responseStatus = responseStatus;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
