package com.sollian.iu.presenter

import android.support.v7.widget.RecyclerView
import com.sollian.base.view.BasePresenter
import com.sollian.buz.bean.User
import com.sollian.buz.controller.UserController
import com.sollian.buz.sharepref.SharePrefs
import com.sollian.iu.activity.MainActivity

/**
 * @author sollian on 2017/9/29.
 */
abstract class AbsMainPresenter(page: MainActivity) : BasePresenter<MainActivity>(page) {
    companion object {
        val TYPE_WIDGET = 0
        val TYPE_BOARD = 1
        val TYPE_COLLECT = 2
        val TYPE_REPLY = 3
        val TYPE_AT = 4
        val TYPE_MAIL = 5
        val TYPE_VOTE = 6
    }

    val user: User by lazy {
        UserController().syncQuery(SharePrefs.name)!!
    }

    abstract fun getType(): Int

    abstract fun getAdapter(): RecyclerView.Adapter<*>?
    abstract fun onRefresh()
    abstract fun onNextPage()
    abstract fun hasNextPage(): Boolean

    abstract fun getTitle(): String?
    abstract fun getMenuAdapter(): RecyclerView.Adapter<*>?

    fun onClickMore() {
        //TODO:实现版面树
    }
}