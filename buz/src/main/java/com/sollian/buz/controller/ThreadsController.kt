package com.sollian.buz.controller

import com.sollian.buz.response.ThreadsResponse
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 * @author solli on 2017/9/24.
 */
class ThreadsController : AbsController() {
    companion object {
        val API_THREADS = API_HEAD + "/threads/"
    }

    /**
     * 获取指定主题的信息
     *
     * @param board 合法的版面名称
     * @param id    文章或主题id
     * @param page  主题文章的页数
     * @param author    只显示该主题中某一用户的文章，au为该用户的用户名，大小写敏感
     */
    fun asyncGet(board: String, id: Int, page: Int = 1, author: String? = null,
                 consumer: ((response: ThreadsResponse) -> Unit)?) {
        var url = "$API_THREADS$board/$id$FORMAT?$APP_KEY&page=$page"
        if (author != null) {
            url += "&au=" + author
        }

        getObservable(url)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { response ->
                    consumer?.invoke(ThreadsResponse(getJson(response)))
                }
    }
}