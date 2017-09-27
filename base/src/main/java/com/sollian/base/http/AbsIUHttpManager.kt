package com.sollian.base.http

import okhttp3.*
import java.util.concurrent.TimeUnit

/**
 * @author sollian on 2017/9/22.
 */
abstract class AbsIUHttpManager {
    var userName: String? = null
    var password: String? = null

    val client: Call.Factory by lazy {
        getHttpClient()
    }

    fun syncSend(request: Request): Response = client.newCall(request).execute()

    private fun getHttpClient(): Call.Factory {
        return OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .authenticator { _, response ->
                    val credential = Credentials.basic(userName!!, password!!)
                    response.request().newBuilder()
                            .header("Authorization", credential)
                            .build()
                }
                .build()
    }
}