package com.lunarcodes.fetch.transport;

import java.util.HashMap;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;

import com.lunarcodes.fetch.config.ConfigBuilder;
import com.lunarcodes.fetch.config.SMTPConfig;
import com.lunarcodes.fetch.exception.TransportException;
import com.lunarcodes.fetch.response.ResponseVo;
import com.lunarcodes.fetch.util.Constants;

public class MailTransporter implements Transporter {

	protected final static Logger LOG = Logger.getLogger(MailTransporter.class);

	private HashMap cachedItems = new HashMap();
	SMTPConfig config = ConfigBuilder.getInstance().getSMTPConfig();

	private MailTransporter() {

		initialize();
	}

	private void initialize() {
		Session session=connect();
		cachedItems.put(Constants.SMTP_SESSION, session);

	}

	private static MailTransporter mailTransporterInstance = null;

	public static MailTransporter getInstance() {
		if (mailTransporterInstance == null) {
			mailTransporterInstance = new MailTransporter();
		}
		return mailTransporterInstance;
	}

	public void transport(ResponseVo responseVo) throws TransportException {

		try {

			Message message = constructMessage(responseVo);
			sendMessage(message);
			
		} catch (AddressException e) {
			LOG.error(e.getMessage(), e);
			throw new TransportException(e.getMessage(), e);
		} catch (MessagingException e) {
			LOG.error(e.getMessage(), e);
			throw new TransportException(e.getMessage(), e);
		}

	}

	private Session connect() {

		// Get system properties
		
     Properties props = System.getProperties();
     props.put("mail.smtp.host", config.getHostname());
     LOG.debug("PORT ::: "+config.getPort());
     props.put("mail.smtp.port", config.getPort());
     props.put("mail.smtp.starttls.enable","true");
     props.put("mail.smtp.auth", "true");
     props.put("mail.smtp.debug", "true");
    /* props.put("mail.smtp.socketFactory.port", config.getPort());
     props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
     props.put("mail.smtp.socketFactory.fallback", "false");
*/
     SecurityManager security = System.getSecurityManager();

         Authenticator auth = new SMTPAuthenticator();
         Session session = Session.getInstance(props, auth);
         session.setDebug(true);

		return session;

	}

	private Message constructMessage(ResponseVo responseVo) throws AddressException, MessagingException {

		Session session=(Session) cachedItems.get(Constants.SMTP_SESSION);
		if (session==null){
			session=connect();
			cachedItems.put(Constants.IMAP_SESSION, session);
		}
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(config.getMailid()));
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(responseVo.getToAddress()));
		message.setSubject(responseVo.getSubject());

		
			Multipart multipart = constructMultiPart(responseVo);
			message.setContent(multipart);
		

		return message;
	}

	private Multipart constructMultiPart(ResponseVo responseVo) throws MessagingException {
		
		Multipart multipart = new MimeMultipart();

		MimeBodyPart bodyPart = null;
		
		bodyPart = new MimeBodyPart();
		bodyPart.setText(responseVo.getMessage());
		multipart.addBodyPart(bodyPart);
		
		if (responseVo.isMultipart()){
			bodyPart = new MimeBodyPart();
			bodyPart.setDataHandler(new DataHandler(responseVo.getResponseContent()));
			multipart.addBodyPart(bodyPart);
		}
		
		

		return multipart;
	}
	
	private void sendMessage(Message message) throws TransportException{
		
		try {
			Session session=(Session)cachedItems.get(Constants.SMTP_SESSION);
			Transport.send(message);
		} catch (NoSuchProviderException e) {
			LOG.error(e.getMessage(), e);
			throw new TransportException(e.getMessage(), e);
		} catch (MessagingException e) {
			LOG.error(e.getMessage(), e);
			throw new TransportException(e.getMessage(), e);
		}
		
	}
	
	private class SMTPAuthenticator extends Authenticator{
		@Override
		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(config.getMailid(), config.getPassword());
		
		}
	}

}


/*
 * public void transport(ResponseVo responseVo) throws TransportException {
 * 
 * try { SMTPConfig config = ConfigBuilder.getInstance().getSMTPConfig();
 * 
 * // FIXME Need to move this code to SMTPUtils or something like that. I //
 * just copied this from http://commons.apache.org/email/userguide.html
 * MultiPartEmail email=getEmailInstance(config);
 * email.addTo(responseVo.getToAddress(), responseVo.getToName());
 * email.setFrom(config.getMailid(),"Fetch2Me");
 * email.setSubject(responseVo.getUrl());
 * email.setMsg("Contents of "+responseVo.getUrl());
 * 
 * // add the attachment email.attach(getEmailAttachment(responseVo));
 * 
 * // send the email email.send(); } catch (ConfigurationException e) {
 * LOG.error(e.getMessage(),e); throw e; } catch (EmailException e) {
 * LOG.error(e.getMessage(),e); throw new TransportException
 * (e.getMessage(),e); }
 * 
 * LOG.debug("Mail successfully sent to :"+responseVo.getToAddress());
 * 
 * } private EmailAttachment getEmailAttachment(ResponseVo responseVo)
 * throws TransportException{ EmailAttachment attachment=null; try {
 * attachment=new EmailAttachment(); //FIXME Use HTTPConnectiont to check
 * whether the URL returns status code 200 attachment.setURL(new
 * URL(responseVo.getUrl()));
 * attachment.setDisposition(EmailAttachment.ATTACHMENT); //FIXME Set name
 * and description from httpheaders or html title.. DO SOMETHING on this..
 * This looks bad //refer to http://commons.apache.org/email/userguide.html
 * for usage } catch (MalformedURLException e) {
 * LOG.error(e.getMessage(),e); //FIXME if we move the URL checking to some
 * other place, then this exception should be propagated as
 * ProcessingException. This is NOT a TransportException throw new
 * TransportException(
 * "URL unattachable. Possible causes : URL could not be attached or Target URL down"
 * , e); } return attachment;
 * 
 * }
 * 
 * private MultiPartEmail getEmailInstance(SMTPConfig configVo) {
 * MultiPartEmail email = new MultiPartEmail();
 * email.setHostName(configVo.getHostname());
 * email.setAuthentication(configVo.getMailid(), configVo.getPassword());
 * email.setHostName("smtp.gmail.com"); email.setTLS(true); return email; }
 */
