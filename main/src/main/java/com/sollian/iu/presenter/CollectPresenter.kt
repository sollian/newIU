package com.sollian.iu.presenter

import android.support.v7.widget.RecyclerView
import com.sollian.base.Utils.IUUtil
import com.sollian.buz.bean.Article
import com.sollian.buz.bean.User
import com.sollian.buz.controller.ArticleController
import com.sollian.iu.R
import com.sollian.iu.activity.MainActivity
import com.sollian.iu.adapter.CollectAdapter
import java.util.*

/**
 * @author sollian on 2017/10/10.
 */
class CollectPresenter(page: MainActivity) : AbsMainPresenter(page) {
    private val itemCountPerPage = 20
    private var hasNextPage = true
    private val articles = arrayListOf<Article>()
    private val articleController = ArticleController()
    private var curPage = 1
    private val adapter = CollectAdapter(page, this)

    init {
        val list = articleController.syncQueryCollected(curPage++, itemCountPerPage)
        hasNextPage = list.size >= itemCountPerPage
        articles.addAll(list)

        articles.addAll(mockArticles())

        adapter.setData(articles)
        IUUtil.post {
            page.onNotifyDataChanged(this)
        }
    }

    fun removeArticle(article: Article) {
        articles.remove(article)
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

    private fun mockArticles(): List<Article> {

        val boardNames = listOf("谈天说地", "海天游踪", "跳蚤市场", "经济学", "Java技术",
                "智能车", "软件办公", "机器人", "WWW技术", "创新实践")
        Collections.shuffle(boardNames)
        val flags = listOf("m", "g", "b", "")
        val photo0 = ""
        val photo1 = "https://bbs.byr.cn/att/Travel/0/136806/1795"
        val photo2 = "https://bbs.byr.cn/att/Travel/0/136806/1795;https://bbs.byr.cn/att/Travel/0/136806/577675"
        val photo3 = "https://bbs.byr.cn/att/Travel/0/136806/1795;https://bbs.byr.cn/att/Travel/0/136806/577675;https://bbs.byr.cn/att/Travel/0/136806/1073799"
        val photos = listOf(photo0, photo1, photo2, photo3)

        var article: Article
        var user: User
        val articles = arrayListOf<Article>()
        for (i in 0..9) {
            article = Article()
            article.board_name = boardNames[i]
            article.flag = flags[i % flags.size]
            article.setIs_top(i == 0)
            article.isHas_attachment = i % 2 == 0
            article.reply_count = i * 10
            article.title = "第" + i + "个帖子"
            article.setReaded(true)
            article.setCollected(true)

            user = User()
            user.user_name = "我是" + i
            user.face_url = "https://static.byr.cn/uploadFace/B/buerlc.3968.jpg"
            article.user = user
            article.photos = photos[i % photos.size]

            articles.add(article)
        }
        return articles
    }
}