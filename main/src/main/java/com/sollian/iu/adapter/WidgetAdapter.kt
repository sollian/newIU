package com.sollian.iu.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import co.revely.gradient.RevelyGradient
import com.sollian.base.Utils.IUUtil
import com.sollian.iu.R
import org.jetbrains.anko.find

/**
 * @author sollian on 2017/10/10.
 */
open class WidgetAdapter(
        context: Context
) : BoardAdapter(context) {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): WidgetHolder {
        val view = View.inflate(context, R.layout.item_article, null)
        return WidgetHolder(view)
    }

    override fun onBindViewHolder(holder: BoardHolder, position: Int) {
        super.onBindViewHolder(holder, position)

        val article = getItem(position)
        val widgetHolder = holder as WidgetHolder

        widgetHolder.boardName.text = article.board_name
        val boardColor = IUUtil.str2Color(article.board_name)
        RevelyGradient.linear()
                .colors(intArrayOf(boardColor, 0))
                .onBackgroundOf(widgetHolder.boardName)
    }

    class WidgetHolder(view: View) : BoardHolder(view) {
        val boardName = view.find<TextView>(R.id.boardName)

        init {
            boardName.visibility = View.VISIBLE
        }
    }
}