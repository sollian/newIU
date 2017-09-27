package com.sollian.buz.controller

import com.sollian.buz.dao.UserDB
import com.sollian.buz.response.UserResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.Response

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
                .observeOn(Schedulers.io())
                .map({
                    saveUser2DB(it)
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    consumer?.invoke(it)
                }
    }

    fun asyncQuery(id: String, consumer: ((response: UserResponse) -> Unit)?) {
        getObservable(API_USER_QUERY + id + FORMAT + "?" + APP_KEY)
                .observeOn(Schedulers.io())
                .map {
                    saveUser2DB(it)
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    consumer?.invoke(it)
                }
    }

    fun syncQuery(id: String) = UserDB.queryById(id)

    private fun saveUser2DB(response: Response?): UserResponse {
        val userResponse = UserResponse(getJson(response))
        if (userResponse.success()) {
            UserDB.insertOrReplace(userResponse.obj!!)
        }
        return userResponse
    }
}