package com.sollian.buz.controller

import com.sollian.buz.response.UserResponse
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 * @author sollian on 2017/9/22.
 */
class UserController : AbsController() {
    companion object {
        private val API_USER_QUERY = API_HEAD + "/user/query/"
        private val API_USER_LOGIN = API_HEAD + "/user/login"
    }

    fun asyncLogin(consumer: ((response: UserResponse) -> Unit)?) {
        getObservable(API_USER_LOGIN + FORMAT + "?" + APP_KEY)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { response ->
                    consumer?.invoke(UserResponse(getJson(response)))
                }
    }

    fun asyncQuery(id: String, consumer: ((response: UserResponse) -> Unit)?) {
        getObservable(API_USER_QUERY + id + FORMAT + "?" + APP_KEY)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { response ->
                    consumer?.invoke(UserResponse(getJson(response)))
                }
    }
}