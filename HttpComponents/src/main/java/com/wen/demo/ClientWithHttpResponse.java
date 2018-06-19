package com.wen.demo;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by wen on 2018/6/19
 *
 * 测试自定义响应处理
 */
public class ClientWithHttpResponse {
    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpGet httpGet = new HttpGet("http://httpbin.org/");
            System.out.println("以GET方式发送Http请求"+httpGet.getRequestLine());
            /**
             * 创建一个自定义的Http响应处理类，匿名内部类
             */
            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
                @Override
                public String handleResponse(HttpResponse httpResponse) throws ClientProtocolException, IOException {
                    int statusCode = httpResponse.getStatusLine().getStatusCode();
                    if (statusCode >= 200 && statusCode <300){
                        HttpEntity httpEntity = httpResponse.getEntity();
                        return httpEntity != null ? EntityUtils.toString(httpEntity) : null;
                    }else {
                        throw new ClientProtocolException("未知的Http响应状态码："+statusCode);
                    }
                }
            };
            String responseBody = httpClient.execute(httpGet, responseHandler);
            System.out.println("--------------------------------------------");
            System.out.println(responseBody);
        } finally {
            httpClient.close();
        }
    }
}







