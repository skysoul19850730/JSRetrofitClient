package com.skysoul.retrofitlibrary.interceptor

import okhttp3.Interceptor
import okhttp3.Response

/**
 * 最初设计是生产不同的interceptor。因为okhttp可以添加多个interceptor。每个interceptor执行完自己会进行process操作，会触发
 * 下一个interceptor。但是调用时需要使用api进行多个interceptor的add操作，调用者代码会看起来更长
 * 所以考虑并尝试只写一个interceptor，但它接收多个interceptorHandler处理者，每个处理者只负责重新build，但不执行process的思路
 * 底层构建各个handler的代码都被封装了，调用者也只需要给interceptor设置需要哪些handler，之后调用okhttp时，只需要添加一个interceptor
 */
class JSInterceptor :Interceptor {
     private constructor()
    var listHandlers:ArrayList<JSInterceptorHandler> = arrayListOf()
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        for(interceptor in listHandlers){
           request = interceptor.dealWithChain(request).build()
        }
        return chain.proceed(request)
    }

    class Builder{
        var listHandlers:ArrayList<JSInterceptorHandler> = arrayListOf()

        fun addInterceptorHandler(handler: JSInterceptorHandler):Builder{
            listHandlers.add(handler)
            return this
        }

        fun build():JSInterceptor{
            var interceptor = JSInterceptor()
            interceptor.listHandlers.addAll(listHandlers)
            return  interceptor
        }
    }
}