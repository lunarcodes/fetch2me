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
	public static HttpEntity getResponseForGetRequest(String url, Map<String, String> params) {

		HttpUriRequest request = constructGetRequest(url, params);
		return (getResponseEntity(request));

	}

	/**
	 * Constructs request object from a URL. 
	 * @param url
	 * @param params
	 * @return
	 */
	private static HttpUriRequest constructGetRequest(String url, Map<String, String> params) {
		HttpGet httpGet = null;
		HttpUriRequest request = null;

		if (params.size() > 0) {
			String queryString = buildQueryString(params);
			url = url + queryString;
		}
		System.out.println(url);

		request = new HttpGet(url);
		return request;
	}

	/**
	 * Param map expanded to a URL
	 * @param params
	 * @return
	 */
	private static String buildQueryString(Map<String, String> params) {
		StringBuilder queryString = new StringBuilder();
		queryString.append("?");
		Set<Entry<String, String>> entrySet = params.entrySet();
		for (Entry<String, String> paramPair : entrySet) {
			queryString.append(paramPair.getKey());
			queryString.append("=");
			queryString.append(CommonUtils.encode(paramPair.getValue()));
			queryString.append("&");
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

		HttpResponse response = null;
		HttpEntity entity = null;

		try {
			response = getDefaultHTTPClient().execute(request);

			int statusCode = response.getStatusLine().getStatusCode();

			if (statusCode != HttpStatus.SC_OK) {
				throw new CommunicationException(Constants.ERROR_DURING_PROCESSING + "::: HTTP status code :" + statusCode);
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			throw new CommunicationException(Constants.ERROR_DURING_PROCESSING + e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			throw new CommunicationException(Constants.ERROR_DURING_PROCESSING + e.getMessage());
		}

		entity = response.getEntity();

		return entity;
	}

	public static boolean isURLValid(String url) {
		// FIXME Use HTTPClient to verify URL validity
		return true;

	}

}
