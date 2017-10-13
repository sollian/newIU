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
open class ReplyPresenter(page: MainActivity) : ReferPresenter(page) {
    override fun getType() = TYPE_REPLY
}