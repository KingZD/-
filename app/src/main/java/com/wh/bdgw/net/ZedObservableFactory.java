package com.wh.bdgw.net;

import android.support.annotation.Nullable;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;

/**
 * 文件名:ZedObservableFactory
 * 创建者:zed
 * 创建日期:2019/4/17 10:34
 * 描述:TODO
 */
public class ZedObservableFactory extends CallAdapter.Factory {
    static int maxRetries;
    static int retryDelayMillis;
    static boolean isAsync;

    public static ZedObservableFactory create() {
        isAsync = false;
        return new ZedObservableFactory();
    }

    public static ZedObservableFactory createAsync() {
        isAsync = true;
        return create();
    }

    public static ZedObservableFactory create(int maxRetries, int retryDelayMillis) {
        ZedObservableFactory.maxRetries = maxRetries;
        ZedObservableFactory.retryDelayMillis = retryDelayMillis;
        return new ZedObservableFactory();
    }

    @Nullable
    @Override
    public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        Type observableType = getParameterUpperBound(0, (ParameterizedType) returnType);
        return new GACallAdapter(observableType);
    }

    public class GACallAdapter<R> implements CallAdapter<R, Object> {
        Type returnType;

        public GACallAdapter(Type returnType) {
            this.returnType = returnType;
        }

        @Override
        public Type responseType() {
            return returnType;
        }

        @Override
        public Object adapt(Call<R> call) {
            if (maxRetries > 0)
                return new ZedObservable<>(call, maxRetries, retryDelayMillis);
            return new ZedObservable<>(call);
        }
    }
}