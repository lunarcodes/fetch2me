package com.lunarcodes.fetch.processor;

import java.net.MalformedURLException;
import java.net.URL;

import javax.activation.DataSource;
import javax.activation.URLDataSource;

import com.lunarcodes.fetch.exception.ProcessingException;
import com.lunarcodes.fetch.exception.TransportException;
import com.lunarcodes.fetch.request.RequestVo;
import com.lunarcodes.fetch.response.ResponseType;
import com.lunarcodes.fetch.response.ResponseVo;
import com.lunarcodes.fetch.transport.MailTransporter;
import com.lunarcodes.fetch.transport.Transporter;
import com.lunarcodes.fetch.util.Constants;

public class URLProcessor extends AbstractProcessor {

	public URLProcessor(RequestVo requestVo) {
		super(requestVo);
	}

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

	public ResponseVo getResponseVo() {
		ResponseVo responseVo = new ResponseVo();

		try {
			if (checkIfURLIsValid(getRequestVo().getRequestContent())) {
				responseVo.setResponseType(ResponseType.URL);
				responseVo.setUrl(getRequestVo().getRequestContent());
				responseVo.setResponseContent(getResponseContent(responseVo));
				responseVo.setSubject(Constants.CONTENTS_OF + getRequestVo().getRequestContent());
			}
		} catch (ProcessingException e) {
			LOG.error(e.getMessage(), e);
			responseVo.setResponseType(ResponseType.ERROR);
			responseVo.setStringResponseContent(Constants.INVALID_URL);
			responseVo.setSubject(Constants.INVALID_URL_SUBJECT);
		}

		responseVo.setToAddress(getRequestVo().getFromAddress());
		responseVo.setToName(getRequestVo().getFromName());

		return responseVo;

	}

	private boolean checkIfURLIsValid(String urlString) {
		// FIXME Use HTTPClient to verify URL validity
		return true;
	}

	private DataSource getResponseContent(ResponseVo responseVo) throws ProcessingException {
		DataSource dataSource = null;
		try {
			dataSource = new URLDataSource(new URL(responseVo.getUrl()));
		} catch (MalformedURLException e) {
			LOG.error(e.getMessage(), e);
			throw new ProcessingException(e.getMessage(), e);
			// e.printStackTrace();
		}
		return dataSource;

	}
}
