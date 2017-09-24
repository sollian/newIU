package com.sollian.buz.controller

import android.text.TextUtils
import com.sollian.buz.response.ArticleResponse
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 * @author sollian on 2017/9/22.
 */
class ArticleController : AbsController() {
    companion object {
        private val API_ARTICLE = API_HEAD + "/article/"
    }

    fun asyncGet(board: String, id: Int,
                 consumer: ((response: ArticleResponse) -> Unit)?) {
        getObservable("$API_ARTICLE$board/$id$FORMAT?$APP_KEY")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { response ->
                    consumer?.invoke(ArticleResponse(getJson(response)))
                }
    }

    /**
     * 发布新文章/主题
     *
     * @param board
     * @param title 新文章的标题
     * @param content 新文章的内容，可以为空
     * @param reid 新文章回复其他文章的id
     */
    fun asyncSend(board: String, title: String, content: String, reid: String?,
                  consumer: ((response: ArticleResponse) -> Unit)?) {
        val params = hashMapOf<String, String>(
                "title" to title,
                "content" to content
        )
        if (!TextUtils.isEmpty(reid)) {
            params.put("reid", reid.toString())
        }

        postObservable("$API_ARTICLE$board/post$FORMAT?$APP_KEY", params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { response ->
                    consumer?.invoke(ArticleResponse(getJson(response)))
                }
    }

    /**
     * 转载文章
     *
     * @param board 文章所在版面
     * @param id 文章ID
     * @param target 要转载的版面
     */
    fun asyncCross(board: String, id: Int, target: String,
                   consumer: ((response: ArticleResponse) -> Unit)?) {
        val params = mapOf<String, String>(
                "target" to target
        )

        postObservable("$API_ARTICLE$board/cross/$id$FORMAT?$APP_KEY", params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { response ->
                    consumer?.invoke(ArticleResponse(getJson(response)))
                }
    }

    /**
     * 转寄文章
     *
     * @param board 文章所在版面
     * @param id 文章ID
     * @param target 收件人ID
     */
    fun asyncForward(board: String, id: Int, target: String,
                     consumer: ((response: ArticleResponse) -> Unit)?) {
        val params = mapOf<String, String>(
                "target" to target
        )

        postObservable("$API_ARTICLE$board/forward/$id$FORMAT?$APP_KEY", params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { response ->
                    consumer?.invoke(ArticleResponse(getJson(response)))
                }
    }

    /**
     * 更新指定文章/主题
     *
     * @param board 合法的版面名称
     * @param id 文章或主题id
     * @param title 修改后的文章标题
     * @param content 修改后的文章内容
     */
    fun asyncUpdate(board: String, id: Int, title: String, content: String,
                    consumer: ((response: ArticleResponse) -> Unit)?) {
        val params = mapOf<String, String>(
                "title" to title,
                "content" to content
        )

        postObservable("$API_ARTICLE$board/update/$id$FORMAT?$APP_KEY", params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { response ->
                    consumer?.invoke(ArticleResponse(getJson(response)))
                }
    }

    /**
     * 删除指定文章
     *
     * @param board 合法的版面名称
     * @param id 文章或主题id
     */
    fun asyncDelete(board: String, id: Int,
                    consumer: ((response: ArticleResponse) -> Unit)?) {
        postObservable("$API_ARTICLE$board/delete/$id$FORMAT?$APP_KEY", null)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { response ->
                    consumer?.invoke(ArticleResponse(getJson(response)))
                }
    }
}