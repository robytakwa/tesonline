package com.arbaelbarca.listmovieskatalog.network;

import com.arbaelbarca.listmovieskatalog.utils.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkApi {
    private ApiServices apiServices;
    private static NetworkApi networkApi;
    private String credentials;

    private NetworkApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL_MOVIES)
                .client(provideOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        apiServices = retrofit.create(ApiServices.class);

    }



    private static OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(getLoggingInterceptor())
                .addInterceptor(chain -> {
                    Request request = chain.request();
                    Request authenticatedRequest = request.newBuilder()
                            .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkY2Y1NzYzNjkxYTJhOTYwZDJkMjQyMDk3Y2RiMWY2YyIsInN1YiI6IjU5ODU1NDEzOTI1MTQxNDM4NzAxOGMyZSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.Pg0vpPU8iOhVHW3g7ZUEOkA3cMp9JEwMpx9AhD-92Tk").build();
                    return chain.proceed(authenticatedRequest);
                })
                .readTimeout(15, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build();
    }

    private static HttpLoggingInterceptor getLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        return interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    public ApiServices getAPI() {
        return apiServices;
    }

    public static NetworkApi getInstance() {
        if (networkApi == null)
            networkApi = new NetworkApi();
        return networkApi;
    }
}
