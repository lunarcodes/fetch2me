package com.lunarcodes.fetch.utils.test;

import java.io.IOException;
import java.util.List;

import junit.framework.TestCase;

import com.lunarcodes.fetch.exception.CommunicationException;
import com.lunarcodes.fetch.response.GoogleResult;
import com.lunarcodes.fetch.util.GoogleUtils;

public class GoogleUtilsTest extends TestCase {

	public void testSearchWeb() {
		try {
			List<GoogleResult> results=GoogleUtils.searchWeb("Hello World");
			GoogleResult result=results.get(0);
			assertNotNull(result.getUrl());
			assertNotNull(result.getContent());
			assertNotNull(result.getTitle());
			assertNotNull(result.getTitleNoFormattting());
			System.out.println(result);
			
			
		} catch (CommunicationException e) {
			e.printStackTrace();
			fail (e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			fail (e.getMessage());
		}
		
		
	}

}
