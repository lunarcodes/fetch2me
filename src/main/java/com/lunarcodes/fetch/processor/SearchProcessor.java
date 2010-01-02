package com.lunarcodes.fetch.processor;

import java.net.MalformedURLException;
import java.net.URL;

import javax.activation.DataSource;
import javax.activation.URLDataSource;

import com.lunarcodes.fetch.exception.ProcessingException;
import com.lunarcodes.fetch.request.RequestVo;
import com.lunarcodes.fetch.response.ResponseType;
import com.lunarcodes.fetch.response.ResponseVo;
import com.lunarcodes.fetch.util.CommonUtils;
import com.lunarcodes.fetch.util.Constants;

public class SearchProcessor extends AbstractProcessor {

	public SearchProcessor(RequestVo requestVo) {
		super(requestVo);
	}

	public ResponseVo getResponseVo() {
		ResponseVo responseVo = new ResponseVo();

		try {
			responseVo.setResponseType(ResponseType.SEARCH);
			responseVo.setResponseContent(getResponseContent(responseVo));
			responseVo.setSubject(Constants.SEARCH_RESULTS_FOR + CommonUtils.stripPrefix(getRequestVo().getRequestContent()));
		} catch (ProcessingException e) {
			LOG.error(e.getMessage(), e);
			responseVo.setResponseType(ResponseType.ERROR);
			responseVo.setStringResponseContent(Constants.UNABLE_TO_PROCESS);
			responseVo.setSubject(Constants.ERROR_DURING_PROCESSING);
		}

		responseVo.setToAddress(getRequestVo().getFromAddress());
		responseVo.setToName(getRequestVo().getFromName());

		return responseVo;

	}

	/**
	 * @param responseVo
	 * @return
	 * @throws ProcessingException
	 */
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
