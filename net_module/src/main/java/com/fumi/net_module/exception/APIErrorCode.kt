package com.fumi.net_module.exception

import com.fumi.net_module.view.INetView
/**
 * @date 创建时间:2020/10/12
 * @author czm
 * @description 继承该类处理相关错误Code逻辑
 */
open class APIErrorCode : IAPIErrorCode {
    override fun handleCode(
        netView: INetView,
        throwable: Throwable
    ) {
        throwable.printStackTrace()
        when (throwable is APIException) {
            else -> netView.toast(throwable.message ?: "")
        }
    }
}