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

    fun asyncGetArticle(board: String, id: Int,
                        consumer: ((response: ArticleResponse) -> Unit)?) {
        getObservable("$API_ARTICLE$board/$id$FORMAT?$APP_KEY")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { response ->
                    consumer?.invoke(ArticleResponse(getJson(response)))
                }
    }

    fun asyncSendArticle(board: String, title: String, content: String, reid: String?,
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

    fun asyncCrossArticle(board: String, id: Int, target: String,
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

    fun asyncForwardArticle(board: String, id: Int, target: String,
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

    fun asyncUpdateArticle(board: String, id: Int, title: String, content: String,
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

    fun asyncDeleteArticle(board: String, id: Int,
                           consumer: ((response: ArticleResponse) -> Unit)?) {
        postObservable("$API_ARTICLE$board/delete/$id$FORMAT?$APP_KEY", null)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { response ->
                    consumer?.invoke(ArticleResponse(getJson(response)))
                }
    }
}