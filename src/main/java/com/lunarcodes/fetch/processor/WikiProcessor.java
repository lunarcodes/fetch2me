package com.lunarcodes.fetch.processor;

import com.lunarcodes.fetch.config.Config;
import com.lunarcodes.fetch.request.RequestVo;
import com.lunarcodes.fetch.response.ResponseVo;
import com.lunarcodes.fetch.util.CommonUtils;

public class WikiProcessor extends AbstractProcessor {
	
	public WikiProcessor(RequestVo requestVo) {
		super(requestVo);
	}

	public void process() {
		// TODO Auto-generated method stub
		String strippedContent=CommonUtils.stripPrefix(getRequestVo().getRequestContent());
		getRequestVo().setRequestContent(strippedContent);
	}

	public ResponseVo getResponseVo() {
		// TODO Auto-generated method stub
		return null;
	}

}
