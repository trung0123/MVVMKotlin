package com.example.askbekotlin.data.api

import com.example.askbekotlin.BuildConfig
import com.example.askbekotlin.utils.Preference
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    val service by lazy { getService(AppService::class.java, AppService.BASE_URL) }

    private const val REQUEST_TIMEOUT = 60
    private var token by Preference(Preference.USER_TOKEN, "")

    private val client: OkHttpClient
        get() {
//            val builder = OkHttpClient.Builder()
//            val logging = HttpLoggingInterceptor()
//            if (BuildConfig.DEBUG) {
//                logging.level = HttpLoggingInterceptor.Level.BODY
//            } else {
//                logging.level = HttpLoggingInterceptor.Level.BASIC
//            }
//
//            builder.addInterceptor(logging)
//                .connectTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)
//
//            return builder.build()

            val httpClient = OkHttpClient().newBuilder()
                .connectTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .readTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .writeTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)

            val interceptor = HttpLoggingInterceptor()
            interceptor.level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

            httpClient.addInterceptor(interceptor)

            httpClient.addInterceptor { chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                    .addHeader("Jil-Public-Key", "com.learningift.askbe.debug")
                    .addHeader("Jil-Token", token)
                    .addHeader("version", "vtest")
                    .addHeader("App-Version", "1.0")
                    .addHeader("Connection", "close")

                val request = requestBuilder
                    .method(original.method, original.body)
                    .build()
                chain.proceed(request)
            }
            return httpClient.build()
        }

    private fun <S> getService(serviceClass: Class<S>, baseUrl: String): S {
        return Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build().create(serviceClass)
    }
}