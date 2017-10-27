package com.sollian.iu.presenter

import android.support.v7.widget.RecyclerView
import com.sollian.buz.bean.Refer
import com.sollian.buz.bean.User
import com.sollian.buz.controller.ReferController
import com.sollian.iu.R
import com.sollian.iu.activity.MainActivity
import com.sollian.iu.adapter.ReferAdapter
import org.jetbrains.anko.toast

/**
 * @author sollian on 2017/10/10.
 */
abstract class ReferPresenter(page: MainActivity) : AbsMainPresenter(page) {
    private val referController = ReferController()
    private val refers = arrayListOf<Refer>()
    //    private var refer: Refer? = null
    private var curPage = DEFAULT_PAGE
    private var totalPage = DEFAULT_PAGE
    private val referType = {
        when (getType()) {
            TYPE_AT -> Refer.AT
            TYPE_REPLY -> Refer.REPLY
            else -> {
                throw IllegalArgumentException("错误的提醒类型")
            }
        }
    }()
    private val adapter = ReferAdapter(page, referType)

    init {
        refers.addAll(mockRefers())
        adapter.setData(refers)
        adapter.notifyDataSetChanged()
        onRefresh()
    }

    override fun getAdapter(): RecyclerView.Adapter<*>? = adapter

    override fun onRefresh() {
        curPage = DEFAULT_PAGE
        referController.asyncGet(referType, curPage) {
            if (!it.success()) {
                getContext()?.toast(it.desc!!)
            } else {
                totalPage = it.obj!!.pagination.page_all_count
                refers.clear()
                refers.addAll(it.obj!!.article)
            }
            getContext()?.onNotifyDataChanged(this)
        }
    }

    override fun onNextPage() {
        referController.asyncGet(referType, curPage + 1) {
            if (!it.success()) {
                getContext()?.toast(it.desc!!)
            } else {
                curPage = it.obj!!.pagination.page_current_count
                totalPage = it.obj!!.pagination.page_all_count
                refers.removeAll(it.obj!!.article)
                refers.addAll(it.obj!!.article)
            }
            getContext()?.onNotifyDataChanged(this)
        }
    }

    override fun hasNextPage() = curPage < totalPage

    override fun getTitle(): String? = when (getType()) {
        TYPE_REPLY -> getContext()!!.getString(R.string.reply_me)
        TYPE_AT -> getContext()!!.getString(R.string.at_me)
        else -> {
            null
        }
    }

    private fun mockRefers(): List<Refer> {
        val refers = arrayListOf<Refer>()

        val boardNames = listOf("谈天说地", "海天游踪", "跳蚤市场", "经济学", "Java技术",
                "智能车", "软件办公", "机器人", "WWW技术", "创新实践")

        for (i in 0..9) {
            val refer = Refer()
            refer.board_name = boardNames[i]
            refer.title = "第" + i + "条提醒"
            refer.setIs_read(i > 4)

            val user = User()
            user.user_name = "我是" + i
            user.face_url = "https://static.byr.cn/uploadFace/B/buerlc.3968.jpg"
            refer.user = user

            refers.add(refer)
        }
        return refers
    }
}