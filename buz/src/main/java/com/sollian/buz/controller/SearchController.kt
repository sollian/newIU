package com.sollian.buz.controller

import com.sollian.buz.response.SearchResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @author solli on 2017/9/24.
 */
class SearchController : AbsController() {
    companion object {
        val API_SEARCH = API_HEAD + "/search/threads" + FORMAT
    }

    /**
     * 搜索单个版面的主题
     *
     * @param board   单个合法版面
     * @param keywords     文章标题/作者包含此关键词
     * @param isTitle true：标题；false：作者
     * @param page    文章的页数
     */
    fun asyncGet(board: String, keywords: String, isTitle: Boolean, page: Int,
                 consumer: ((response: SearchResponse) -> Unit)?) {
        var url = "$API_SEARCH?$APP_KEY&board=$board&page=$page&day=365"
        url += if (isTitle) "&title1=" + keywords else "&author=" + keywords

        getObservable(url)
                .observeOn(Schedulers.io())
                .map {
                    val searchResponse = SearchResponse(getJson(it))
                    if (searchResponse.success()) {
                        ArticleController().safeInsertOrUpdate(*searchResponse.obj!!.threads)
                    }
                    searchResponse
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    consumer?.invoke(it)
                }
    }
}