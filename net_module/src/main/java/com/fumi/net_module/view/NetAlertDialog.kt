package com.fumi.net_module.view

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import com.fumi.net_module.R

/**
 * @date 创建时间:2020/10/20
 * @author AS
 * @description 提供一个简单的loading类
 */
class NetAlertDialog : AlertDialog {
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


}
