package com.sollian.buz.sharepref

import com.sollian.base.Utils.BaseContext
import com.sollian.base.kotlinext.DelegatesExt

/**
 * @author sollian on 2017/9/25.
 */
object SharePrefs {
    private val USER_NAME = "user_name"
    private val PASSWORD = "password"

    private val COLUMN_WIDTH = "column_width"

    private val SECTION_LAST_SYNC_TIME = "section_last_sync_time"

    var name: String by DelegatesExt.preference(BaseContext.context, USER_NAME, "")
    var password: String by DelegatesExt.preference(BaseContext.context, PASSWORD, "")

    var columnWidth: Int by DelegatesExt.preference(BaseContext.context, COLUMN_WIDTH, 0)

    var sectionLastSyncTime: Long by DelegatesExt.preference(BaseContext.context, SECTION_LAST_SYNC_TIME, 0)
}