package com.sollian.iu.utils

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View


/**
 * @author sollian on 2017/10/12.
 */
class MarginItemDecoration(private val space: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
//        val itemCount = mAdapter.getItemCount()
//        val pos = parent.getChildAdapterPosition(view)

        outRect.left = space
        outRect.top = space
        outRect.bottom = space
        outRect.right = space


//        if (pos != itemCount - 1) {
//            outRect.right = space
//        } else {
//            outRect.right = 0
//        }
    }
}