@file:JvmName("ApiUtils")

package com.fumi.net_module

import com.fumi.net_module.ApiFactory.handleCode
import com.fumi.net_module.ApiFactory.errorCode
import com.fumi.net_module.bean.BaseHttpBean
import com.fumi.net_module.view.INetView
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @date 创建时间:2020/10/12
 * @author AS
 * @description 拓展类
 */
fun <T> Flowable<out BaseHttpBean<T>>.handle(): Flowable<T> {
    return map {
        handleCode.invoke(it.code, it) as T
    }
}

fun <T> Flowable<out BaseHttpBean<T>>.asyncAndHandle(): Flowable<T> {
    return async().handle()
}

fun <T> Flowable<T>.loading(netView: INetView) =
    doOnSubscribe { netView.showLoading() }.doFinally { netView.dismissLoading() }

fun <T> Flowable<T>.async() =
    subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())


//下载相关
fun <T> Flowable<T>.download(netView: INetView, function: (T) -> Unit): Any {
    val subscribe = async().loading(netView)
        .subscribe(
            { function.invoke(it) },
            { e -> errorCode.handleCode(netView, e) })
    return netView.addDisposable(subscribe)
}
