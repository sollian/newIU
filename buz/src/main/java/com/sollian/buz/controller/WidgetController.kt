package com.sollian.buz.controller

import com.sollian.buz.bean.Widget
import com.sollian.buz.dao.WidgetDB
import com.sollian.buz.response.WidgetResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @author sollian on 2017/9/25.
 */
class WidgetController : AbsController() {
    companion object {
        val API_WIDGET_TOPTEN = API_HEAD + "/widget/topten" + FORMAT
    }

    fun asyncGet(consumer: ((response: WidgetResponse) -> Unit)?) {
        getObservable(API_WIDGET_TOPTEN + '?' + APP_KEY)
                .observeOn(Schedulers.io())
                .map {
                    val widgetResponse = WidgetResponse(getJson(it))
                    if (widgetResponse.success())
                        saveWidget2DB(widgetResponse.obj!!)
                    widgetResponse
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    consumer?.invoke(it)
                }
    }

    fun syncGet(): Widget? {
        val widgets = WidgetDB.query();
        return if (widgets.isNotEmpty()) {
            val widget = widgets[0]
            val articleIds = widget.articleIds.split(Widget.SPLITTER);
            val articles = ArticleController().queryByIds(*kotlin.IntArray(articleIds.size) {
                articleIds[it].toInt()
            })
            if (articleIds.size == articles.size) widget else null
        } else null
    }

    private fun saveWidget2DB(widget: Widget) {
        widget.articleIds = {
            var ids = ""
            widget.article.forEach {
                ids += it.id.toString() + Widget.SPLITTER
            }
            ids.dropLast(1)
            ids
        }.invoke()
        ArticleController().safeInsertOrUpdate(*widget.article)
        WidgetDB.replace(widget)
    }
}