package com.sollian.buz.controller

import com.sollian.buz.bean.Article
import com.sollian.buz.dao.ArticleDB
import com.sollian.buz.response.ArticleResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

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
                .subscribe {
                    consumer?.invoke(ArticleResponse(getJson(it)))
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
        if (!reid.isNullOrEmpty()) {
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
                .map {
                    val articleResponse = ArticleResponse(getJson(it))
                    if (articleResponse.success()) {
                        val article = articleResponse.obj!!
                        val art = ArticleDB.queryById(article.id)
                        if (art != null)
                            safeInsertOrUpdate(article)
                    }
                    articleResponse
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    consumer?.invoke(it)
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
                .observeOn(Schedulers.io())
                .map {
                    val articleResponse = ArticleResponse(getJson(it))
                    if (articleResponse.success()) {
                        ArticleDB.delete(articleResponse.obj!!)
                    }
                    articleResponse
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    consumer?.invoke(it)
                }
    }

    fun queryByIds(ids: List<Int>) = ArticleDB.queryByIds(ids)

    fun markRead(id: Int) {
        val article = ArticleDB.queryById(id)
        if (article != null) {
            article.setReaded(true)
            ArticleDB.update(article)
        }
    }

    fun markCollected(id: Int, isCollected: Boolean) {
        val article = ArticleDB.queryById(id)
        if (article != null) {
            article.setCollected(isCollected)
            ArticleDB.update(article)
        }
    }

    fun clearCollected(ids: List<Int>) {
        val articles = ArticleDB.queryByIds(ids)
        articles.forEach {
            it.setCollected(false)
        }
        if (articles.isNotEmpty())
            ArticleDB.update(articles)
    }

    fun syncQueryCollected(page: Int, limit: Int): List<Article> {
        val p = if (page < 1) 1 else page
        return ArticleDB.queryCollected(p, limit)
    }

    /**
     * 不覆盖@Local属性
     */
    fun safeInsertOrUpdate(article: Article) {
        val art = ArticleDB.queryById(article.id)
        if (art == null) {
            if (article.didValid()) ArticleDB.insertOrReplace(article)
        } else {
            if (article == art) {
            } else if (!article.didValid()) {
                ArticleDB.delete(art)
            } else {
                article.copyLocalProp(art)
                article.userId = article.user.id
                ArticleDB.insertOrReplace(article)
            }
        }
    }

    /**
     * 不覆盖@Local属性
     */
    fun safeInsertOrUpdate(vararg article: Article) {
        val insertList = arrayListOf<Article>()
        val updateList = arrayListOf<Article>()
        article.forEach {
            val art = ArticleDB.queryById(it.id)
            if (art == null) insertList.add(it)
            else if (art == it) {
            } else if (!it.didValid()) ArticleDB.delete(art)
            else {
                it.copyLocalProp(art)
                it.userId = it.user.id
                updateList.add(it)
            }
        }
        if (insertList.isNotEmpty()) ArticleDB.insertOrReplace(insertList)
        if (updateList.isNotEmpty()) ArticleDB.update(updateList)
    }
}

