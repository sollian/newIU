package com.sollian.iu.presenter

import android.support.v7.widget.RecyclerView
import android.view.MenuItem
import com.sollian.buz.bean.Mailbox
import com.sollian.buz.bean.User
import com.sollian.buz.bean.Vote
import com.sollian.buz.bean.VoteList
import com.sollian.buz.controller.VoteController
import com.sollian.iu.R
import com.sollian.iu.activity.MainActivity
import com.sollian.iu.adapter.VoteAdapter
import org.jetbrains.anko.toast

/**
 * @author sollian on 2017/10/10.
 */
class VotePresenter(page: MainActivity) : AbsMainPresenter(page) {
    private val adapter = VoteAdapter(page)
    private var curPage = DEFAULT_PAGE
    private var totalPage = DEFAULT_PAGE
    private val votes = arrayListOf<Vote>()
    private val voteController = VoteController()
    private var type = VoteList.ALL

    init {
        votes.addAll(mockVote())
        adapter.setData(votes)
        onRefresh()
    }

    override fun getType() = TYPE_VOTE

    override fun getAdapter(): RecyclerView.Adapter<*>? = adapter

    override fun onRefresh() {
        curPage = DEFAULT_PAGE
        voteController.asyncGetList(type, curPage) {
            if (!it.success()) {
                page.toast(it.desc!!)
            } else {
                totalPage = it.obj!!.pagination.page_all_count
                votes.clear()
                votes.addAll(it.obj!!.votes)
            }
            page.onNotifyDataChanged(this)
        }
    }

    override fun onNextPage() {
        voteController.asyncGetList(Mailbox.INBOX, curPage + 1) {
            if (!it.success()) {
                page.toast(it.desc!!)
            } else {
                totalPage = it.obj!!.pagination.page_all_count
                curPage = it.obj!!.pagination.page_current_count
                votes.removeAll(it.obj!!.votes)
                votes.addAll(it.obj!!.votes)
            }
            page.onNotifyDataChanged(this)
        }
    }

    override fun hasNextPage() = curPage < totalPage

    override fun getTitle(): String? = page.getString(R.string.vote)

    override fun getMenuResId() = R.menu.menu_vote
    override fun onMenuClick(item: MenuItem) {
        super.onMenuClick(item)
        when (item.itemId) {
            R.id.menu_vote_all -> {
                type = VoteList.ALL
            }
            R.id.menu_vote_hot -> {
                type = VoteList.HOT
            }
            R.id.menu_vote_new -> {
                type = VoteList.NEW
            }
            R.id.menu_vote_my -> {
                type = VoteList.ME
            }
            R.id.menu_vote_join -> {
                type = VoteList.JOIN
            }
            else -> {

            }
        }
        onRefresh()
    }

    private fun mockVote(): List<Vote> {
        val votes = arrayListOf<Vote>()

        for (i in 0..9) {
            val vote = Vote()
            vote.setIs_end(i > 5)
            vote.title = "第" + i + "个投票"
            vote.type = i and 1
            vote.voted = if (i % 3 == 0) Vote.Voted() else null
            vote.vote_count = i shl 3

            val user = User()
            user.user_name = "我是" + i
            user.face_url = "https://static.byr.cn/uploadFace/B/buerlc.3968.jpg"
            vote.user = user
            votes.add(vote)
        }

        return votes
    }
}