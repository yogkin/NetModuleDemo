package com.fumi.netmoduledemo.activity

import android.os.Bundle
import android.util.Log
import com.fumi.netmoduledemo.R
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @date 创建时间:2020/10/14
 * @author AS
 * @description kotlin使用
 */
class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_click.setOnClickListener {
            myService.getRoomLiveList(mapOf()).execute {
                Log.d("MainActivity", it.toString())
            }
        }
    }

}
