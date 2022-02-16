package com.fumi.net_module.exception

import com.fumi.net_module.view.INetView

/**
 * @date 创建时间:2020/10/12
 * @author czm
 * @description
 */
interface IAPIErrorCode {

    /**
     * @date 创建时间:2020/10/12
     * @author czm
     * @description 处理错误
     */
    fun handleCode(netView: INetView, throwable: Throwable)
}