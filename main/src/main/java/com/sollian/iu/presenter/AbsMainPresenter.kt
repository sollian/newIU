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
    val user: User by lazy {
        UserController().syncQuery(SharePrefs.name)!!
    }

    abstract fun getAdapter(): RecyclerView.Adapter<*>
    abstract fun onRefresh()
    abstract fun onNextPage()
    open fun hasNextPage() = false
}