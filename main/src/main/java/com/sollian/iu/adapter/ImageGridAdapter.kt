package com.sollian.iu.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.BaseAdapter
import android.widget.ImageView
import com.sollian.base.kotlinext.dp2px
import com.sollian.iu.utils.GlideUtil
import java.lang.ref.WeakReference

/**
 * @author sollian on 2017/10/12.
 */
class ImageGridAdapter(
        private val context: Context,
        var imgSize: Int
) : BaseAdapter() {
    private val data = ArrayList<String>()
    private val padding = 2.dp2px()
    private val refViews = arrayListOf<WeakReference<View>>()

    fun setData(data: List<String>?) {
        this.data.clear()
        if (data != null)
            this.data.addAll(data)
    }

    fun clearLastTasks() {
        refViews.forEach {
            val view = it.get()
            if (view != null) {
                GlideUtil.clear(context, view)
            }
        }
        refViews.clear()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var vImage = convertView as? ImageView
        if (vImage == null) {
            vImage = ImageView(context)
            vImage.setPadding(padding, padding, padding, padding)
            vImage.scaleType = ImageView.ScaleType.CENTER_CROP
        }

        if (imgSize == 0) {
            imgSize = AbsListView.LayoutParams.WRAP_CONTENT
        }
        val params = AbsListView.LayoutParams(imgSize, imgSize)
        vImage.layoutParams = params

        GlideUtil.loadWithAnim(context, getItem(position), vImage)
        refViews.add(WeakReference(vImage))

        return vImage
    }

    override fun getItem(position: Int) = data[position]

    override fun getItemId(position: Int) = position.toLong()

    override fun getCount() = data.size
}