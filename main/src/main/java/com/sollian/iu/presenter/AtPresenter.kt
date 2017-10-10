package com.sollian.iu.presenter

import com.sollian.iu.activity.MainActivity

/**
 * @author sollian on 2017/10/10.
 */
class AtPresenter(page: MainActivity) : ReplyPresenter(page) {
    override fun getType() = TYPE_AT
}