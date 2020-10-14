package com.fumi.netmoduledemo.activity

import android.os.Bundle
import android.util.Log
import com.fumi.net_module.execute
import com.fumi.netmoduledemo.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_click.setOnClickListener {
            myService.getRoomLiveList(mapOf()).execute(this) {
                Log.d("MainActivity", it.toString())
            }
        }
    }

}
