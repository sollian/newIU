package com.sollian.buz.controller

import com.sollian.buz.bean.Vote
import com.sollian.buz.bean.VoteList
import com.sollian.buz.response.VoteListResponse
import com.sollian.buz.response.VoteResponse
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 * @author solli on 2017/9/24.
 */
class VoteController : AbsController() {
    companion object {
        val API_VOTE = API_HEAD + "/vote/"
    }

    /**
     * 获取投票信息
     *
     * @param vid 投票vid
     */
    fun asyncGet(vid: Int, consumer: ((response: VoteResponse) -> Unit)?) {
        getObservable(API_VOTE + vid + FORMAT + '?' + APP_KEY)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { response ->
                    consumer?.invoke(VoteResponse(getJson(response)))
                }
    }

    /**
     * 投票操作
     *
     */
    fun asyncVote(vote: Vote, consumer: ((response: VoteResponse) -> Unit)?) {
        val params = hashMapOf<String, Any>()
        if (vote.type == Vote.SINGLE_VOTE) {
            vote.options.forEach {
                if (it.isChecked) {
                    params.put("vote", it.viid.toString())
                    return@forEach
                }
            }
        } else {
            val ids = mutableListOf<String>()
            vote.options.forEach {
                if (it.isChecked) {
                    ids.add(it.viid.toString())
                }
            }
            params.put("vote[]", ids)
        }

        postObservable(API_VOTE + vote.vid + FORMAT + '?' + APP_KEY, params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { response ->
                    consumer?.invoke(VoteResponse(getJson(response)))
                }
    }

    /**
     * 获取投票列表
     *
     * @param type
     * @param page
     */
    fun asyncGetList(@VoteList.VoteType type: String, page: Int,
                     consumer: ((response: VoteListResponse) -> Unit)?) {
        getObservable(API_VOTE + "category/" + type + FORMAT + '?' + APP_KEY + "&page=" + page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { response ->
                    consumer?.invoke(VoteListResponse(getJson(response)))
                }
    }
}