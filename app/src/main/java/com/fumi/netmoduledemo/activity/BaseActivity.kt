package com.fumi.netmoduledemo.activity

import android.app.Activity
import android.widget.Toast
import com.fumi.net_module.ApiFactory
import com.fumi.net_module.create
import com.fumi.net_module.view.INetView
import com.fumi.netmoduledemo.view.NetAlertDialog
import com.fumi.netmoduledemo.bean.MyService
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseActivity : Activity(), INetView {

    private val dialog by lazy { NetAlertDialog(this) }

    private val mCompositeDisposable: CompositeDisposable = CompositeDisposable()

    protected val myService = ApiFactory.create<MyService>()

    override fun showLoading() {
        dialog.showLoading()
    }

    override fun dismissLoading() {
        dialog.dismiss()
    }

    override fun toast(content: String) {
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show()
    }

    override fun addDisposable(subscription: Disposable) {
        mCompositeDisposable.add(subscription)
    }

    override fun dispose() {
        mCompositeDisposable.dispose()
    }

    fun Disposable.addDispose() {
        mCompositeDisposable.add(this)
    }

    fun Disposable.addLifecycle() {
        mCompositeDisposable.add(this)
    }

    override fun onDestroy() {
        mCompositeDisposable.dispose()
        super.onDestroy()
    }
}