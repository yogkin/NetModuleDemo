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
 * @author AS
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
     * 主动取消订阅
     */
    fun dispose()

    fun <T> Flowable<BaseHttpBean<T>>.execute( successCode: Int = 0, function: (T) -> Unit): Any {
        val subscribe = handle(successCode).async().loading(this@INetView)
                .subscribe(
                        { function.invoke(it) },
                        { e -> ApiFactory.handleCode.handleCode(this@INetView, e) })
        return this@INetView.addDisposable(subscribe)
    }


    fun <T> Flowable<BaseHttpBean<T>>.executeNoLoading(function: (T) -> Unit): Any {
        return handle().async()
                .subscribe(
                        { function.invoke(it) },
                        { e -> ApiFactory.handleCode.handleCode(this@INetView, e) })
    }

}