package com.sollian.iu.presenter

import android.support.v7.widget.RecyclerView
import com.sollian.base.Utils.IUUtil
import com.sollian.buz.bean.Article
import com.sollian.buz.controller.ArticleController
import com.sollian.iu.R
import com.sollian.iu.activity.MainActivity
import com.sollian.iu.adapter.CollectAdapter

/**
 * @author sollian on 2017/10/10.
 */
class CollectPresenter(page: MainActivity) : AbsMainPresenter(page) {
    private val itemCountPerPage = 20
    private var hasNextPage = true
    private val articles = arrayListOf<Article>()
    private val articleController = ArticleController()
    private var curPage = 1
    private val adapter = CollectAdapter(page)

    init {
        val list = articleController.syncQueryCollected(curPage++, itemCountPerPage)
        hasNextPage = list.size >= itemCountPerPage
        articles.addAll(list)
        adapter.setData(articles)
        IUUtil.post {
            page.onNotifyDataChanged(this)
        }
    }

    override fun getAdapter(): RecyclerView.Adapter<*>? = adapter

    override fun canRefresh() = false

    override fun onNextPage() {
        val list = articleController.syncQueryCollected(curPage++, itemCountPerPage)
        hasNextPage = list.size >= itemCountPerPage
        articles.addAll(list)
        adapter.setData(articles)
        page.onNotifyDataChanged(this)
    }

    override fun hasNextPage() = hasNextPage

    override fun getTitle(): String = page.getString(R.string.collect)

    override fun getType() = TYPE_COLLECT
}