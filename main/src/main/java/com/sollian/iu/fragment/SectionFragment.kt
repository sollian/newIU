package com.sollian.iu.fragment

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import com.sollian.base.kotlinext.DelegatesExt

/**
 * @author sollian on 2017/10/27.
 */
class SectionFragment : DialogFragment() {
    private var list by DelegatesExt.notNullSingleValue<RecyclerView>()

    override fun getLayoutInflater(savedInstanceState: Bundle): LayoutInflater {
        val inflater = super.getLayoutInflater(savedInstanceState)

        val window = dialog.window
        window.requestFeature(Window.FEATURE_NO_TITLE)
//        window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
//        window.setBackgroundDrawableResource(android.R.color.transparent)
//        window.decorView.setPadding(0, 0, 0, 0)
//        val layoutParams = window.attributes
//        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT
//        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT
//        window.attributes = layoutParams

        return inflater
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            RecyclerView(context)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list = view as RecyclerView
    }
}