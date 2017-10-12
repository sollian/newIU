package com.sollian.iu.presenter

import android.support.v7.widget.RecyclerView
import com.sollian.buz.bean.Article
import com.sollian.buz.bean.User
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
        widget = mockWidget()
//        widget = widgetController.syncGet()
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

    private fun mockWidget(): Widget {
        val widget = Widget()
        widget.title = "十大"
        widget.name = "十大"

        val boardNames = listOf("谈天说地", "海天游踪", "跳蚤市场", "经济学", "Java技术",
                "智能车", "软件办公", "机器人", "WWW技术", "创新实践")
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

            user = User()
            user.user_name = "我是" + i
            user.face_url = "https://static.byr.cn/uploadFace/B/buerlc.3968.jpg"
            article.user = user
            article.photos = photos[i % photos.size]

            articles.add(article)
        }
        widget.article = articles.toTypedArray()
        return widget
    }
}