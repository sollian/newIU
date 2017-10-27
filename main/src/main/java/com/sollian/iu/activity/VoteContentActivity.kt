package com.sollian.iu.activity

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.sollian.base.Utils.IUUtil
import com.sollian.base.view.BaseFragmentActivity
import com.sollian.buz.bean.Vote
import com.sollian.iu.R
import com.sollian.iu.adapter.VoteContentAdapter
import com.sollian.iu.presenter.VoteContentPresenter
import com.sollian.iu.utils.GlideUtil
import com.sollian.iu.utils.LinearItemDecoration
import com.sollian.iu.view.SmoothLinearLayoutManager
import kotlinx.android.synthetic.main.activity_vote.*
import org.jetbrains.anko.find

class VoteContentActivity : BaseFragmentActivity<VoteContentPresenter>() {
    companion object {
        val KEY_VOTE_ID = "vote_id";
    }

    val adapter = VoteContentAdapter(this)

    override fun initPresenter(): VoteContentPresenter? = VoteContentPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vote)

        initView()
    }

    private fun initView() {
        back.setOnClickListener {
            finish()
        }

        list.layoutManager = SmoothLinearLayoutManager(this)
        list.addItemDecoration(LinearItemDecoration())
        list.adapter = adapter
    }

    fun updateView(vote: Vote) {
        find<TextView>(R.id.title).text = vote.title

        GlideUtil.load(this, vote.user.face_url, head)
        name.text = vote.user.user_name
        time.text = IUUtil.formatTime(vote.start)

        adapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onChanged() {
                updateStatus(adapter.getData())
            }

            override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
                updateStatus(adapter.getData())
            }
        })
        adapter.setData(vote)
        adapter.notifyDataSetChanged()
    }

    fun updateDesc(desc: String) {
        subject.text = desc
    }

    private fun updateStatus(vote: Vote?) {
        if (vote == null) return

        if (vote.didVoted()) {
            status.text = getString(R.string.voted)
            status.visibility = View.VISIBLE
            status.setOnClickListener(null)
            multiCount.visibility = View.GONE
        } else if (vote.is_end) {
            status.text = getString(R.string.terminated)
            status.visibility = View.VISIBLE
            status.setOnClickListener(null)
            multiCount.visibility = View.GONE
        } else {
            status.visibility = if (canVote(vote)) View.VISIBLE else View.GONE
            status.text = getString(R.string.vote)
            status.setOnClickListener {
                presenter?.sendVote()
            }

            if (vote.didMultiVote()) {
                var count = 0
                vote.options.forEach {
                    if (it.isChecked) {
                        count++
                    }
                }
                multiCount.text = getString(R.string.already_chose, count, vote.limit)
                multiCount.visibility = View.VISIBLE
            } else {
                multiCount.visibility = View.GONE
            }
        }
    }

    private fun canVote(vote: Vote): Boolean {
        if (vote.canVote() && vote.options != null) {
            vote.options.forEach {
                if (it.isChecked) {
                    return true
                }
            }
        }
        return false
    }
}
