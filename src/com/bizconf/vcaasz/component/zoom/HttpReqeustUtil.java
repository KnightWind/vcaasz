package com.bizconf.vcaasz.component.zoom;

import java.nio.charset.CodingErrorAction;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import net.sf.json.JSONObject;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.MessageConstraints;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.bizconf.vcaasz.constant.ConstantUtil;

/**
 * @desc http和https请求
 * @author Darren
 * @date 2014-6-9
 */
public class HttpReqeustUtil {

	private static Logger logger = Logger.getLogger(HttpReqeustUtil.class);
	private static PoolingHttpClientConnectionManager connManager = null;
	private static CloseableHttpClient httpClient = null;
	// 连接池
	static {
		try {
			SSLContext sslContext = SSLContexts.custom().useTLS().build();
	        sslContext.init(null,
	                new TrustManager[] { new X509TrustManager() {
	                    public X509Certificate[] getAcceptedIssuers() {
	                        return null;
	                    }
	                    public void checkClientTrusted(
	                            X509Certificate[] certs, String authType) {
	                    }
	                    public void checkServerTrusted(
	                            X509Certificate[] certs, String authType) {
	                    }
	                }}, null);
			Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder
					.<ConnectionSocketFactory> create()
					.register("http", PlainConnectionSocketFactory.INSTANCE)
					.register("https", new SSLConnectionSocketFactory(sslContext))
					.build();
			
			connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
			httpClient = HttpClients.custom().setConnectionManager(connManager).build();
			
            SocketConfig socketConfig = SocketConfig.custom().setTcpNoDelay(true).build();
            connManager.setDefaultSocketConfig(socketConfig);
            
            MessageConstraints messageConstraints = MessageConstraints.custom()
            		.setMaxHeaderCount(20000).setMaxLineLength(20000).build();
            
            ConnectionConfig connectionConfig = ConnectionConfig.custom()
                .setMalformedInputAction(CodingErrorAction.IGNORE)
                .setUnmappableInputAction(CodingErrorAction.IGNORE)
                .setCharset(Consts.UTF_8)
                .setMessageConstraints(messageConstraints)
                .build();
            
            connManager.setDefaultConnectionConfig(connectionConfig);
            connManager.setMaxTotal(2000);
            connManager.setDefaultMaxPerRoute(connManager.getMaxTotal());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 用户进行鉴权
	 * */
	public static Map<String, Object> getAuthMeetMap() {
		Map<String, Object> params = new HashMap<String, Object>();
//		params.put("api_key", "PhBWUYWROT4z9BtvqlRdVRPcIqpMcvFllIDm");
//		params.put("api_secret", "H4BXlCHJyeYXZxhO1XcDOvhnF94blyzo7gRe");
		params.put("data_type", "json");
		return params;
	}

	/**
	 * 用户进行鉴权
	 * */
	public static Map<String, Object> getAuthMap() {
		Map<String, Object> params = new HashMap<String, Object>();
		// params.put("api_key", "IzwoprpHEfHw5BZJcv0mCU6ruVtH7ruMuVzE");
		// params.put("api_secret", "oB8WFJyzair4XDU7gkIJvE1iw0AAEoXhU61A");
		
//		params.put("api_key", "PhBWUYWROT4z9BtvqlRdVRPcIqpMcvFllIDm");
//		params.put("api_secret", "H4BXlCHJyeYXZxhO1XcDOvhnF94blyzo7gRe");
		params.put("data_type", "json");
		return params;
	}

	/**
	 * 向HTTPS地址发送POST请求
	 * 
	 * @param reqURL
	 *            请求地址
	 * @param params
	 *            请求参数
	 * @return 响应内容
	 */
	@SuppressWarnings({ "unchecked" })
	public static Map<String, Object> sendSSLPostRequest(String reqURL,
			Map<String, Object> params) {
		long start = System.currentTimeMillis();

		long responseLength = 0; // 响应长度
		String responseContent = null; // 响应内容
		Map<String, Object> retMap = null;

		try {
			HttpPost httpPost = new HttpPost(reqURL); // 创建HttpPost
			List<NameValuePair> formParams = new ArrayList<NameValuePair>(); // 构建POST请求的表单参数
			for (Map.Entry<String, Object> entry : params.entrySet()) {
				if (entry.getKey() != null && entry.getValue() != null) {
					formParams.add(new BasicNameValuePair(entry.getKey(), entry
							.getValue().toString()));
				}
			}
			RequestConfig requestConfig = RequestConfig.custom()
                    .setSocketTimeout(ConstantUtil.TIMEOUT)
                    .setConnectTimeout(ConstantUtil.TIMEOUT)
                    .setConnectionRequestTimeout(ConstantUtil.TIMEOUT).build();
			httpPost.setEntity(new UrlEncodedFormEntity(formParams, "UTF-8"));
			httpPost.setConfig(requestConfig);
			HttpResponse response = httpClient.execute(httpPost);

			long end = System.currentTimeMillis();

			HttpEntity entity = response.getEntity(); // 获取响应实体
			if (null != entity) {
				responseLength = entity.getContentLength();
				responseContent = EntityUtils.toString(entity, "UTF-8");
				EntityUtils.consume(entity); // Consume response content
			}
			JSONObject jsonObject = JSONObject.fromObject(responseContent);
			retMap = (Map<String, Object>) jsonObject;

			logger.info("请求地址: " + httpPost.getURI());
			logger.info("响应状态: " + response.getStatusLine());
			logger.info("响应长度: " + responseLength);
			logger.info("响应内容: " + jsonObject);
			logger.info("响应时长: " + (end - start));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retMap;
	}

	@SuppressWarnings("unchecked")
	public static Map<String, Object> sendSSLPostRequesttest(String reqURL) {

		long responseLength = 0; // 响应长度
		String responseContent = null; // 响应内容
		Map<String, Object> retMap = null;

		try {
			HttpGet httpPost = new HttpGet(reqURL); // 创建HttpPost
			HttpResponse response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity(); // 获取响应实体

			if (null != entity) {
				responseLength = entity.getContentLength();
				responseContent = EntityUtils.toString(entity, "UTF-8");
				EntityUtils.consume(entity); // Consume response content
			}
			JSONObject jsonObject = JSONObject.fromObject(responseContent);
			retMap = (Map<String, Object>) jsonObject;

			logger.info("请求地址: " + httpPost.getURI());
			logger.info("响应状态: " + response.getStatusLine());
			logger.info("响应长度: " + responseLength);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return retMap;
	}
	
	public static String sendMsgByGet(String reqURL){
		long responseLength = 0;
		String responseContent = "";
//		HttpClient httpClient = new DefaultHttpClient();
		long start = System.currentTimeMillis();
		HttpGet get = new HttpGet(reqURL);
		try {
			HttpResponse response = httpClient.execute(get);
			HttpEntity entity = response.getEntity();             //获取响应实体 
			long end = System.currentTimeMillis();
	        if (null != entity) { 
	            responseLength = entity.getContentLength(); 
	            responseContent = EntityUtils.toString(entity, "GBK"); 
	            EntityUtils.consume(entity); //Consume response content 
	        } 
	        logger.info("请求地址: " + get.getURI()); 
	        logger.info("响应状态: " + response.getStatusLine()); 
	        logger.info("响应长度: " + responseLength); 
	        logger.info("响应内容: " + responseContent);
	        logger.info("响应时间: " + (end-start));
	        
		}catch (Exception e) {
			e.printStackTrace();
		}
		return responseContent;
	}
	

	public static void main(String... args) {
		sendSSLPostRequesttest("https://www.zoomus.cn/j/1804399383");
	}

}
