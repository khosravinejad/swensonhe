package com.morteza.swensonhe.exception

class ServerException(val responseCode: Int, val serverMessage: String) : Throwable()