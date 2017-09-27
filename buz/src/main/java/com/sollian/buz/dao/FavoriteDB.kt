package com.sollian.buz.dao

import com.sollian.buz.bean.Favorite

/**
 * @author sollian on 2017/9/25.
 */
object FavoriteDB : AbsDB() {
    override fun getSessionKey() = "favorite"

    fun query(): List<Favorite> {
        return db().queryBuilder()
                .list()
    }

    fun replace(favorite: Favorite) {
        db().deleteAll()
        db().insert(favorite)
    }

    private fun db() = session().favoriteDao!!
}