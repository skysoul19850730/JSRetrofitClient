package com.skysoul.retrofitlibrary.interceptor.headers

import android.util.Log
import com.skysoul.retrofitlibrary.interceptor.JSInterceptorHandler
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit

class JSInterceptorHeaderHandler(var baseHeadersCreator: JSBaseHeaderCreator) :
    JSInterceptorHandler {


//    override fun intercept(chain: Interceptor.Chain): Response {
//        var request = chain.request()
//        var builder = request.newBuilder()
//        var maps = baseHeadersCreator.createBaseHeads()
//        var replaceable = baseHeadersCreator.isHeaderReplaceable()
//        maps.forEach {
//            if(!replaceable){//如果是不可替代的header，则去除已有的header
////                builder.removeHeader(it.key)
////                builder.addHeader(it.key,it.value)
//                builder.header(it.key,it.value)
//            }else{
//               if( request.header(it.key)==null) {
//                   //todo sqc:如果原请求已有相同key的header，并且baseHeadersCreator允许被替代，
//                   //则需要查看此处添加header是否影响原header，因为没找到是否原header是否存在某个key的api
//                   builder.addHeader(it.key, it.value)
//               }
//            }
//        }
//
//
//    }

    override fun dealWithChain(request: Request): Request.Builder {
        var builder = request.newBuilder()
        var maps = baseHeadersCreator.createBaseHeads()
        var replaceable = baseHeadersCreator.isHeaderReplaceable()
        Log.d("OkHttp","reheader: ${maps.toString()}")
        maps.forEach {
            if (!replaceable) {//如果是不可替代的header，则去除已有的header
//                builder.removeHeader(it.key)
//                builder.addHeader(it.key,it.value)
                builder.header(it.key, it.value)
            } else {
                if (request.header(it.key) == null) {
                    //todo sqc:如果原请求已有相同key的header，并且baseHeadersCreator允许被替代，
                    //则需要查看此处添加header是否影响原header，因为没找到是否原header是否存在某个key的api
                    builder.addHeader(it.key, it.value)
                }
            }
        }
        return builder
    }
}