package com.fumi.netmoduledemo.mvp

import android.app.Activity
import android.widget.Toast
import com.fumi.net_module.ApiFactory
import com.fumi.net_module.create
import com.fumi.net_module.view.INetView
import com.fumi.netmoduledemo.bean.MyService
import com.fumi.netmoduledemo.presenter.BBPresenter
import com.fumi.netmoduledemo.presenter.BBView
import com.fumi.netmoduledemo.presenter.BasePresenter
import com.fumi.netmoduledemo.view.NetAlertDialog
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


abstract class BaseActivity : Activity(), INetView {

    abstract val presenter: BasePresenter

    private val dialog by lazy { NetAlertDialog(this) }

    private val mCompositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun showLoading() {
        dialog.show()
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
}