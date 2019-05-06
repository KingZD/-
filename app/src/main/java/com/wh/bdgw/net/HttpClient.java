package com.wh.bdgw.net;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.google.gson.GsonBuilder;
import com.wh.bdgw.util.StringUtils;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class HttpClient {
    private static HttpClient INSTANCE;
    private static Api api;

    private HttpClient() {
        Bundle bundle = StringUtils.getMetaData();
        //打印请求Log
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        if (bundle != null && !bundle.getBoolean("DEBUG"))
            logInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        else
            logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //okHttpClient
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                //打印请求log
                .addInterceptor(logInterceptor)
//                .addInterceptor(new CommonInterceptor())
                //失败重连
                .retryOnConnectionFailure(true)
                //time out
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .sslSocketFactory(createSSLSocketFactory(), new TrustAllManager())
                .hostnameVerifier(new TrustAllHostnameVerifier())
                .build();

        Retrofit build = new Retrofit
                .Builder()
                .client(okHttpClient)
                .baseUrl("https://www.baidu.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                //处理java.io.EOFException:End of input at line 1 column 1 path $
                .addConverterFactory(new NullOnEmptyConverterFactory())
                //自适配
                .addCallAdapterFactory(ZedObservableFactory.create())
                .build();
        api = build.create(Api.class);
    }

    public static Api getApi() {
        if (INSTANCE == null) {
            synchronized (HttpClient.class) {
                if (INSTANCE == null) {
                    INSTANCE = new HttpClient();
                }
            }
        }
        return api;
    }

    /**
     * 默认信任所有的证书
     * TODO 最好加上证书认证，主流App都有自己的证书
     *
     * @return
     */
    @SuppressLint("TrulyRandom")
    private static SSLSocketFactory createSSLSocketFactory() {
        SSLSocketFactory sSLSocketFactory = null;
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{new TrustAllManager()},
                    new SecureRandom());
            sSLSocketFactory = sc.getSocketFactory();
        } catch (Exception e) {
        }
        return sSLSocketFactory;
    }

    private static class TrustAllManager implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    private static class TrustAllHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }
}
