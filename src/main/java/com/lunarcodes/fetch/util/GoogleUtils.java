package com.lunarcodes.fetch.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.lunarcodes.fetch.exception.CommunicationException;
import com.lunarcodes.fetch.response.GoogleJsonVo;
import com.lunarcodes.fetch.response.GoogleResult;

public class GoogleUtils {

	/**
	 * Searches the Web using Google Web Search API 
	 * @param searchString
	 * @return
	 * @throws CommunicationException
	 * @throws IOException
	 */
	public static List<GoogleResult> searchWeb(String searchString) throws CommunicationException, IOException{
		List<GoogleResult> results=null;
		Map <String, String> params=new HashMap<String, String>();
		params.put("v", "1.0");
		params.put("q", searchString);
		
		String jsonString=HttpUtils.getResponseStringForGetRequest(Constants.GOOGLE_WEB_SEARCH_URL, params);
		System.out.println("Json String"+jsonString);
		results=convertJsonToGoogleResults(jsonString);
		System.out.println(results);
		return results;
		
	}
	
	private static List<GoogleResult> convertJsonToGoogleResults(String json){
		Gson gson= new Gson();
		GoogleJsonVo googleJsonVo=gson.fromJson(json, GoogleJsonVo.class);
		System.out.println("Google Json Vo"+googleJsonVo);
		System.out.println("Google ResponseStatus"+googleJsonVo.getResponseStatus());
		return(googleJsonVo.getResponseData().getGoogleResults());
	}
}
