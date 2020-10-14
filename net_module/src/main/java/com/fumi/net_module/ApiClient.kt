package com.fumi.net_module

import android.content.Context
import android.os.Debug
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
import java.util.*
import java.util.concurrent.TimeUnit


/**
 * @date 创建时间:2020/10/12
 * @author AS
 * @description ApiFactory
 */

inline fun <reified T> ApiFactory.create(baseUrl: String? = null, interceptor: Interceptor? = null): T = create(T::class.java, baseUrl, interceptor)


object ApiFactory {


    /**
     * 处理异常类，可以自定义并实现handle方法
     */
    var handleCode: IAPIErrorCode = APIErrorCode()

    /**
     * 是否为调试模式
     */
    var debug = false

    /**
     * 超时时间
     */
    private var timeOut = 60L

    /**
     * baseUrl
     */
    var baseUrl = ""

    /**
     * @date 创建时间:2020/10/10
     * @author AS
     * @description 需要先调用该方法
     */
    fun init(
            baseUrl: String,
            debug: Boolean = false,
            timeOut: Long = 60L
    ): ApiFactory {
        this.baseUrl = baseUrl
        this.debug = debug
        this.timeOut = timeOut
        return this
    }


    fun <T> create(clazz: Class<T>, baseUrl: String? = null, interceptor: Interceptor? = null): T {

        val builder = OkHttpClient.Builder()
        if (debug) {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(httpLoggingInterceptor)
            //线上包不走系统代理，防止抓包
            builder.proxy(null)
        } else {
            builder.proxy(Proxy.NO_PROXY)
        }
        interceptor?.also { builder.addInterceptor(it) }
        builder.connectTimeout(timeOut, TimeUnit.SECONDS)
                .readTimeout(timeOut, TimeUnit.SECONDS)
                .writeTimeout(timeOut, TimeUnit.SECONDS)

        val retrofitBuilder = Retrofit.Builder()
                .baseUrl(baseUrl ?: this.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(builder.build())
        return retrofitBuilder.build().create(clazz)
    }
}