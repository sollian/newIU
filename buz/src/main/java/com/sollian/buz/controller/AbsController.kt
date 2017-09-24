package com.sollian.buz.controller

import com.sollian.buz.http.IUHttpManager
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.schedulers.Schedulers
import okhttp3.*
import java.io.File

/**
 * @author sollian on 2017/9/22.
 */
abstract class AbsController {
    companion object {
        val APP_KEY = "appkey=365c7cb3aeb92163"
        val API_HEAD = "http://api.byr.cn"
        val BBS_URL = "http://bbs.byr.cn"
        val FORMAT = ".json"
        val GUEST = "guest"
    }

    fun getObservable(url: String): Observable<Response> {
        val request = Request.Builder()
                .url(url)
                .get()
                .build()
        return genObservable(request)
    }

    fun postObservable(url: String, params: Map<String, String>?): Observable<Response> {
        val formBody = FormBody.Builder()
        if (params != null && !params.isEmpty()) {
            for ((key, value) in params) {
                formBody.add(key, value)
            }
        }

        val request = Request.Builder()
                .url(url)
                .post(formBody.build())
                .build()

        return genObservable(request)
    }

    fun postFileObservable(url: String, file: File): Observable<Response> {
        val body = MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", file.name, RequestBody.create(null, file))
                .build()
        val request = Request.Builder()
                .url(url)
                .post(body)
                .build()
        return genObservable(request)
    }

    private fun genObservable(request: Request) =
            Observable.create(ObservableOnSubscribe<Response> { e ->
                e.onNext(IUHttpManager.instance.syncSend(request))
            })
                    .subscribeOn(Schedulers.io())

    fun getJson(response: Response?): String? {
        var json = response?.body()?.string()
        if (json == null) {
            json = response?.message()
        }
        return json
    }
}