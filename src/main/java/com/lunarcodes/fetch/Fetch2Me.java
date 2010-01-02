package com.lunarcodes.fetch;

import java.util.List;

import org.apache.log4j.Logger;

import com.lunarcodes.fetch.config.ConfigBuilder;
import com.lunarcodes.fetch.exception.ProcessingException;
import com.lunarcodes.fetch.exception.TransportException;
import com.lunarcodes.fetch.processor.Processor;
import com.lunarcodes.fetch.processor.ProcessorFactory;
import com.lunarcodes.fetch.request.RequestVo;
import com.lunarcodes.fetch.util.ImapUtils;

/**
 * @author arun
 * Entry point of the program. 
 *
 */
public class Fetch2Me {
	
	protected final static Logger LOG = Logger.getLogger(Fetch2Me.class);
	public static void main(String[] args) {
		Fetch2Me fetch2Me=new Fetch2Me();
		fetch2Me.startProcessing();
	}
	
	/**
	 * This method accepts and processes only mail requests as of now. Need to change signature or spawn a different thread for sms processing.
	 */
	private void startProcessing(){
		List<RequestVo> requestList=ImapUtils.getInstance().fetchRequestMessages(ConfigBuilder.getInstance().getImapConfig());
		Processor processor=null;
		for (RequestVo requestVo:requestList){
			processor=ProcessorFactory.getInstance().getProcessor(requestVo);
			try {
				processor.process();
			} catch (ProcessingException e) {
				//FIXME Fallback to error message to user. Should use the upcoming SMTPUtils class
				LOG.error(e.getMessage(), e);
			} catch (TransportException e) {
				//FIXME Fallback to error message to user
				LOG.error(e.getMessage(), e);
			}
		}
	}

}
