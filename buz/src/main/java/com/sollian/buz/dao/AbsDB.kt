package com.sollian.buz.dao

/**
 * @author sollian on 2017/9/25.
 */
abstract class AbsDB {
    fun session() = IUDaoManager.instance.getDbSession(getSessionKey())

    fun clearSession() {
        session().clear()
    }

    internal abstract fun getSessionKey(): String
}