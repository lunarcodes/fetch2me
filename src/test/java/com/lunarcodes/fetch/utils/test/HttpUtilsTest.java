package com.lunarcodes.fetch.utils.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import com.lunarcodes.fetch.exception.CommunicationException;
import com.lunarcodes.fetch.util.Constants;
import com.lunarcodes.fetch.util.HttpUtils;

public class HttpUtilsTest extends TestCase {

	public void testGetResponseStringForGetRequest() {
		Map<String, String> params=new HashMap<String, String>();
		params.put("v", "1.0");
		params.put("q", "Hello World");
		
		try {
			String responseString=HttpUtils.getResponseStringForGetRequest(Constants.GOOGLE_WEB_SEARCH_URL, params);
			System.out.println(responseString);
			assertTrue("Request Successful", responseString.contains(": 200"));
		} catch (CommunicationException e) {
			e.printStackTrace();
			fail (e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			fail (e.getMessage());
		}
	}

	public void testIsURLValid() {
		//fail("Not yet implemented");
	}

}
