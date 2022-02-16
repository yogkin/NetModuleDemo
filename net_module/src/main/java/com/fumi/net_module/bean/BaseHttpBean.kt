package com.fumi.net_module.bean

/**
 * @date 创建时间:2020/10/12
 * @author czm
 * @description 网络基类，需要可以拓展，比如返回来的信息不是msg，而是mgs，通过继承改类，getMsg 返回mgs的值
 */
open class BaseHttpBean<T>(
        open val code: Int,
        open val data: T,
        open val msg: String = ""
)