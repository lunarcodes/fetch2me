package com.lunarcodes.fetch.processor;

import java.io.IOException;
import java.util.List;

import com.lunarcodes.fetch.exception.CommunicationException;
import com.lunarcodes.fetch.exception.ProcessingException;
import com.lunarcodes.fetch.request.RequestVo;
import com.lunarcodes.fetch.response.GoogleResult;
import com.lunarcodes.fetch.response.ResponseType;
import com.lunarcodes.fetch.response.ResponseVo;
import com.lunarcodes.fetch.util.CommonUtils;
import com.lunarcodes.fetch.util.Constants;
import com.lunarcodes.fetch.util.GoogleUtils;

public class SearchProcessor extends AbstractProcessor {

	public SearchProcessor(RequestVo requestVo) {
		super(requestVo);
	}

	public ResponseVo getResponseVo() {
		ResponseVo responseVo = new ResponseVo();

		try {
			responseVo.setResponseType(ResponseType.SEARCH);
			//No Attachment for this mail. So set the Message content alone
			responseVo.setMessage(getMessage(getRequestVo()));
			responseVo.setSubject(Constants.SEARCH_RESULTS_FOR + CommonUtils.stripPrefix(getRequestVo().getRequestContent()));
		} catch (ProcessingException e) {
			LOG.error(e.getMessage(), e);
			responseVo.setResponseType(ResponseType.ERROR);
			responseVo.setMessage(Constants.UNABLE_TO_PROCESS);
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
	private String getMessage(RequestVo request) throws ProcessingException {
		String searchString=CommonUtils.stripPrefix(request.getRequestSubject());
		StringBuilder messageContent=new StringBuilder();
		try {
			List<GoogleResult> results=GoogleUtils.searchWeb(searchString);
			
			for (GoogleResult result:results){
				messageContent.append(Constants.TITLE)
					.append(result.getTitle())
					.append(Constants.BREAK)
					
					.append(Constants.URL)
					.append(result.getUrl())
					.append(Constants.BREAK)
					
					.append(Constants.CACHED_URL)
					.append(result.getCacheUrl())
					.append(Constants.BREAK)
					
					.append(Constants.CONTENT)
					.append(result.getContent())
					.append(Constants.BREAK).append(Constants.BREAK);
			}
			                     
		} catch (CommunicationException e) {
			//e.printStackTrace();
			LOG.error(e.getMessage(),e);
			throw new ProcessingException(e.getMessage());
		} catch (IOException e) {
			//e.printStackTrace();
			LOG.error(e.getMessage(),e);
			throw new ProcessingException(e.getMessage());
		}
		return messageContent.toString();
	}

}
