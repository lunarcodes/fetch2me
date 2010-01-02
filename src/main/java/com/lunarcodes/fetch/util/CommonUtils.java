package com.lunarcodes.fetch.util;

import java.net.URLEncoder;

public class CommonUtils {

	public static String stripPrefix(String requestContent) {
		String strippedContent = null;
		strippedContent = requestContent.split(":", 1)[1];
		return strippedContent;
	}

	public static String encode(String rawString) {
		try {
			return URLEncoder.encode(rawString, "UTF-8");
		} catch (Exception ex) {
			return ""; //silently return an empty string if the string could not be encoded
		}
	}
}
