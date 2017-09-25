package com.sollian.buz.dao

import com.sollian.buz.bean.Widget

/**
 * @author sollian on 2017/9/25.
 */
object WidgetDB : AbsDB() {
    override fun getSessionKey() = "widget"

    fun insertOrUpdate(widget: Widget) {
        db().deleteAll()
        db().insert(widget)
    }

    fun query(): List<Widget> {
        return db().queryBuilder()
                .list()
    }

    fun db() = session().widgetDao
}