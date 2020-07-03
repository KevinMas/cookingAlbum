package com.example.android.cookingalbum.repository

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ServiceFactory {

    fun<T> getService(service: Class<T>): T = retrofit.create(service)

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://cooking-records.herokuapp.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpBuilder.build())
        .build()

    /**
     *
     */
    private val httpBuilder: OkHttpClient.Builder
        get() {
            // log interceptor
            val loggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            // create http client
            return OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
        }
}
