package com.parch.combine.core.component.tools;

import com.parch.combine.core.common.util.IOUtil;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * HTTP请求帮助类
 */
public class HttpTool {

    /**
     * 发送GET请求
     *
     * @param url     请求的URL地址
     * @param params  GET请求的参数
     * @param headers 请求头的参数
     * @param retry   重试次数
     * @param timeout 超时时间
     * @return 服务器响应的结果
     */
    public static String doGet(String url, Map<String, Object> params, Map<String, String> headers, int retry, int timeout) throws IOException {
        url = buildGetUrl(url, params);
        for (int i = 0; i < retry; i++) {
            try {
                HttpURLConnection conn = buildConnection(url, "GET", headers, timeout);
                int code = conn.getResponseCode();
                if (code == HttpURLConnection.HTTP_OK) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    reader.close();
                    return response.toString();
                }
            } catch (IOException e) {
                if (i == retry - 1) {
                    throw e;
                }
            }
            randomDelay();
        }
        return null;
    }

    /**
     * 发送POST请求
     *
     * @param url     请求的URL地址
     * @param params  POST请求的参数
     * @param headers 请求头的参数
     * @param retry   重试次数
     * @param timeout 超时时间
     * @return 服务器响应的结果
     */
    public static String doPost(String url, String params, Map<String, String> headers, int retry, int timeout) throws IOException {
        for (int i = 0; i < retry; i++) {
            try {
                HttpURLConnection conn = buildConnection(url, "POST", headers, timeout);
                conn.setDoOutput(true);

                OutputStream outputStream = conn.getOutputStream();
                outputStream.write(params.getBytes());
                outputStream.flush();
                outputStream.close();
                int code = conn.getResponseCode();
                if (code == HttpURLConnection.HTTP_OK) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    reader.close();
                    return response.toString();
                }
            } catch (IOException e) {
                if (i == retry - 1) {
                    throw e;
                }
            }
            randomDelay();
        }
        return null;
    }

    /**
     * 文件下载
     *
     * @param url      下载文件的URL地址
     * @param retry    重试次数
     * @param timeout  超时时间
     * @return 下载成功返回true，否则返回false
     */
    public static byte[] downloadFile(String url, Map<String, Object> params, Map<String, String> headers, int retry, int timeout) throws IOException {
        url = buildGetUrl(url, params);
        for (int i = 0; i < retry; i++) {
            try {
                HttpURLConnection conn = buildConnection(url, "GET", headers, timeout);

                int code = conn.getResponseCode();
                if (code == HttpURLConnection.HTTP_OK) {
                    InputStream inputStream = conn.getInputStream();
                    byte[] data = IOUtil.toByteArray(inputStream);
                    inputStream.close();
                    return data;
                }
            } catch (IOException e) {
                if (i == retry - 1) {
                    throw e;
                }
            }
            randomDelay();
        }
        return null;
    }

    private static HttpURLConnection buildConnection(String url, String mode, Map<String, String> headers, int timeout) throws IOException {
        URL urlObj = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
        conn.setRequestMethod(mode);
        conn.setConnectTimeout(timeout);
        conn.setReadTimeout(timeout);
        setHeaders(conn, headers);

        return conn;
    }

    private static String buildGetUrl(String url, Map<String, Object> params) {
        StringBuilder sb = new StringBuilder(url);
        if (params != null && !params.isEmpty()) {
            sb.append("?");
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    private static void setHeaders(HttpURLConnection conn, Map<String, String> headers) {
        if (headers != null && !headers.isEmpty()) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                conn.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }
    }

    private static void randomDelay() {
        try {
            //增加随机延迟，避免并发情况下同时重试导致请求量过大
            Thread.sleep((long) (1000 * Math.random()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
