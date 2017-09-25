package com.sollian.buz.dao

import com.sollian.buz.bean.Article
import com.sollian.buz.bean.ArticleDao

/**
 * @author sollian on 2017/9/25.
 */
object ArticleDB : AbsDB() {
    override fun getSessionKey() = "article"

    fun queryByIds(vararg ids: Int): List<Article> {
        return db().queryBuilder()
                .where(ArticleDao.Properties.Id.`in`(ids))
                .list()
    }

    fun insertOrUpdate(article: Iterable<Article>) {
        db().insertOrReplaceInTx(article)
    }

    fun db() = session().articleDao
}