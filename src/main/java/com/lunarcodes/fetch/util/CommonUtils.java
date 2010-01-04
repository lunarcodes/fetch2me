package com.lunarcodes.fetch.util;

import java.net.URLEncoder;

import org.apache.commons.lang.StringUtils;

public class CommonUtils {

	public static String stripPrefix(String requestContent) throws ArrayIndexOutOfBoundsException{
		String strippedContent = null;
		//System.out.println(StringUtils.substring(requestContent, StringUtils.indexOf(requestContent, Constants.COLON)+1));
		strippedContent = StringUtils.substring(requestContent, StringUtils.indexOf(requestContent, Constants.COLON)+1);
		return strippedContent.trim();
	}

	public static String encode(String rawString) {
		try {
			return URLEncoder.encode(rawString, "UTF-8");
		} catch (Exception ex) {
			return ""; //silently return an empty string if the string could not be encoded
		}
	}
}
