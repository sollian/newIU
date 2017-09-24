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

    /**
     * 获取指定版面的信息
     *
     * @param name 合法的版面名称
     * @param page 文章的页数
     */
    fun asyncGet(name: String, page: Int,
                 consumer: ((response: UserResponse) -> Unit)?) {
        getObservable("$API_BOARD$name$FORMAT?page=$page&$APP_KEY")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { response ->
                    consumer?.invoke(UserResponse(getJson(response)))
                }
    }
}