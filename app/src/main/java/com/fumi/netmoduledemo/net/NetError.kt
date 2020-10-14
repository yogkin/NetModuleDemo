package com.fumi.netmoduledemo.net

import com.fumi.net_module.exception.APIErrorCode
import com.fumi.net_module.view.INetView


class NetError : APIErrorCode() {
    override fun handleCode(netView: INetView, throwable: Throwable) {
        super.handleCode(netView, throwable)
        netView.toast("显示了，哈哈哈")
    }
}