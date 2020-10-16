package com.fumi.netmoduledemo.net

import com.fumi.net_module.bean.BaseHttpBean

object HttpHandleCode {
    val errorCode = { httpCode: Int, bean: BaseHttpBean<*> ->
        when (httpCode) {
            0, 200 -> bean.data
//                -4 -> throw AccountPermissionException()
//                -1 -> throw TokanFailException()
//                -8 -> throw PaidExpiredException(bean.code, bean.msg)
//                else -> throw APIErrorCodeException(bean.code, bean.msg)
            else -> throw IllegalArgumentException("服务器异常")
        }
    }
}