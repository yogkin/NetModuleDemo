package com.fumi.netmoduledemo.presenter

import android.widget.Toast
import com.fumi.net_module.ApiFactory
import com.fumi.net_module.create
import com.fumi.netmoduledemo.bean.MyService
import com.fumi.netmoduledemo.view.FMIView
import com.fumi.netmoduledemo.view.NetAlertDialog
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BasePresenter(protected val mView: BBView) : FMIView {


    protected val myService = ApiFactory.create<MyService>()

    override fun showLoading() {
        mView.showLoading()
    }

    override fun dismissLoading() {
        mView.dismissLoading()
    }

    override fun toast(content: String) {
        mView.toast(content)
    }

    override fun addDisposable(subscription: Disposable) {
        mView.addDisposable(subscription)
    }

    override fun dispose() {
        mView.dispose()
    }

}