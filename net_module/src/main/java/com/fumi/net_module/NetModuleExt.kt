@file:JvmName("ApiUtils")

package com.fumi.net_module

import com.fumi.net_module.ApiFactory.handleCode
import com.fumi.net_module.bean.BaseHttpBean
import com.fumi.net_module.exception.APIException
import com.fumi.net_module.view.INetView
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @date 创建时间:2020/10/12
 * @author AS
 * @description 拓展类
 */
fun <T> Flowable<out BaseHttpBean<T>>.handle(successCode: Int = 0): Flowable<T> {
    return this.map {
        when (it.code) {
            successCode -> it.data ?: throw APIException(-2333, "data 不能为空")
            else -> throw APIException(it.code, it.msg)
        }
    }
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
                    { e -> handleCode.handleCode(netView, e) })
    return netView.addDisposable(subscribe)
}
