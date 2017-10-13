package com.sollian.iu.presenter

import android.support.v7.widget.RecyclerView
import com.sollian.iu.activity.MainActivity

/**
 * @author sollian on 2017/10/10.
 */
class VotePresenter(page: MainActivity) : AbsMainPresenter(page) {
    override fun getType() = TYPE_VOTE

    override fun getAdapter(): RecyclerView.Adapter<*>? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onRefresh() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onNextPage() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hasNextPage(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getTitle(): String? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}