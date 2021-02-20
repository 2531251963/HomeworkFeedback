package com.example.demo.common.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.nio.charset.Charset;

/**
 * @desc HttpUtils
 */
public class HttpUtils {

    public static String httpPost(String url,HttpEntity entity){
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try{
            HttpPost post = new HttpPost(url);
            post.setHeader("Content-Type", "application/json");
            post.setEntity(entity);
            HttpResponse response = httpclient.execute(post);
            return EntityUtils.toString(response.getEntity(), Charset.forName("UTF-8"));
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        finally {
            try{
                httpclient.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
