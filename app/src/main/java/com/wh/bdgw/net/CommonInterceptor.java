package com.wh.bdgw.net;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 文件名:CommonInterceptor
 * 创建者:zed
 * 创建日期:2019/3/7 9:47
 * 描述:TODO
 */
public class CommonInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        //获取原先的请求
        Request originalRequest = chain.request();
        //重新构建url
        HttpUrl.Builder builder = originalRequest.url().newBuilder();
        //如果是post请求的话就把参数重新拼接一下，get请求的话就可以直接加入公共参数了
        if (originalRequest.method().equals("POST")) {
            RequestBody body = originalRequest.body();
            if (body instanceof FormBody) {
                FormBody fmBody = (FormBody) body;
                for (int i = 0; i < fmBody.size(); i++) {
                    builder.addQueryParameter(fmBody.name(i), fmBody.value(i));
                }
            }
        }
        //这里是我的2个公共参数
        builder.addQueryParameter("key", "f996ee442307b7c6ccfcfc68bcf77682");
        //新的url
        HttpUrl httpUrl = builder.build();
        Request request = originalRequest.newBuilder()
                .method(originalRequest.method(), originalRequest.body())
                .url(httpUrl).build();
        return chain.proceed(request);
    }
}
