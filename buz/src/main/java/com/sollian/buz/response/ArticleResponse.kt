package com.sollian.buz.response

import com.sollian.base.Utils.JsonUtil
import com.sollian.buz.bean.Article

/**
 * @author sollian on 2017/9/22.
 */
class ArticleResponse(json: String?) : AbsResponse(json) {
    var article: Article? = null

    init {
        article = JsonUtil.parse(json, Article::class.java)
        checkError(article)
    }
}