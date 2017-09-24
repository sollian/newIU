package com.sollian.buz.controller

import com.sollian.buz.response.MailResponse
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 * @author solli on 2017/9/24.
 */
class SearController : AbsController() {
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
                 consumer: ((response: MailResponse) -> Unit)?) {
        var url = "$API_SEARCH?$APP_KEY&board=$board&page=$page&day=365"
        url += if (isTitle) "&title1=" + keywords else "&author=" + keywords

        getObservable(url)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { response ->
                    consumer?.invoke(MailResponse(getJson(response)))
                }
    }
}