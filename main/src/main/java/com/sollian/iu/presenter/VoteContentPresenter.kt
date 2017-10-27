package com.sollian.iu.presenter

import android.os.Bundle
import android.os.Handler
import com.sollian.base.Utils.BaseContext
import com.sollian.base.view.BasePresenter
import com.sollian.buz.bean.User
import com.sollian.buz.bean.Vote
import com.sollian.buz.controller.ArticleController
import com.sollian.buz.controller.VoteController
import com.sollian.iu.activity.VoteContentActivity
import org.jetbrains.anko.toast
import java.util.regex.Pattern

/**
 * @author sollian on 2017/10/25.
 */
class VoteContentPresenter(page: VoteContentActivity) : BasePresenter<VoteContentActivity>(page) {
    companion object {
        private val INVALID_ID = -1
    }

    private var vote: Vote? = null
    private val voteController by lazy {
        VoteController()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val vid = getContext()!!.intent.getIntExtra(VoteContentActivity.KEY_VOTE_ID, INVALID_ID)
        if (vid == INVALID_ID) {
            getContext()!!.finish()
            return
        }

        Handler().post {
            getContext()?.updateView(mockVote())
        }

//        voteController.asyncGet(vid) {
//            if (it.success()) {
//                vote = it.obj
//                getContext()?.updateView(vote!!)
//                getArticle()
//            } else {
//                getContext()?.toast(it.desc!!)
//                getContext()?.finish()
//            }
//        }
    }

    private fun getArticle() {
        if (getContext() == null) return

        ArticleController().asyncGet("nVote", vote!!.vid) {
            if (it.success()) {
                val p = Pattern.compile("\n描述:([^\n]*)")
                val m = p.matcher(it.obj!!.content)
                if (m.find()) {
                    val mr = m.toMatchResult()
                    getContext()?.updateDesc(mr.group(1))
                }
            }
        }
    }

    fun sendVote() {
        progressDialog.show()
        voteController.asyncVote(vote!!) {
            progressDialog.dismiss()
            if (it.success()) {
                vote = it.obj
                getContext()?.updateView(vote!!)
            } else {
                BaseContext.context.toast(it.desc!!)
            }
        }
    }

    private fun mockVote(): Vote {
        val vote = Vote()
        vote.setIs_end(false)
        vote.title = "第444个投票"
        vote.type = 0
        vote.voted = null
        vote.limit = 3
        vote.vote_count = 55

        val user = User()
        user.user_name = "我是44"
        user.face_url = "https://static.byr.cn/uploadFace/B/buerlc.3968.jpg"
        vote.user = user


        val options = arrayListOf<Vote.Option>()
        for (i in 0..9) {
            val option = Vote.Option()
            option.label = "第" + i + "个选项"
            option.viid = i
            option.num = i + 1
            options.add(option)
        }

        vote.options = options.toTypedArray()

        return vote
    }
}