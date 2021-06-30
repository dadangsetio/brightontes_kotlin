package com.repairzone.brightontes.data.network

import android.content.Context
import android.util.Log
import com.repairzone.brightontes.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okio.Buffer
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {
    fun getClient(context: Context): ApiServices {
        val baseUrl = "https://www.omdbapi.com"
        val requestInterceptor = Interceptor { chain ->
            val url = chain.request()
                .url
                .newBuilder()
                .build()

            val request = chain.request()
                .newBuilder()
                .url(url)
//            if (token.isNotEmpty()){
//                request.addHeader("Authorization", token)
//            }
            val buildRequest = request.build()

            if (BuildConfig.DEBUG){
                Log.e(javaClass.simpleName, buildRequest.method + " " + buildRequest.url)
                Log.e(javaClass.name, "" + buildRequest.header("Cookie"))
                val buffer = Buffer()
                buildRequest.body?.writeTo(buffer)
                Log.e(javaClass.simpleName, "Payload- " + buffer.readUtf8())
            }

            return@Interceptor  chain.proceed(buildRequest)
        }

        val httpLoggingInterceptor = HttpLoggingInterceptor()
        val loggingInterceptor =
            httpLoggingInterceptor.apply {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            }


        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(requestInterceptor)
            .addInterceptor(loggingInterceptor)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiServices::class.java)
    }
}