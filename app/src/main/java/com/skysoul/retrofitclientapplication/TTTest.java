package com.skysoul.retrofitclientapplication;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface TTTest {
    @Headers({
            "Content-Type: application/javascript",
            "User-Agent: YourAgent"
    })
    @GET("/data/{user_id}")
    Call<List<ResponseBody>> getData();
}
