package com.sollian.iu.presenter

import android.support.v7.widget.RecyclerView
import com.sollian.buz.bean.Widget
import com.sollian.buz.controller.WidgetController
import com.sollian.iu.activity.MainActivity
import com.sollian.iu.adapter.WidgetAdapter
import org.jetbrains.anko.toast

/**
 * @author sollian on 2017/9/29.
 */
class WidgetPresenter(page: MainActivity) : AbsMainPresenter(page) {
    private var widget: Widget? = null
    private var adapter = WidgetAdapter(page)
    private val widgetController = WidgetController()

    init {
        widget = widgetController.syncGet()
        adapter.setData(widget?.article)
        onRefresh()
    }

    override fun getType() = TYPE_WIDGET

    override fun getAdapter(): RecyclerView.Adapter<*>? = adapter

    override fun onRefresh() {
        widgetController.asyncGet {
            if (!it.success()) {
                page.toast(it.desc!!)
                page.onNotifyDataChanged(this)
                return@asyncGet
            }
            widget = it.obj
            adapter.setData(widget?.article)
            page.onNotifyDataChanged(this)
        }
    }

    override fun hasNextPage() = false

    override fun onNextPage() {
    }

    override fun getTitle() = widget?.title

    override fun getMenuAdapter(): RecyclerView.Adapter<*>? = null
}