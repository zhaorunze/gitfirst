package zhaorunze.gittest.api;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateFactory;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import zhaorunze.gittest.api.customgson.NullOnEmptyConverterFactory;
import zhaorunze.gittest.config.Constant;


public class RetrofitClient {

    private static Retrofit mRetrofit;
    private static final String TAG = "RetrofitClient";

    private RetrofitClient() {
    }

    public static Retrofit builderRetrofit() {
        if (mRetrofit == null) {
            Gson gson = new GsonBuilder().registerTypeAdapterFactory(new NullToDefaultValueAdapterFactory()).create();
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(Constant.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addConverterFactory(new NullOnEmptyConverterFactory())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(getOkHttp())
                    .build();
        }
        return mRetrofit;
    }

    public static SSLSocketFactory getSSLSocketFactory(Context context, int[] certificates){
        if(context == null){
            throw new NullPointerException("context is null");
        }
        CertificateFactory certificateFactory;
        try {
            certificateFactory = CertificateFactory.getInstance("X.509");
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null, null);
            for (int i = 0; i < certificates.length; i++) {
                InputStream is = context.getResources().openRawResource(certificates[i]);
                keyStore.setCertificateEntry(String.valueOf(i), certificateFactory.generateCertificate(is));
                if(is != null){
                    is.close();
                }
            }
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustManagerFactory.getTrustManagers(), new SecureRandom());
            return sslContext.getSocketFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static OkHttpClient getOkHttp() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d(TAG, message);
            }
        });
        RequestParamInterceptor requestParamInterceptor = new RequestParamInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);
        httpClient.connectTimeout(Constant.NET_TIME_OUT, TimeUnit.SECONDS);
        httpClient.addNetworkInterceptor(requestParamInterceptor);

        /*int[] certificates = new int[] {R.raw.app_key};
        SSLSocketFactory sslSocketFactory = getSSLSocketFactory(AppContext.getInstance(), certificates);
        if(sslSocketFactory != null){
            httpClient.sslSocketFactory(sslSocketFactory);
        }
        */
        return httpClient.build();
    }

    public static class NullToDefaultValueAdapterFactory<T> implements TypeAdapterFactory {

        @Override
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
            Class<T> rawType = (Class<T>) type.getRawType();
            if (rawType == String.class) {
                return (TypeAdapter<T>) new StringNullAdapter();
            }

            if (rawType == Integer.class) {
                return (TypeAdapter<T>) new IntegerNullAdapter();
            }

            if (rawType == Long.class) {
                return (TypeAdapter<T>) new LongNullAdapter();
            }

            if (rawType == Double.class) {
                return (TypeAdapter<T>) new DoubleNullAdapter();
            }

            if (rawType == Boolean.class) {
                return (TypeAdapter<T>) new BooleanNullAdapter();
            }
            return null;
        }
    }

    public static class StringNullAdapter extends TypeAdapter<String> {

        @Override
        public void write(JsonWriter out, String value) throws IOException {
            if (value == null) {
                out.nullValue();
                return;
            }
            out.value(value);
        }

        @Override
        public String read(JsonReader in) throws IOException {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return "";
            }
            return in.nextString();
        }
    }

    public static class IntegerNullAdapter extends TypeAdapter<Integer> {

        @Override
        public void write(JsonWriter out, Integer value) throws IOException {
            if (value == null) {
                out.nullValue();
                return;
            }
            out.value(value);
        }

        @Override
        public Integer read(JsonReader in) throws IOException {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return 0;
            }
            return in.nextInt();
        }
    }

    public static class LongNullAdapter extends TypeAdapter<Long> {

        @Override
        public void write(JsonWriter out, Long value) throws IOException {
            if (value == null) {
                out.nullValue();
                return;
            }
            out.value(value);
        }

        @Override
        public Long read(JsonReader in) throws IOException {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return 0L;
            }
            return in.nextLong();
        }
    }

    public static class DoubleNullAdapter extends TypeAdapter<Double> {
        @Override
        public void write(JsonWriter out, Double value) throws IOException {
            if (value == null) {
                out.nullValue();
                return;
            }
            out.value(value);
        }

        @Override
        public Double read(JsonReader in) throws IOException {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return 0D;
            }
            return in.nextDouble();
        }
    }

    public static class BooleanNullAdapter extends TypeAdapter<Boolean> {
        @Override
        public void write(JsonWriter out, Boolean value) throws IOException {
            if (value == null) {
                out.nullValue();
                return;
            }
            out.value(value);
        }

        @Override
        public Boolean read(JsonReader in) throws IOException {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return false;
            }
            return in.nextBoolean();
        }
    }

}
