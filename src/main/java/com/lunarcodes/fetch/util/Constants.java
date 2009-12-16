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
	public static final String INVALID_URL="Fetch2Me is unable to process your request because the URL to be fetched is either invalid or could not be reached";
	public static final String INVALID_URL_SUBJECT="Error during processing your request";
	public static final String CONTENTS_OF="Contents of ";
	public static final String SUCCESS="Here you go... your request has been successfully processed !!!";
	
	
}
