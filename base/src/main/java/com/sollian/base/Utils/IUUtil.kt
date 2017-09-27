package com.sollian.base.Utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 * @author sollian on 2017/9/22.
 */
object IUUtil {
    fun hideSoftKeyBoard(v: View?) {
        if (v == null) return

        val inputMethodManager =
                BaseContext.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (inputMethodManager.isActive()) {
            inputMethodManager.hideSoftInputFromWindow(v.windowToken,
                    InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }

    fun showSoftKeyBoard(v: View?) {
        if (v == null) return

        val inputMethodManager =
                BaseContext.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(v, 0)
    }
}