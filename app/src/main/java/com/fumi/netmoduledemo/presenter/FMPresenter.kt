package com.fumi.netmoduledemo.presenter

import com.fumi.netmoduledemo.bean.ResultBean
import com.fumi.netmoduledemo.view.FMIView


interface BBView : FMIView {
    fun finishGetData(it: List<ResultBean>)
}

class BBPresenter(view: BBView) : BasePresenter(view) {
    fun getData() {
        myService.getRoomLiveList(mapOf()).execute {
            mView.finishGetData(it)
        }
    }

}