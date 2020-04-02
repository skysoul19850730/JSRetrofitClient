package com.skysoul.retrofitclientapplication

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface GankService {
//    @Headers({
//        "headertest : t1",
//        "headertest2 : t2"
//    })
    @GET("today")
    @Headers("headertest: t1","headertest2: t2")
    fun gankDaily(): Call<GankDailyResult>

    @GET("data/{filterType}/{count}/{page}")
    fun gankFilter(
        @Path("filterType") filterType: String,
        @Path("count") count: Int,
        @Path("page") page: Int
    ): Call<ResponseBody>

    @GET("search/query/{queryText}/category/all/count/{count}/page/{page}")
    fun search(
        @Path("queryText") queryText: String,
        @Path("count") count: Int,
        @Path("page") page: Int
    ): Call<ResponseBody>

    @Headers("Content-Type: application/javascript", "User-Agent: YourAgent")
    @GET("/data/{user_id}")
    fun getData(): Call<List<ResponseBody?>?>?

}

