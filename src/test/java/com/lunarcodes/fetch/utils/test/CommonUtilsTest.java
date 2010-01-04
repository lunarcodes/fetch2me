package com.lunarcodes.fetch.utils.test;

import com.lunarcodes.fetch.util.CommonUtils;

import junit.framework.TestCase;

public class CommonUtilsTest extends TestCase {


	public void testStripPrefix(){
		try {
			String subject="search: Jesus Christ";
			assertEquals("Jesus Christ", CommonUtils.stripPrefix(subject));
		} catch (Exception e) {
			e.printStackTrace();
			fail (e.getMessage());
		}
	}
	

	public void testStripPrefixLimit(){
		try {
			String subject="search: Jesus : Christ";
			assertEquals("Jesus : Christ", CommonUtils.stripPrefix(subject));
		} catch (Exception e) {
			e.printStackTrace();
			fail (e.getMessage());
		}
	}
}
