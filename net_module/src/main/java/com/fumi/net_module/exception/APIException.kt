package com.fumi.net_module.exception

import java.lang.RuntimeException

internal class APIException(private val code: Int, private val msg: String) : RuntimeException(msg,Throwable(code.toString())) {
}