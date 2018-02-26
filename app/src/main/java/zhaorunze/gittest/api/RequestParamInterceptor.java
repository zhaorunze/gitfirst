package zhaorunze.gittest.api;

import android.util.Log;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import zhaorunze.gittest.config.Constant;

public class RequestParamInterceptor implements Interceptor {
    private static final String TAG = "RequestParamInterceptor";
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request oldRequest = chain.request();
        HttpUrl.Builder urlBuilder = oldRequest.url()
                .newBuilder()
                .scheme(oldRequest.url().scheme())
                .host(oldRequest.url().host());
        Request newRequest = oldRequest.newBuilder()
                .addHeader("Authorization", Constant.TOKEN)//添加头部额外信息
                .method(oldRequest.method(), oldRequest.body())
                .url(urlBuilder.build())
                .build();
        Log.d(TAG, newRequest.toString());
        return chain.proceed(newRequest);
    }
}
