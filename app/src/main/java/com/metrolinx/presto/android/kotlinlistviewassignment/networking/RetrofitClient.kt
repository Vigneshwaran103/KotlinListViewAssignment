package com.accenture.codingassignment.networking

import android.content.Context
import com.metrolinx.presto.android.kotlinlistviewassignment.AppConstant
import okhttp3.Interceptor
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import okhttp3.OkHttpClient
import okhttp3.Response
import java.io.IOException
import java.net.UnknownHostException

object RetrofitClient {
    private var retrofit: Retrofit? = null

    fun getRetrofitClient(mContext: Context): Retrofit? {
        if (retrofit == null) {

            val oktHttpClient = OkHttpClient.Builder()
                .addInterceptor(object : Interceptor {
                    @Throws(IOException::class,UnknownHostException::class)
                    override fun intercept(chain: Interceptor.Chain): Response {
                        val builder = chain.request().newBuilder()
                        return chain.proceed(builder.build())
                    }})

            retrofit = Retrofit.Builder()
                .baseUrl(AppConstant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(oktHttpClient.build())
                .build()
        }
        return retrofit
    }
}