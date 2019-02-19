package org.light.collect.android.naxa.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ServiceGenerator {

    private static Gson gson = new GsonBuilder().create();
    private static Retrofit rxRetrofit;
    private static OkHttpClient okHttp;


    public static void clearInstance() {
        rxRetrofit = null;
        okHttp = null;
    }


    private static OkHttpClient createOkHttpClient() {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();


        okHttpClientBuilder.connectTimeout(10, TimeUnit.SECONDS);
        okHttpClientBuilder.writeTimeout(10, TimeUnit.SECONDS);
        okHttpClientBuilder.readTimeout(60, TimeUnit.SECONDS);

        return okHttpClientBuilder
                .build();
    }

    public static Retrofit getRxClient() {


        if (okHttp == null) {
            okHttp = createOkHttpClient();
        }

        if (rxRetrofit == null) {
            rxRetrofit = new Retrofit.Builder()
                    .client(okHttp)
                    .baseUrl(APIEndpoint.BASE_URL)
                    .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }


        return rxRetrofit;
    }



}
