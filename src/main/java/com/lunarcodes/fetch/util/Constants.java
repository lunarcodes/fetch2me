package com.lunarcodes.fetch.util;

public interface Constants {

	public static final String CONFIG_FILE_NAME="config.xml";
	public static final String KEY="key";
	public static final String VALUE="value";
	public static final String SMTP_XPATH="//smtpconfig/property";
	public static final String IMAP_XPATH="//imapconfig/property";
	public static final String INBOX="Inbox";
	public static final String UNSEEN_FLAG_TERM ="UNSEEN_FLAG_TERM";
	public static final String IMAP_SESSION ="IMAP_SESSION";
	public static final String SMTP_SESSION ="SMTP_SESSION";
	public static final String UNABLE_TO_PROCESS="Fetch2Me is unable to process your request because the URL/search criteria to be fetched is either invalid or could not be reached";
	public static final String ERROR_DURING_PROCESSING="Error during processing your request";
	public static final String CONTENTS_OF="Contents of ";
	public static final String SEARCH_RESULTS_FOR="Search Results for ";
	public static final String SUCCESS="Here you go... your request has been successfully processed !!!";

	//http://code.google.com/apis/ajaxsearch/documentation/reference.html#_restUrlBase
	public static final String WEB_SEARCH_URL = "http://ajax.googleapis.com/ajax/services/search/web";
	public static final String NEWS_SEARCH_URL = "http://ajax.googleapis.com/ajax/services/search/news";
	public static final String LOCAL_SEARCH_URL = "http://ajax.googleapis.com/ajax/services/search/local";
	public static final String BOOK_SEARCH_URL = "http://ajax.googleapis.com/ajax/services/search/books";
	public static final String IMAGE_SEARCH_URL = "http://ajax.googleapis.com/ajax/services/search/images";
	public static final String VIDEO_SEARCH_URL = "http://ajax.googleapis.com/ajax/services/search/videos";
	public static final String BLOG_SEARCH_URL = "http://ajax.googleapis.com/ajax/services/search/blogs";
	
	public static final String USER_AGENT="Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.9.0.1) Gecko/2008072820 Lunarcodes";
	public static final int CONNECTION_TIMEOUT_SEC= 10000 ;//(10* 1000) 	
}
