package com.sollian.base.view

import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.support.annotation.DrawableRes
import android.support.annotation.RawRes
import android.widget.TextView
import pl.droidsonroids.gif.GifDrawable
import java.io.IOException
import java.lang.ref.WeakReference
import java.util.concurrent.ConcurrentHashMap

/**
 * @author sollian on 2017/9/4.
 */

class IUGifDrawable @Throws(Resources.NotFoundException::class, IOException::class)
constructor(
        resources: Resources,
        @DrawableRes
        @RawRes
        id: Int) : GifDrawable(resources, id) {

    private val textViewMap: MutableMap<Int, WeakReference<TextView>>

    init {
        textViewMap = ConcurrentHashMap<Int, WeakReference<TextView>>()
        callback = GifDrawableCallBack()
        setBounds(0, 0, intrinsicWidth, intrinsicHeight)
    }

    fun addTextView(textview: TextView) {
        textViewMap.put(textview.hashCode(), WeakReference(textview))
    }

    //从TextView刷新列表中移除, ListView复用需要进行移除
    fun removeTextView(textView: TextView) {
        textViewMap.remove(textView.hashCode())
    }

    private inner class GifDrawableCallBack : Drawable.Callback {

        override fun invalidateDrawable(
                who: Drawable) {
            for ((_, value) in textViewMap) {
                value.get()?.invalidate()
            }
        }

        override fun scheduleDrawable(
                who: Drawable,
                what: Runnable, `when`: Long) {
        }

        override fun unscheduleDrawable(
                who: Drawable,
                what: Runnable) {
        }
    }
}
