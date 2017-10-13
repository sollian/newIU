package com.sollian.iu.presenter

import android.content.res.ColorStateList
import android.support.v7.widget.RecyclerView
import android.view.MenuItem
import com.sollian.base.Utils.IUUtil
import com.sollian.buz.bean.Article
import com.sollian.buz.bean.Board
import com.sollian.buz.bean.Pagination
import com.sollian.buz.bean.User
import com.sollian.buz.controller.BoardController
import com.sollian.iu.R
import com.sollian.iu.activity.MainActivity
import com.sollian.iu.adapter.BoardAdapter
import org.jetbrains.anko.toast

/**
 * @author sollian on 2017/10/10.
 */
open class BoardPresenter(page: MainActivity) : AbsMainPresenter(page) {
    private val boardController = BoardController()
    private val adapter = BoardAdapter(page)
    private var board: Board? = null
    private var curPage = DEFAULT_PAGE
    private var totalPage = DEFAULT_PAGE
    private val articles = arrayListOf<Article>()

    init {
        board = mockBoard()
        adapter.setData(board!!.article)
        adapter.notifyDataSetChanged()
        onRefresh()
    }

    fun setBoard(name: String) {
        board = boardController.syncGet(name)
        onRefresh()
    }

    override fun getType() = TYPE_BOARD

    override fun getAdapter(): RecyclerView.Adapter<*>? = adapter

    override fun onRefresh() {
        curPage = DEFAULT_PAGE
        boardController.asyncGet(board!!.name, curPage) {
            if (!it.success()) {
                page.toast(it.desc!!)
            } else {
                totalPage = it.obj!!.pagination.page_all_count
                articles.addAll(it.obj!!.article)
                articles.clear()
            }
            page.onNotifyDataChanged(this)
        }
    }

    override fun onNextPage() {
        boardController.asyncGet(board!!.name, curPage + 1) {
            if (!it.success()) {
                page.toast(it.desc!!)
            } else {
                totalPage = it.obj!!.pagination.page_all_count
                curPage = it.obj!!.pagination.page_current_count
                articles.removeAll(it.obj!!.article)
                articles.addAll(it.obj!!.article)
            }
            page.onNotifyDataChanged(this)
        }
    }

    override fun hasNextPage() = curPage < totalPage

    override fun getThemeColor() = IUUtil.str2Color(board!!.name)

    override fun getTitle(): String = board!!.description

    override fun getMenuResId() = R.menu.menu_board
    override fun getMenuItemIconTintList(): ColorStateList {
        val colors = intArrayOf(getThemeColor(),
                page.resources.getColor(R.color.widget_normal))
        val statePress = intArrayOf(android.R.attr.state_pressed)
        val stateNormal = intArrayOf()
        val states = arrayOf(statePress, stateNormal)
        return ColorStateList(states, colors)
    }

    override fun onMenuClick(item: MenuItem) {
        super.onMenuClick(item)
        when (item.itemId) {
            R.id.menu_search_by_author -> {
            }
            R.id.menu_search_by_keyword -> {
            }
            R.id.menu_write -> {
            }
            else -> {
            }
        }
    }

    private fun mockBoard(): Board {
        val board = Board()
        board.name = "经济学"
        board.description = "经济学"

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
            article.board_name = board.name
            article.flag = flags[i % flags.size]
            article.setIs_top(i == 0)
            article.isHas_attachment = i % 2 == 0
            article.reply_count = i * 10
            article.title = "第" + i + "个帖子"
            article.setReaded(i % 3 == 0)
            article.setCollected(i % 2 == 0)

            user = User()
            user.user_name = "我是" + i
            user.face_url = "https://static.byr.cn/uploadFace/B/buerlc.3968.jpg"
            article.user = user
            article.photos = photos[i % photos.size]

            articles.add(article)
        }
        board.article = articles.toTypedArray()

        val pagination = Pagination()
        pagination.page_all_count = 1
        pagination.page_current_count = 1

        board.pagination = pagination
        return board
    }
}