package com.fumi.netmoduledemo.view

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import com.fumi.net_module.view.INetView
import com.fumi.netmoduledemo.R
import io.reactivex.disposables.Disposable

class NetAlertDialog : AlertDialog, INetView {
    constructor(context: Context) : super(context)
    constructor(context: Context, themeResId: Int) : super(context, themeResId)
    constructor(
        context: Context,
        cancelable: Boolean,
        cancelListener: DialogInterface.OnCancelListener?
    ) : super(context, cancelable, cancelListener)

    init {
        val view: View = LayoutInflater.from(context).inflate(R.layout.dialog_loading_base, null)
        setView(view)
        setCancelable(false)
    }

    override fun showLoading() {
        show()
    }

    override fun dismissLoading() {
        dismiss()
    }

    override fun toast(content: String) {

    }


    override fun addDisposable(subscription: Disposable) {

    }

}
