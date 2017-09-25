package com.sollian.base.http

import android.text.TextUtils
import okhttp3.Credentials
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.util.concurrent.TimeUnit

/**
 * @author sollian on 2017/9/22.
 */
abstract class AbsIUHttpManager {
    var client: OkHttpClient? = null
    var userName: String? = null
    var password: String? = null

    fun syncSend(request: Request, largeRes: Boolean = false): Response? {
        val httpClient = if (largeRes) getHttpClient() else getSmallResClient()
        return httpClient?.newCall(request)?.execute()
    }

    private fun getSmallResClient(): OkHttpClient? {
        if (client == null) {
            client = getHttpClient();
        }
        return client
    }

    private fun getHttpClient(): OkHttpClient? {
        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(password)) {
            return null
        }
        return OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .authenticator { route, response ->
                    val credential = Credentials.basic(userName, password)
                    response.request().newBuilder()
                            .header("Authorization", credential)
                            .build()
                }
                .build()
    }
}