package com.sollian.buz.dao

import android.content.Context
import com.sollian.buz.bean.DaoMaster
import org.greenrobot.greendao.database.Database

/**
 * @author sollian on 2017/9/25.
 */
internal class IUDBHelper(context: Context, dbName: String) : DaoMaster.OpenHelper(context, dbName) {
    override fun onUpgrade(db: Database?, oldVersion: Int, newVersion: Int) {

    }
}