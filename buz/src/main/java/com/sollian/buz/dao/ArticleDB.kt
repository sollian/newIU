package com.sollian.buz.dao

import com.sollian.buz.bean.Article
import com.sollian.buz.bean.ArticleDao

/**
 * @author sollian on 2017/9/25.
 */
object ArticleDB : AbsDB() {
    override fun getSessionKey() = "article"

    fun queryById(id: Int): Article? {
        return db().queryBuilder()
                .where(ArticleDao.Properties.Id.eq(id))
                .unique()
    }

    fun queryByIds(vararg ids: Int): List<Article> {
        return db().queryBuilder()
                .where(ArticleDao.Properties.Id.`in`(ids))
                .list()
    }

    /**
     * page从1开始
     */
    fun queryCollected(page: Int, limit: Int): List<Article> {
        return db().queryBuilder()
                .where(ArticleDao.Properties.IsCollected.eq(true))
                .limit(limit)
                .offset((page - 1) * limit)
                .list()
    }

    fun insertOrReplace(article: Article) {
        db().insertOrReplace(article)
    }

    fun insertOrReplace(article: Iterable<Article>) {
        db().insertOrReplaceInTx(article)
    }

    fun update(article: Article) {
//        var art: Article? = article
//        if (article.gId == null) {
//            art = queryById(article.id)
//        }
//        if (art == null) insertOrReplace(article) else db().update(art)

        db().update(article)
    }

    fun update(article: Iterable<Article>) {
        db().updateInTx(article)
    }

    fun delete(article: Article) {
        var art: Article? = article
        if (article.gId == null) art = queryById(article.id)
        if (art != null) db().deleteByKey(art.gId)
    }

    fun deleteByGid(gid: Long) {
        db().deleteByKey(gid)
    }

    fun db() = session().articleDao!!
}