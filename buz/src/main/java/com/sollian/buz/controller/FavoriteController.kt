package com.sollian.buz.controller

import com.sollian.buz.response.FavoriteResponse
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 * @author solli on 2017/9/24.
 */
class FavoriteController : AbsController() {
    companion object {
        val API_FAVORITE = API_HEAD + "/favorite/"
    }

    fun asyncGet(level: Int, consumer: ((response: FavoriteResponse) -> Unit)?) {
        getObservable(API_FAVORITE + level + FORMAT + '?' + APP_KEY)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { response ->
                    consumer?.invoke(FavoriteResponse(getJson(response)))
                }
    }

    /**
     * @param level
     * @param name
     * @param dir     是否为自定义目录：0不是，1是
     */
    fun asyncAdd(level: Int, name: String, dir: Int = 0,
                 consumer: ((response: FavoriteResponse) -> Unit)?) {
        val params = mapOf<String, String>(
                "name" to name,
                "dir" to dir.toString()
        )

        postObservable(API_FAVORITE + "add/" + level + FORMAT + '?' + APP_KEY, params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { response ->
                    consumer?.invoke(FavoriteResponse(getJson(response)))
                }
    }

    fun asyncDelete(level: Int, name: String, dir: Int,
                    consumer: ((response: FavoriteResponse) -> Unit)?) {
        val params = mapOf<String, String>(
                "name" to name,
                "dir" to dir.toString()
        )

        postObservable(API_FAVORITE + "delete/" + level + FORMAT + '?' + APP_KEY, params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { response ->
                    consumer?.invoke(FavoriteResponse(getJson(response)))
                }
    }
}