package com.sollian.buz.response

import com.sollian.buz.bean.Article

/**
 * @author sollian on 2017/9/22.
 */
class ArticleResponse(json: String?) : AbsResponse<Article>(json) {
    override fun getObjClass() = Article::class.java
}