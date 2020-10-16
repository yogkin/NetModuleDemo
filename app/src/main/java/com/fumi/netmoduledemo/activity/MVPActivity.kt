package com.fumi.netmoduledemo.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.fumi.netmoduledemo.R
import com.fumi.netmoduledemo.bean.ResultBean
import com.fumi.netmoduledemo.mvp.BaseActivity
import com.fumi.netmoduledemo.presenter.BBPresenter
import com.fumi.netmoduledemo.presenter.BBView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_mvp.*

/**
 * @date 创建时间:2020/10/16
 * @author AS
 * @description mvp使用
 */
class MVPActivity : BaseActivity(), BBView {

    companion object {
        fun open(context: Context) {
            context.startActivity(Intent(context, MVCActivity::class.java))
        }
    }

    override val presenter = BBPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvp)
        btn_click.setOnClickListener {
            presenter.getData()
        }

    }

    override fun finishGetData(it: List<ResultBean>) {
        toast(it.toString())
    }

}