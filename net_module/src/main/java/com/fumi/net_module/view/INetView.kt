package com.fumi.net_module.view

import com.fumi.net_module.ApiFactory
import com.fumi.net_module.async
import com.fumi.net_module.bean.BaseHttpBean
import com.fumi.net_module.handle
import com.fumi.net_module.loading
import io.reactivex.Flowable
import io.reactivex.disposables.Disposable

/**
 * @date 创建时间:2020/10/12
 * @author czm
 * @description
 */
interface INetView {
    fun showLoading()
    fun dismissLoading()
    fun toast(content: String)

    /**
     * 需要在关键位置取消订阅，防止内存泄漏
     */
    fun addDisposable(subscription: Disposable)

    /**
     * 取消订阅
     */
    fun dispose()


    fun <T> Flowable<out BaseHttpBean<T>>.execute(function: (T) -> Unit) {
        val subscribe = handle().async().loading(this@INetView)
            .subscribe(
                { function.invoke(it) },
                { e -> ApiFactory.errorCode.handleCode(this@INetView, e) })
        this@INetView.addDisposable(subscribe)
    }


    fun <T> Flowable<T>.executeNoHandle(function: (T) -> Unit) {
        val subscribe = async().loading(this@INetView)
            .subscribe(
                { function.invoke(it) },
                { e -> ApiFactory.errorCode.handleCode(this@INetView, e) })
        this@INetView.addDisposable(subscribe)
    }


    fun <T> Flowable<out BaseHttpBean<T>>.executeNoLoading(function: (T) -> Unit) {
        val subscribe = handle().async()
            .subscribe(
                { function.invoke(it) },
                { e -> ApiFactory.errorCode.handleCode(this@INetView, e) })
        this@INetView.addDisposable(subscribe)
    }

    fun <T> Flowable<out BaseHttpBean<T>>.execute(function: (T) -> Unit, errorHandler: () -> Unit) {
        val subscribe = handle().async()
            .subscribe(
                { function.invoke(it) },
                { e -> ApiFactory.errorCode.handleCode(this@INetView, e);errorHandler.invoke() })
        this@INetView.addDisposable(subscribe)
    }

    fun <T> Flowable<out BaseHttpBean<T>>.executeNoLoading(
        function: (T) -> Unit,
        errorHandler: () -> Unit
    ) {
        val subscribe = handle().async()
            .subscribe(
                { function.invoke(it) },
                { e -> ApiFactory.errorCode.handleCode(this@INetView, e);errorHandler.invoke() })
        this@INetView.addDisposable(subscribe)
    }

}