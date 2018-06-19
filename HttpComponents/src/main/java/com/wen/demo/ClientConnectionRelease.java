package com.wen.demo;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by wen on 2018/6/19
 *
 * 测试关闭连接
 */
public class ClientConnectionRelease {
    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpGet httpGet = new HttpGet("http://httpbin.org/get");
            System.out.println("以GET方式发送Http请求"+httpGet.getRequestLine());
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
            try {
                System.out.println("----------------------------------------");
                System.out.println(httpResponse.getStatusLine());
                // 获取响应实体
                HttpEntity entity = httpResponse.getEntity();
                if (entity != null){
                    InputStream inputStream = entity.getContent();
                    try {
                        inputStream.read();
                        //可以在此处对响应内容做一些其他处理
                    } finally {
                        inputStream.close();
                    }
                }
            } finally {
                httpResponse.close();
            }
        } finally {
            httpClient.close();
        }
    }
}
