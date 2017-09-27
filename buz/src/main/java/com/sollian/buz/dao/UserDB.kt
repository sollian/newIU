package com.sollian.buz.dao

import com.sollian.buz.bean.User
import com.sollian.buz.bean.UserDao

/**
 * @author sollian on 2017/9/25.
 */
object UserDB : AbsDB() {
    override fun getSessionKey() = "user"

    fun queryById(id: String): User? {
        return db().queryBuilder()
                .where(UserDao.Properties.Id.eq(id))
                .unique()
    }

    fun insertOrReplace(user: User) {
        db().insertOrReplace(user)
    }

    private fun db() = session().userDao!!
}