package com.weix.commonUtils;

import android.text.TextUtils;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.bind.TypeAdapters;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Email：langmanleguang@qq.com
 */
public class HttpHelper {

    private static final String TAG = HttpHelper.class.getSimpleName();
    public static final String BASE_URL = "http://192.168.1.144:8080/jeecg/";
    private static OkHttpClient mOkHttpClient;
    private static Retrofit mRetrofit;
    private volatile static HttpHelper INSTANCE;

    //构造方法私有
    private HttpHelper() {
        initRetrofit();
    }

    //获取单例
    public static HttpHelper getInstance() {
        if (INSTANCE == null) {
            synchronized (HttpHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new HttpHelper();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * 初始化OkHttp
     */
    private void initOkHttpClient() {
        if (null == mOkHttpClient) {

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            if (builder.interceptors() != null) {
                builder.interceptors().clear();
            }

//            File cacheFile = new File(Constants.PATH_NET_CACHE);
//            Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);


            //设置缓存
//            builder.cache(cache);
            //设置超时
            builder.connectTimeout(1000, TimeUnit.SECONDS);
            builder.readTimeout(1000, TimeUnit.SECONDS);
            builder.writeTimeout(1000, TimeUnit.SECONDS);
            //错误重连
            builder.retryOnConnectionFailure(true);

            builder.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    int maxAge = 60 * 60; // 有网络时 设置缓存超时时间1个小时
                    int maxStale = 60 * 60 * 24 * 28; // 无网络时，设置超时为4周
                    Request request = chain.request();
//                    if (IsNetWork.isNetworkReachable(MyApp.getContext())){
                    request = request.newBuilder()
                            .addHeader("X-AUTH-TOKEN","token")
                            .addHeader("Content-Type", "application/x-www-form-urlencoded")
                            .cacheControl(CacheControl.FORCE_NETWORK)//有网络时只从网络获取
                            .build();
                    //添加公共token
//                    } else {
//                        request = request.newBuilder()
//                                .cacheControl(CacheControl.FORCE_CACHE)//无网络时只从缓存中读取
//                                .build();
//                    }
                    Response response = chain.proceed(request);
//                    if (IsNetWork.isNetworkReachable(MyApp.getContext())) {
                    response = response.newBuilder()
                            .removeHeader("Pragma")
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .build();
//                    } else {
//                        response = response.newBuilder()
//                                .removeHeader("Pragma")
//                                .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
//                                .build();
//                    }
                    return response;
                }
            });

            //DEBUG模式下配Log拦截器
            if (true) {
                HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                builder.addInterceptor(loggingInterceptor);
            }

//            builder.addNetworkInterceptor();

            if (true) {
                builder.addInterceptor(new LoggingInterceptor());
            }
            mOkHttpClient = builder.build();
        }
    }

    private void initRetrofit() {
        if (null == mRetrofit) {
            initOkHttpClient();

            GsonBuilder gsonBuilder = new GsonBuilder();

            // Gson double类型转换, 避免空字符串解析出错
            final TypeAdapter<Number> DOUBLE = new TypeAdapter<Number>() {
                @Override
                public Number read(JsonReader in) throws IOException {
                    if (in.peek() == JsonToken.NULL) {
                        in.nextNull();
                        return null;
                    }
                    if (in.peek() == JsonToken.STRING) {
                        String tmp = in.nextString();
                        if (TextUtils.isEmpty(tmp)) tmp = "0";
                        return Double.parseDouble(tmp);
                    }
                    return in.nextDouble();
                }

                @Override
                public void write(JsonWriter out, Number value) throws IOException {
                    out.value(value);
                }
            };

            // Gson long类型转换, 避免空字符串解析出错
            final TypeAdapter<Number> LONG = new TypeAdapter<Number>() {
                @Override
                public Number read(JsonReader in) throws IOException {
                    if (in.peek() == JsonToken.NULL) {
                        in.nextNull();
                        return null;
                    }
                    if (in.peek() == JsonToken.STRING) {
                        String tmp = in.nextString();
                        if (TextUtils.isEmpty(tmp)) tmp = "0";
                        return Long.parseLong(tmp);
                    }
                    return in.nextLong();
                }

                @Override
                public void write(JsonWriter out, Number value) throws IOException {
                    out.value(value);
                }
            };

            // Gson int类型转换, 避免空字符串解析出错
            final TypeAdapter<Number> INT = new TypeAdapter<Number>() {
                @Override
                public Number read(JsonReader in) throws IOException {
                    if (in.peek() == JsonToken.NULL) {
                        in.nextNull();
                        return null;
                    }
                    if (in.peek() == JsonToken.STRING) {
                        String tmp = in.nextString();
                        if (TextUtils.isEmpty(tmp)) tmp = "0";
                        return Integer.parseInt(tmp);
                    }
                    return in.nextInt();
                }

                @Override
                public void write(JsonWriter out, Number value) throws IOException {
                    out.value(value);
                }
            };

            gsonBuilder.registerTypeAdapterFactory(TypeAdapters.newFactory(double.class, Double.class, DOUBLE));
            gsonBuilder.registerTypeAdapterFactory(TypeAdapters.newFactory(long.class, Long.class, LONG));
            gsonBuilder.registerTypeAdapterFactory(TypeAdapters.newFactory(int.class, Integer.class, INT));

            mRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(mOkHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }
    }

    public ApiService initService() {
        return mRetrofit.create(ApiService.class);
    }
}
