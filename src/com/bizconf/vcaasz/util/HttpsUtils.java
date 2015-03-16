package com.bizconf.vcaasz.util;

import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * @desc 
 * @author Administrator
 * @date 2014-6-23
 */
public class HttpsUtils {

	
	@SuppressWarnings({"unchecked" })
	public static String sendSSLPostRequesttest(String reqURL,String param) {
		
		long responseLength = 0; // 响应长度
		String responseContent = null; // 响应内容
		Map<String, Object> retMap = null;
		
		HttpClient httpClient = new DefaultHttpClient(); // 创建默认的httpClient实例
		httpClient.getParams().setParameter(HttpMethodParams.USER_AGENT, param);
		
		X509TrustManager xtm = new X509TrustManager() { // 创建TrustManager
			public void checkClientTrusted(X509Certificate[] chain,
					String authType) throws CertificateException {
			}
			
			public void checkServerTrusted(X509Certificate[] chain,
					String authType) throws CertificateException {
			}
			
			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}
		};
		try {
			SSLContext ctx = SSLContext.getInstance("TLS");
			ctx.init(null, new TrustManager[] { xtm }, null);
			
			SSLSocketFactory socketFactory = new SSLSocketFactory(ctx);
			httpClient.getConnectionManager().getSchemeRegistry()
			.register(new Scheme("https", 443, socketFactory));
			
			HttpGet httpPost = new HttpGet(reqURL); // 创建HttpPost
//			List<NameValuePair> formParams = new ArrayList<NameValuePair>(); // 构建POST请求的表单参数
//			for (Map.Entry<String, Object> entry : params.entrySet()) {
//				if(entry.getKey()!=null && entry.getValue()!=null){
//					formParams.add(new BasicNameValuePair(entry.getKey(), entry
//							.getValue().toString()));
//				}
//			}
//			httpPost.setEntity(new UrlEncodedFormEntity(formParams, "UTF-8"));
			
			HttpResponse response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity(); // 获取响应实体
			
			if (null != entity) {
				responseLength = entity.getContentLength();
				responseContent = EntityUtils.toString(entity, "UTF-8");
				EntityUtils.consume(entity); // Consume response content
			}
			System.out.println("the response str == ");
			System.out.println(responseContent);
			
			
			System.out.println("请求地址: " + httpPost.getURI());
			System.out.println("响应状态: " + response.getStatusLine());
			System.out.println("响应长度: " + responseLength);
			return responseContent;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpClient.getConnectionManager().shutdown(); // 关闭连接,释放资源
		}
		return null;
	}
	
	
	/**
	 * 判断URL是否可用
	 * @param urlStr
	 * @return
	 */
	public synchronized static boolean URLenable(String urlStr) {
		int counts = 0;
		if (urlStr == null || urlStr.length() <= 0 || "".equals(urlStr)) {                       
			return false;                 
		}
		URL u = null;
		HttpURLConnection c = null;
		int s = -1;
		while (counts < 5) {
			try {
				u = new URL(urlStr);
				c = (HttpURLConnection) u.openConnection();
				s = c.getResponseCode();
				if (s == 200) {
					return true;
				}
				counts++;
			}catch (Exception ex) {
				counts++; 
				urlStr = null;
				continue;
			}
		}
		return false;
	}
	
}
