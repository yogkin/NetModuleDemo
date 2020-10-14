package com.fumi.netmoduledemo.bean

import com.fumi.net_module.bean.BaseHttpBean
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.QueryMap


interface MyService {
    /**
     * 获取直播间列表
     */

    @GET("/wxarticle/chapters/json")
    fun getRoomLiveList(@QueryMap map: Map<String, String>): Flowable<BaseHttpBean<List<ResultBean>>>

}