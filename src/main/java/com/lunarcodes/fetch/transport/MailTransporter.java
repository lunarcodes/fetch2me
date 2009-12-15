package com.lunarcodes.fetch.transport;

import java.net.MalformedURLException;
import java.net.URL;

import javax.mail.MessagingException;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.log4j.Logger;

import com.lunarcodes.fetch.config.Config;
import com.lunarcodes.fetch.config.ConfigBuilder;
import com.lunarcodes.fetch.config.SMTPConfig;
import com.lunarcodes.fetch.exception.ConfigurationException;
import com.lunarcodes.fetch.exception.TransportException;
import com.lunarcodes.fetch.response.ResponseVo;
import com.sun.tools.internal.ws.wscompile.DefaultAuthenticator;

public class MailTransporter implements Transporter {

	protected final static Logger LOG = Logger.getLogger(MailTransporter.class);

	public void transport(ResponseVo responseVo) throws TransportException {

			try {
				SMTPConfig config = ConfigBuilder.getInstance().getSMTPConfig();

				// FIXME Need to move this code to SMTPUtils or something like that. I
				// just copied this from http://commons.apache.org/email/userguide.html
				MultiPartEmail email=getEmailInstance(config);
				email.addTo(responseVo.getToAddress(), responseVo.getToName());
				email.setFrom(config.getMailid(),"Fetch2Me");
				email.setSubject(responseVo.getUrlResponseContent());
				email.setMsg("Contents of "+responseVo.getUrlResponseContent());

				// add the attachment
				email.attach(getEmailAttachment(responseVo));

				// send the email
				email.send();
			} catch (ConfigurationException e) {
				LOG.error(e.getMessage(),e);
				throw e;
			} catch (EmailException e) {
				LOG.error(e.getMessage(),e);
				throw new TransportException (e.getMessage(),e);
			}
			
			LOG.debug("Mail successfully sent to :"+responseVo.getToAddress());
			
	}
	private EmailAttachment getEmailAttachment(ResponseVo responseVo) throws TransportException{
		EmailAttachment attachment=null;
		try {
			attachment=new EmailAttachment();
			//FIXME Use HTTPConnectiont to check whether the URL returns status code 200
			attachment.setURL(new URL(responseVo.getUrlResponseContent()));
			attachment.setDisposition(EmailAttachment.ATTACHMENT);
			//FIXME Set name and description from httpheaders or html title.. DO SOMETHING on this.. This looks bad
			//refer to http://commons.apache.org/email/userguide.html for usage
		} catch (MalformedURLException e) {
			LOG.error(e.getMessage(),e);
			//FIXME if we move the URL checking to some other place, then this exception should be propagated as ProcessingException. This is NOT a TransportException
			throw new TransportException ("URL unattachable. Possible causes : URL could not be attached or Target URL down", e);
		}
		return attachment;
		
	}
	
	private MultiPartEmail getEmailInstance(SMTPConfig configVo) {
		MultiPartEmail email = new MultiPartEmail();
		email.setHostName(configVo.getHostname());
		email.setAuthentication(configVo.getMailid(), configVo.getPassword());
		email.setHostName("smtp.gmail.com");
		email.setTLS(true);
		return email;
	}

}
