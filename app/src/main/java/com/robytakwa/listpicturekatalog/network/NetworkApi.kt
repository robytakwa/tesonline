package com.robytakwa.listpicturekatalog.network

import com.robytakwa.listpicturekatalog.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class NetworkApi private constructor() {
    val api: ApiServices
    private val credentials: String? = null

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL_MOVIES)
                .client(provideOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()

        api = retrofit.create(ApiServices::class.java)

    }

    companion object {
        private var networkApi: NetworkApi? = null


        private fun provideOkHttpClient(): OkHttpClient {
            return OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .addInterceptor { chain ->
                        val request = chain.request()
                        val authenticatedRequest = request.newBuilder()
                                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkY2Y1NzYzNjkxYTJhOTYwZDJkMjQyMDk3Y2RiMWY2YyIsInN1YiI6IjU5ODU1NDEzOTI1MTQxNDM4NzAxOGMyZSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.Pg0vpPU8iOhVHW3g7ZUEOkA3cMp9JEwMpx9AhD-92Tk").build()
                        chain.proceed(authenticatedRequest)
                    }
                    .readTimeout(15, TimeUnit.SECONDS)
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .writeTimeout(15, TimeUnit.SECONDS)
                    .build()
        }

        private val loggingInterceptor: HttpLoggingInterceptor
            get() {
                val interceptor = HttpLoggingInterceptor()
                return interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            }

        val instance: NetworkApi
            get() {
                if (networkApi == null)
                    networkApi = NetworkApi()
                return networkApi!!
            }
    }
}
