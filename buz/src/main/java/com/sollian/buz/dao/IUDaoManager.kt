package com.sollian.buz.dao

import com.sollian.base.Utils.BaseContext
import com.sollian.buz.bean.DaoMaster
import com.sollian.buz.bean.DaoSession

/**
 * @author sollian on 2017/9/25.
 */
internal class IUDaoManager {
    companion object {
        private val dbName = "iudb"

        val instance: IUDaoManager by lazy {
            IUDaoManager()
        }
    }

    private val dbHelper: DaoMaster.OpenHelper
    private val dbMaster: DaoMaster
    private val dbSessions = hashMapOf<String, DaoSession>()

    init {
        dbHelper = IUDBHelper(BaseContext.context, dbName)
        dbMaster = DaoMaster(dbHelper.writableDb)
    }

    fun getDbSession(key: String): DaoSession {
        return if (dbSessions.containsKey(key)) {
            dbSessions[key]!!
        } else {
            val session = dbMaster.newSession()
            dbSessions[key] = session
            session
        }
    }
}