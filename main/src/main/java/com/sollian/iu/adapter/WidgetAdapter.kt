package com.sollian.iu.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.sollian.buz.bean.Article
import com.sollian.iu.R

/**
 * @author sollian on 2017/10/10.
 */
class WidgetAdapter(
        val context: Context
) : RecyclerView.Adapter<WidgetAdapter.WidgetHolder>() {
    val data = ArrayList<Article>()

    fun setData(articles: Array<Article>?) {
        data.clear()
        if (articles != null)
            data.addAll(articles.asList())
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): WidgetHolder {
        val view = View.inflate(context, R.layout.item_widget, null)
        return WidgetHolder(view)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: WidgetHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun getItem(position: Int) = data[position]

    class WidgetHolder(view: View) : RecyclerView.ViewHolder(view) {

    }
}