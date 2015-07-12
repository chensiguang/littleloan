package com.hexin.core.http;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

public class HttpClientUtil {
	private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

	public static String getReponse(String url) {

		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		CloseableHttpClient httpClient = httpClientBuilder.build();
		HttpGet httpGet;
		try {
			httpGet = new HttpGet(url);

			HttpResponse httpResponse = httpClient.execute(httpGet);
			HttpEntity entity = httpResponse.getEntity();
			if (entity != null) {
				String xml = EntityUtils.toString(entity);
				return xml;
			}

		} catch (IOException e) {
			logger.error(e.getMessage());
		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
		}
		return null;
	}

	public static String post(String url, String jsonData) throws IOException {
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		CloseableHttpClient client = httpClientBuilder.build();  
		HttpPost httpPost = new HttpPost(url);
		
		List <NameValuePair> params = new ArrayList<NameValuePair>();  
        params.add(new BasicNameValuePair("data", jsonData));
		  
		httpPost.setEntity(new UrlEncodedFormEntity(params,Consts.UTF_8));
		try {  
		    // 执行get请求  
		    HttpResponse httpResponse = client.execute(httpPost);  
		    // 获取响应消息实体  
		    HttpEntity entity = httpResponse.getEntity();  
		    // 响应状态  
		    System.out.println("status:" + httpResponse.getStatusLine());
		    // 判断响应实体是否为空  
		    if (entity != null) {
		    	return EntityUtils.toString(entity);
		    }  
		} catch (IOException e) {  
		    throw e;  
		} finally {  
		    try { 
		    	client.close();  
		    } catch (IOException e) {  
		        throw e;  
		    }  
		} 
		return null;
	}
	
	@SuppressWarnings("deprecation")
	public static boolean download(String url, String filepath) {  
		HttpClient client = new DefaultHttpClient();  
		HttpGet httpget = new HttpGet(url);  
        try {  
            HttpResponse response = client.execute(httpget);  
  
            HttpEntity entity = response.getEntity();  
            InputStream is = entity.getContent();  
            File file = new File(filepath);  
            file.getParentFile().mkdirs();  
            FileOutputStream fileout = new FileOutputStream(file);  
            /** 
             * 根据实际运行效果 设置缓冲区大小 
             */  
            byte[] buffer=new byte[10240];  
            int ch = 0;  
            while ((ch = is.read(buffer)) != -1) {  
                fileout.write(buffer,0,ch);  
            }  
            is.close();  
            fileout.flush();  
            fileout.close();  
            logger.info("download file success : "+ url);
            return true;
        } catch (Exception e) {  
            logger.error("download file  error :" + url +" => " + e.getMessage());
        }  
        finally{
        	httpget.releaseConnection();
        }
        return false;  
    }

}
