package com.skysoul.retrofitclientapplication

import com.google.gson.GsonBuilder
import com.skysoul.retrofitlibrary.interceptor.JSInterceptor
import com.skysoul.retrofitlibrary.interceptor.headers.JSBaseHeaderCreator
import com.skysoul.retrofitlibrary.interceptor.headers.JSInterceptorHeaderHandler
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*


object RetrofitClient : JSBaseHeaderCreator {

    private const val BASE_URL = "http://gank.io/api/"

    private const val DATE_PATTERN_1 = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS"
    private const val DATE_PATTERN_2 = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
    private const val DATE_PATTERN_3 = "yyyy-MM-dd'T'HH:mm:ss"

    val INSTANCE: GankService = create()
//    val INSTANCE2: GankService = create2()

    private fun create(): GankService {
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BODY

        val dateGson = GsonBuilder()
            .registerTypeAdapter(Date::class.java, GankDateDeserializer(DATE_PATTERN_1, DATE_PATTERN_2, DATE_PATTERN_3))
            .serializeNulls()
            .create()

        var jsInterceptor = JSInterceptor.Builder()
            .addInterceptorHandler(JSInterceptorHeaderHandler(this))
            .build()
        val client = OkHttpClient.Builder()
            .addInterceptor(jsInterceptor)
            .addInterceptor(logger)//logger拦截放在最后，如果先触发logger拦截打印的header等信息都是改变之前的，改变之后不会重新打印
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(dateGson))
            .build()
            .create(GankService::class.java)
    }
//    private fun create2(): GankService {
//        val logger = HttpLoggingInterceptor()
//        logger.level = HttpLoggingInterceptor.Level.BODY
//
//        val dateGson = GsonBuilder()
//            .registerTypeAdapter(Date::class.java, GankDateDeserializer(DATE_PATTERN_1, DATE_PATTERN_2, DATE_PATTERN_3))
//            .serializeNulls()
//            .create()
//
//        val client = OkHttpClient.Builder()
//            .addInterceptor(logger)
//            .addInterceptor(JSHeaderInterceptor(null))
//            .build()
//        return Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .client(client)
//            .addConverterFactory(GsonConverterFactory.create(dateGson))
//            .build()
//            .create(GankService::class.java)
//    }

    override fun createBaseHeads(): Map<String, String> {
        var map = HashMap<String,String>()
        map.put("headertest","head1")
        return map
    }

    override fun isHeaderReplaceable(): Boolean {
       return false
    }
}