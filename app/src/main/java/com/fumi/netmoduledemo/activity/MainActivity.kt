package com.fumi.netmoduledemo.activity

import android.os.Bundle
import android.util.Log
import com.fumi.netmoduledemo.R
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @date 创建时间:2020/10/14
 * @author czm
 * @description kotlin使用
 */
class MainActivity : SimpleActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_mvc.setOnClickListener {
            MVCActivity.open(this)
        }
        btn_mvp.setOnClickListener {
            MVPActivity.open(this)
        }
    }

}
