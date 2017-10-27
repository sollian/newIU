package com.sollian.iu.utils

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View
import com.sollian.base.kotlinext.dp2px

/**
 * @author sollian on 2017/10/12.
 */
class LinearItemDecoration() : RecyclerView.ItemDecoration() {
    private val space = 1.dp2px()
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.top = space
    }
}