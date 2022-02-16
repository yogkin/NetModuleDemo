package com.fumi.netmoduledemo.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.fumi.netmoduledemo.App
import com.fumi.netmoduledemo.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_mvc.*

/**
 * @date 创建时间:2020/10/16
 * @author czm
 * @description mvc使用
 */
class MVCActivity : SimpleActivity() {

    companion object {
        fun open(context: Context) {
            context.startActivity(Intent(context, MVCActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvc)
        btn_click.setOnClickListener {
            myService.getRoomLiveList(mapOf()).execute {
                toast(it.toString())
            }
        }
    }
}