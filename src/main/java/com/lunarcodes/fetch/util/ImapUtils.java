package com.lunarcodes.fetch.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;
import javax.mail.search.FlagTerm;

import org.apache.log4j.Logger;

import com.lunarcodes.fetch.config.ConfigBuilder;
import com.lunarcodes.fetch.config.ImapConfig;
import com.lunarcodes.fetch.exception.CommunicationException;
import com.lunarcodes.fetch.request.RequestVo;
import com.lunarcodes.fetch.transport.TransportType;

public class ImapUtils {
	
	protected final static Logger LOG = Logger.getLogger(ImapUtils.class);
	
	private HashMap cachedItems=new HashMap();
	private ImapUtils(){
		
		initialize();
	}
	
	private void initialize(){
		//Initialize Unread messages Flagterm and store it in the cachedItems
		//Filter read items
		FlagTerm unreadMessageFlagTerm=new FlagTerm (new Flags(Flags.Flag.SEEN),false); //the false flag negates the SEEN to UNSEEN. Flags.Flag doesnt have an unseen flag
		cachedItems.put(Constants.UNSEEN_FLAG_TERM, unreadMessageFlagTerm);
		
	}
	
	private static ImapUtils imapUtilsInstance=null;
	
	public static ImapUtils getInstance(){
		if (imapUtilsInstance==null){
			imapUtilsInstance=new ImapUtils();
		}
		return imapUtilsInstance;
	}

	private Store connect(ImapConfig imapConfigVo) throws CommunicationException{

		LOG.debug(imapConfigVo);
		Store store=null;
		try {
			Properties props=System.getProperties();
			props.setProperty("mail.store.protocol", "imaps");
			
			Session session=(Session)cachedItems.get(Constants.IMAP_SESSION);
			
			if (session==null){
				session=Session.getDefaultInstance(props);
				cachedItems.put(Constants.IMAP_SESSION, session);
			}
			
			store=session.getStore();
			
			store.connect(imapConfigVo.getHostname(), imapConfigVo.getMailid(), imapConfigVo.getPassword());
		} catch (NoSuchProviderException e) {
			LOG.error(e.getMessage(),e);
			throw new CommunicationException (e.getMessage(),e);
			
		} catch (MessagingException e) {
			LOG.error(e.getMessage(),e);
			throw new CommunicationException (e.getMessage(),e);
		}
		return store;
		
	}

	private Message[] getUnreadMessages(Store store, String folderName) throws CommunicationException{

		Message[] messages=null;
		try {
			LOG.info("Opening messages from "+folderName);
			Folder inbox=store.getFolder(folderName);
			inbox.open(Folder.READ_ONLY);
			messages=inbox.search((FlagTerm)cachedItems.get(Constants.UNSEEN_FLAG_TERM));
			/*for(Message message:messages) {
				System.out.println(message);
			}*/

		} catch (MessagingException e) {
			LOG.error(e.getMessage(),e);
			throw new CommunicationException (e.getMessage(),e);
		}
		return messages;
	}
	
	private List<RequestVo> createUserFriendlyRequests(Message[] messages) throws CommunicationException{
		ArrayList<RequestVo> requestList=new ArrayList<RequestVo>();
		RequestVo requestVo=null;
		InternetAddress internetAddress=null;
		try {
			for (Message message: messages){
				requestVo=new RequestVo();
				internetAddress=(InternetAddress)message.getFrom()[0];
				requestVo.setFromAddress(internetAddress.getAddress());
				requestVo.setFromName(internetAddress.getPersonal());
				requestVo.setRequestSubject(message.getSubject());
				//TODO As of now Request content is the same as request Subject. When we 
				//decide to read the message content, then we can think about using the requestContent
				requestVo.setRequestContent(message.getSubject());
				
				requestVo.setMessageId(message.getMessageNumber());
				//FIXME Need to pull the transport type setter to a different method for SMS delivery
				//Set Transport type
				requestVo.setTransportType(TransportType.MAIL);
				LOG.debug(requestVo);
				requestList.add(requestVo);
			}
		} catch (MessagingException e) {
			LOG.error(e.getMessage(),e);
			throw new CommunicationException (e.getMessage(),e);
		}
		return requestList;
	}
	
	//Move this method to ResponseHandlerFactory
	/*private RequestType resolveRequestType(String requestContent){
		//This is a totally lame logic but this is exactly what i have written in the Python version
		//TODO. If possible, do something better
		
	}*/
	
	
	public List<RequestVo> fetchRequestMessages(ImapConfig imapConfigVo){
		
		Store mailBox=connect(imapConfigVo);
		Message[] messages=getUnreadMessages(mailBox, Constants.INBOX);
		List<RequestVo> requestVoList=createUserFriendlyRequests(messages);
		
		return requestVoList;
		
	}
	

	//TODO Remove this main method and add some decent testcases for this class
	public static void main(String[] args) {
		ImapUtils.getInstance().fetchRequestMessages(ConfigBuilder.getInstance().getImapConfig());
	}

}
