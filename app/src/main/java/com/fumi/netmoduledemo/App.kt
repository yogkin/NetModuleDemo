package com.fumi.netmoduledemo

import android.app.Application
import com.fumi.net_module.ApiFactory
import com.fumi.netmoduledemo.net.HttpHandleCode
import com.fumi.netmoduledemo.net.NetError

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        ApiFactory.errorCode = NetError()
        ApiFactory.handleCode = HttpHandleCode.handleCode
    }


}