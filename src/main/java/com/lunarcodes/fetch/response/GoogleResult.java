package com.lunarcodes.fetch.response;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

public class GoogleResult implements Serializable {
	//Websearch
	private String url;
	private String visibleUrl;
	private String cacheUrl;
	private String title;
	private String titleNoFormattting;
	private String content;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getVisibleUrl() {
		return visibleUrl;
	}
	public void setVisibleUrl(String visibleUrl) {
		this.visibleUrl = visibleUrl;
	}
	public String getCacheUrl() {
		return cacheUrl;
	}
	public void setCacheUrl(String cacheUrl) {
		this.cacheUrl = cacheUrl;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitleNoFormattting() {
		return titleNoFormattting;
	}
	public void setTitleNoFormattting(String titleNoFormattting) {
		this.titleNoFormattting = titleNoFormattting;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}
