package com.lunarcodes.fetch.processor;

import com.lunarcodes.fetch.request.RequestVo;
import com.lunarcodes.fetch.response.ResponseVo;
import com.lunarcodes.fetch.transport.MailTransporter;
import com.lunarcodes.fetch.transport.Transporter;

public class URLProcessor extends AbstractProcessor {

	public URLProcessor(RequestVo requestVo) {
		super(requestVo);
	}
	public void process() {

		ResponseVo responseVo=null;
		Transporter transporter=null;
		switch (getRequestVo().getTransportType()){
		case MAIL:
			transporter=new MailTransporter();
			responseVo=getResponseVo();
			break;
			
		case SMS: //FIXME
			break;
			
		case JABBER: //FIXME
			break;
			
		
		}
		transporter.transport(responseVo);
	}
	
	public ResponseVo getResponseVo(){
		ResponseVo responseVo=new ResponseVo();
		responseVo.setRequestType(getRequestVo().getRequestType());
		responseVo.setUrlResponseContent(getRequestVo().getRequestContent());
		responseVo.setToAddress(getRequestVo().getFromAddress());
		responseVo.setToName(getRequestVo().getFromName());
		
		return responseVo;
		
	}

}
