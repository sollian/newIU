package com.sollian.buz.controller

import com.sollian.buz.response.UserResponse
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 * @author solli on 2017/9/23.
 */
class BoardController : AbsController() {
    companion object {
        val API_SECTION = API_HEAD + "/section/"
        val API_BOARD = API_HEAD + "/board/"
    }

    fun asyncGet(name: String, page: Int,
                      consumer: ((response: UserResponse) -> Unit)?) {
        getObservable("$API_BOARD$name$FORMAT?page=$page&$APP_KEY")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { response ->
                    consumer?.invoke(UserResponse(getJson(response)))
                }
    }
}