package com.sollian.iu.view

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

/**
 * @author solli on 2017/9/30.
 */
class SmoothLinearLayoutManager(context: Context) : LinearLayoutManager(context) {
    override fun getExtraLayoutSpace(state: RecyclerView.State) = 200
}