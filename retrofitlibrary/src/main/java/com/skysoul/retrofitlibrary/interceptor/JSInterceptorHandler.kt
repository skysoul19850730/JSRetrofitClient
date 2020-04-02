package com.skysoul.retrofitlibrary.interceptor

import okhttp3.Request

interface JSInterceptorHandler {
    fun dealWithChain(request: Request):Request.Builder
}