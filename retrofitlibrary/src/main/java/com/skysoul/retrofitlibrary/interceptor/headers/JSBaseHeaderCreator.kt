package com.skysoul.retrofitlibrary.interceptor.headers

interface JSBaseHeaderCreator {
    fun createBaseHeads():Map<String,String>
    fun isHeaderReplaceable():Boolean
}