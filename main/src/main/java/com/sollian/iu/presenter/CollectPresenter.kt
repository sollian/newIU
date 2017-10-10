package com.sollian.iu.presenter

import com.sollian.iu.activity.MainActivity

/**
 * @author sollian on 2017/10/10.
 */
class CollectPresenter(page: MainActivity) : BoardPresenter(page) {
    override fun getType() = TYPE_COLLECT
}