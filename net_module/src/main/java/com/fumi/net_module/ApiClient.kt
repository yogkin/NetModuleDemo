package com.fumi.net_module

import com.fumi.net_module.bean.BaseHttpBean
import com.fumi.net_module.exception.APIErrorCode
import com.fumi.net_module.exception.IAPIErrorCode
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.IllegalArgumentException
import java.net.Proxy
import java.util.concurrent.TimeUnit


/**
 * @date 创建时间:2020/10/12
 * @author czm
 * @description ApiFactory
 */

inline fun <reified T> ApiFactory.create(
    baseUrl: String? = null,
    debug: Boolean = false,
    timeOut: Long = 60,
    interceptor: Interceptor? = null
): T = create(T::class.java, baseUrl, debug, timeOut, interceptor)


object ApiFactory {


    /**
     * 处理异常类，可以自定义并实现handle方法
     */
    var errorCode: IAPIErrorCode = APIErrorCode()

    /**
     * 默认200为成功
     */
    var handleCode: (Int, BaseHttpBean<*>) -> Any? =
        { httpCode: Int, httpBean: BaseHttpBean<*> ->
            if (httpCode == 200 || httpCode == 0) {
                httpBean.data
            } else {
                throw IllegalArgumentException("解析错误")
            }
        }


    fun <T> create(
        clazz: Class<T>,
        baseUrl: String? = null,
        debug: Boolean = false,
        timeOut: Long = 60,
        interceptor: Interceptor? = null
    ): T {

        val builder = OkHttpClient.Builder()
        if (debug) {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(httpLoggingInterceptor)
        } else {
            builder.proxy(Proxy.NO_PROXY)
        }
        interceptor?.also { builder.addInterceptor(it) }
        builder.connectTimeout(timeOut, TimeUnit.SECONDS)
            .readTimeout(timeOut, TimeUnit.SECONDS)
            .writeTimeout(timeOut, TimeUnit.SECONDS)

        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(baseUrl ?: "")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(builder.build())
        return retrofitBuilder.build().create(clazz)
    }
}