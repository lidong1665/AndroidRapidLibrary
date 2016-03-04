package com.lidong.demo.gpush;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Map.Entry;

import org.json.simple.JSONObject;

import android.os.AsyncTask;

public class GetuiSdkHttpPost {

    private static final String SERVICEURL = "http://sdk.open.api.igexin.com/service";
    private static final int CONNECTION_TIMEOUT_INT = 8000;
    private static final int READ_TIMEOUT_INT = 5000;

    public static void httpPost(final Map<String, Object> map) {

        new AsyncTask<Void, Integer, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                String param = JSONObject.toJSONString(map);

                if (param != null) {
                    URL url = null;
                    BufferedReader bufferReader = null;
                    HttpURLConnection urlConn = null;

                    try {
                        url = new URL(SERVICEURL);
                        urlConn = (HttpURLConnection) url.openConnection();
                        urlConn.setDoInput(true); // 设置输入流采用字节流
                        urlConn.setDoOutput(true); // 设置输出流采用字节流
                        urlConn.setRequestMethod("POST");
                        urlConn.setUseCaches(false); // 设置缓存
                        urlConn.setRequestProperty("Charset", "utf-8");
                        urlConn.setConnectTimeout(CONNECTION_TIMEOUT_INT);
                        urlConn.setReadTimeout(READ_TIMEOUT_INT);

                        urlConn.connect(); // 连接既往服务端发送消息

                        DataOutputStream dop = new DataOutputStream(urlConn.getOutputStream());
                        dop.write(param.getBytes("utf-8")); // 发送参数
                        dop.flush(); // 发送，清空缓存
                        dop.close(); // 关闭

                        // 下面开始做接收工作
                        bufferReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
                        String result = ""; // 获取服务器返回数据
                        String readLine = null;

                        while ((readLine = bufferReader.readLine()) != null) {
                            result += readLine;
                        }

                        System.out.println("result： " + result);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        if (urlConn != null) {
                            urlConn.disconnect();
                        }

                        if (bufferReader != null) {
                            try {
                                bufferReader.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                } else {
                    System.out.println("param is null");
                }
                return null;
            }
        }.execute();
    }

    /**
     * 生成Sign方法
     */
    public static String makeSign(String masterSecret, Map<String, Object> params) throws IllegalArgumentException {
        if (masterSecret == null || params == null) {
            throw new IllegalArgumentException("masterSecret and params can not be null.");
        }

        if (!(params instanceof SortedMap)) {
            params = new TreeMap<String, Object>(params);
        }

        StringBuilder input = new StringBuilder(masterSecret);
        for (Entry<String, Object> entry : params.entrySet()) {
            Object value = entry.getValue();
            if (value instanceof String || value instanceof Integer || value instanceof Long) {
                input.append(entry.getKey());
                input.append(entry.getValue());
            }
        }

        return getMD5Str(input.toString());
    }

    /**
     * MD5加密
     */
    public static String getMD5Str(String sourceStr) {
        byte[] source = sourceStr.getBytes();
        // 用来将字节转换成 16 进制表示的字符
        char hexDigits[] = new char[] {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        java.security.MessageDigest md = null;

        try {
            md = java.security.MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        if (md == null) {
            return null;
        }

        md.update(source);
        byte tmp[] = md.digest(); // MD5 的计算结果是一个 128 位的长整数，
        // 用字节表示就是 16 个字节
        char str[] = new char[16 * 2]; // 每个字节用 16 进制表示的话，使用两个字符，
        // 所以表示成 16 进制需要 32 个字符
        int k = 0; // 表示转换结果中对应的字符位置
        for (int i = 0; i < 16; i++) {
            // 从第一个字节开始，对 MD5 的每一个字节
            // 转换成 16 进制字符的转换
            byte byte0 = tmp[i]; // 取第 i 个字节
            str[k++] = hexDigits[byte0 >>> 4 & 0xf]; // 取字节中高 4 位的数字转换,
            // >>> 为逻辑右移，将符号位一起右移
            str[k++] = hexDigits[byte0 & 0xf]; // 取字节中低 4 位的数字转换
        }

        return new String(str); // 换后的结果转换为字符串
    }

}
