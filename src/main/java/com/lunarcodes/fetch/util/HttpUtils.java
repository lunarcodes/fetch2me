package com.lunarcodes.fetch.util;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.lunarcodes.fetch.exception.CommunicationException;

public class HttpUtils {

	private static HttpClient getDefaultHTTPClient() {
		HttpClient httpClient = new DefaultHttpClient();
		return httpClient;
	}

	/**
	 * Gets a HTTPEntity object for any given url and params. Why HTTPEntity and not a response?
	 * The httpEntity can hold both streaming content, string responses and binary responses
	 * @param url
	 * @param params
	 * @return
	 */
	public static String getResponseStringForGetRequest(String url, Map<String, String> params) throws CommunicationException, IOException {

		HttpUriRequest request = constructGetRequest(url, params);
		HttpEntity entity=getResponseEntity(request);
		return EntityUtils.toString(entity);

	}

	/**
	 * Overloaded method without params
	 * @param url
	 * @return
	 * @throws CommunicationException
	 * @throws IOException
	 */
	public static String getResponseStringForGetRequest(String url) throws CommunicationException, IOException {

		return getResponseStringForGetRequest(url, null);

	}
	
	/**
	 * Constructs request object from a URL. 
	 * @param url
	 * @param params
	 * @return
	 */
	private static HttpUriRequest constructGetRequest(String url, Map<String, String> params) {
		HttpUriRequest request = null;

		if (params!=null && params.size() > 0) {
			String queryString = buildQueryString(params);
			url = url + queryString;
		}
		System.out.println(url);

		request = new HttpGet(url);
		return request;
	}
	
	/**
	 * Param map expanded to a URL QueryString
	 * @param params
	 * @return
	 */
	private static String buildQueryString(Map<String, String> params) {
		StringBuilder queryString = new StringBuilder();
		queryString.append("?");
		if (params!=null){
			Set<Entry<String, String>> entrySet = params.entrySet();
			for (Entry<String, String> paramPair : entrySet) {
				queryString.append(paramPair.getKey());
				queryString.append("=");
				queryString.append(CommonUtils.encode(paramPair.getValue()));
				queryString.append("&");
			}
		}
		
		//delete the last & at the end
		int lastCharPos=queryString.length()-1;
		if ('&'==queryString.charAt(lastCharPos)){
			queryString.deleteCharAt(lastCharPos);
		}
		return queryString.toString();
	}

	/**
	 * Executes the request, gets the response, extracts the entity out of it and returns it.
	 * @param request
	 * @return
	 * @throws CommunicationException
	 */
	private static HttpEntity getResponseEntity(HttpUriRequest request) throws CommunicationException {

		HttpEntity responseEntity=null;

		try {
			HttpResponse response = getDefaultHTTPClient().execute(request);

			int statusCode = response.getStatusLine().getStatusCode();

			if (statusCode != HttpStatus.SC_OK) {
				throw new CommunicationException(Constants.ERROR_DURING_PROCESSING + "::: HTTP status code :" + statusCode);
			}
			responseEntity = response.getEntity();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			throw new CommunicationException(Constants.ERROR_DURING_PROCESSING + e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			throw new CommunicationException(Constants.ERROR_DURING_PROCESSING + e.getMessage());
		}

		return responseEntity;
	}

	public static boolean isURLValid(String url) {
		// FIXME Use HTTPClient to verify URL validity
		return true;

	}

}
