package com.skysoul.retrofitclientapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var gankService = RetrofitClient.INSTANCE
        tvtest.setOnClickListener {
            gankService.gankDaily().enqueue(object: Callback<GankDailyResult> {
                override fun onFailure(call: Call<GankDailyResult>, t: Throwable) {
                }

                override fun onResponse(
                    call: Call<GankDailyResult>,
                    response: Response<GankDailyResult>
                ) {
                }

            })
        }
    }
}
