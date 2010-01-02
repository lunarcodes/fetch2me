package com.lunarcodes.fetch.processor;

import org.apache.log4j.Logger;

import com.lunarcodes.fetch.exception.TransportException;
import com.lunarcodes.fetch.request.RequestVo;
import com.lunarcodes.fetch.response.ResponseVo;
import com.lunarcodes.fetch.transport.MailTransporter;
import com.lunarcodes.fetch.transport.Transporter;

public abstract class AbstractProcessor implements Processor {
	
	protected final static Logger LOG = Logger.getLogger(AbstractProcessor.class);
	
	private RequestVo requestVo;
	
	public AbstractProcessor(RequestVo requestVo){
		this.requestVo=requestVo;
	}
	
	/* (non-Javadoc)
	 * @see com.lunarcodes.fetch.processor.Processor#process()
	 */
	public void process() throws TransportException {

		ResponseVo responseVo = null;
		Transporter transporter = null;
		switch (getRequestVo().getTransportType()) {
		case MAIL:
			transporter = MailTransporter.getInstance();
			responseVo = getResponseVo();
			break;

		case SMS: // FIXME
			break;

		case JABBER: // FIXME
			break;

		}
		transporter.transport(responseVo);
	}

	/* (non-Javadoc)
	 * @see com.lunarcodes.fetch.processor.Processor#getResponseVo()
	 */
	public abstract ResponseVo getResponseVo();
	
	public RequestVo getRequestVo() {
		return requestVo;
	}
	public void setRequestVo(RequestVo requestVo) {
		this.requestVo = requestVo;
	}
}
