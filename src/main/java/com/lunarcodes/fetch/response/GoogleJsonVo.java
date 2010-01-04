package com.lunarcodes.fetch.response;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

public class GoogleJsonVo {
	
	private ResponseData responseData;
	private String responseStatus;
	
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
	
	public static class ResponseData{
		private List<GoogleResult> results=new ArrayList<GoogleResult>();

		@Override
		public String toString() {
			return ReflectionToStringBuilder.toString(this);
		}

		public List<GoogleResult> getResults() {
			return results;
		}

		public void setResults(List<GoogleResult> results) {
			this.results = results;
		}
		
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
