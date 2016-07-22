package com.skysea.monitor.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skysea.monitor.web.Keys;

public class HttpClientUtil {
	
	private static Logger LOG = LoggerFactory.getLogger(HttpClientUtil.class);
	
	private static CloseableHttpClient httpClient = HttpClients.createDefault();
	
	public static String[] doGet(String url,Integer maxConnMillisecond) {
		if(StringUtils.isBlank(url)){
			return null;
		}
		HttpGet httpGet = new HttpGet(url);
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(maxConnMillisecond).build();  
		httpGet.setConfig(requestConfig);
		CloseableHttpResponse response = null;
		InputStream is = null;
		ByteArrayOutputStream outStream = null;
		try {
			long timeS = System.currentTimeMillis();
			response = httpClient.execute(httpGet);
			StatusLine statusLine = response.getStatusLine();
			LOG.info(statusLine.toString());
			HttpEntity entity = response.getEntity();
			long timeE = System.currentTimeMillis();
			if (null != entity) {
				is = entity.getContent();
				outStream = new ByteArrayOutputStream();
				byte[] data = new byte[1024];
				int count = -1;
				while ((count = is.read(data)) != -1) {
					outStream.write(data, 0, count);
				}
				data = null;
				String resultStr = new String(outStream.toByteArray(), "UTF-8");
				LOG.info(resultStr);
				return new String[]{(timeE - timeS) + "", resultStr.length() + "",resultStr};
			}
		} catch (ClientProtocolException e) {
			LOG.error(e.getMessage(),e);
		} catch (IOException e) {
			LOG.error(e.getMessage(),e);
		} finally {
			try {
				if (null != outStream) {
					outStream.close();
					outStream = null;
				}
				if (null != is) {
					is.close();
					is = null;
				}
				if (null != response) {
					response.close();
					response = null;
				}
				if(null != httpGet){
					httpGet.releaseConnection();
					httpGet = null;
				}
			} catch (IOException e) {
				LOG.error(e.getMessage(),e);
			}
		}
		return null;
	}
	
	
	public static String doGet(String url,Map<String,String> paramMap) {
		return doGet(url,paramMap,Keys.SYS_ENCODING);
	}
	public static String doGet(String url,Map<String,String> paramMap,String charSet) {
		if(StringUtils.isBlank(url)){
			return null;
		}
		if (StringUtils.isBlank(charSet)) {
			charSet = Keys.SYS_ENCODING;
		}
		
		CloseableHttpResponse response = null;
		InputStream is = null;
		try {
			if (null != paramMap && paramMap.size() > 0) {
				List<NameValuePair> pairList = new ArrayList<NameValuePair>(paramMap.size());
				for (Map.Entry<String, String> entry : paramMap.entrySet()) {
					pairList.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
				}
				if(url.indexOf("?") != -1){
					url += EntityUtils.toString(new UrlEncodedFormEntity(pairList, charSet));
				}else{
					url += "?" + EntityUtils.toString(new UrlEncodedFormEntity(pairList, charSet));
				}
			}
			HttpGet httpGet = new HttpGet(url);
			httpGet.addHeader("Content-type","application/json; charset="+charSet);  
			httpGet.setHeader("Accept", "application/json");
			response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			if (null != entity) {
				is = entity.getContent();
				if(null != is){
					StringBuffer buffer = new StringBuffer();
					byte[] b = new byte[1024];
					int result = is.read(b);
					while (result != -1) {
						buffer.append(new String(b, 0, result, Keys.SYS_ENCODING));
						result = is.read(b);
					}
					return buffer.toString();
				}
			}
		} catch (ClientProtocolException e) {
			LOG.error(e.getMessage(),e);
		} catch (IOException e) {
			LOG.error(e.getMessage(),e);
		} finally {
			try {
				if (null != is) {
					is.close();
					is = null;
				}
				if (null != response) {
					response.close();
					response = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
				LOG.error(e.getMessage(),e);
			}
		}
		return null;
	}
	public static String doPost(String url,String entity,Map<String,Object> paramMap) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			return doPost(url,"{\"" + entity + "\":" + mapper.writeValueAsString(paramMap) + "}",Keys.SYS_ENCODING);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static String doPost(String url,String param) {
		return doPost(url,param,Keys.SYS_ENCODING);
	}
	public static String doPost(String url,String param,String charSet) {
		if(StringUtils.isBlank(url)){
			return null;
		}
		if (StringUtils.isBlank(charSet)) {
			charSet = Keys.SYS_ENCODING;
		}
		CloseableHttpResponse response = null;
		InputStream is = null;
		try {
			HttpPost httpPost = new HttpPost(url);
			httpPost.addHeader("Content-type","application/json; charset="+charSet);  
			httpPost.setHeader("Accept", "application/json");  
			if (StringUtils.isNotBlank(param)) {
				httpPost.setEntity(new StringEntity(param,Charset.forName(charSet))); 
			}
			response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			if (null != entity) {
				is = entity.getContent();
				if(null != is){
					StringBuffer buffer = new StringBuffer();
					byte[] b = new byte[1024];
					int result = is.read(b);
					while (result != -1) {
						buffer.append(new String(b, 0, result, Keys.SYS_ENCODING));
						result = is.read(b);
					}
					return buffer.toString();
				}
			}
		} catch (ClientProtocolException e) {
			LOG.error(e.getMessage(),e);
		} catch (IOException e) {
			LOG.error(e.getMessage(),e);
		} finally {
			try {
				if (null != is) {
					is.close();
				}
				if (null != response) {
					response.close();
				}
			} catch (IOException e) {
				LOG.error(e.getMessage(),e);
			}
		}
		return null;
	}
	
	public static void main(String[] args){
		
	}
}
