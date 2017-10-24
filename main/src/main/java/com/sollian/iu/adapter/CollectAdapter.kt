package com.sollian.iu.adapter

import android.content.Context
import android.view.View
import com.sollian.buz.bean.Article
import com.sollian.iu.R
import com.sollian.iu.presenter.CollectPresenter
import org.jetbrains.anko.toast

/**
 * @author sollian on 2017/10/13.
 */
class CollectAdapter(context: Context, private val collectPresenter: CollectPresenter) : WidgetAdapter(context) {
    override fun onBindViewHolder(holder: BoardHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.readed.visibility = View.GONE
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.collect -> {
                val article = v.tag as Article
                val position = data.indexOf(article)
                article.setCollected(!article.isCollected())
                articleController.markCollected(article.id, article.isCollected())
                collectPresenter.removeArticle(article)
                data.remove(article)
                notifyItemRemoved(position)
                context.toast(if (article.isCollected()) R.string.collected else R.string.cancel_collect)
            }
            else -> {
                super.onClick(v)
            }
        }
    }
}