package com.sollian.base.kotlinext

import android.widget.EditText
import com.sollian.base.Utils.BaseContext
import org.jetbrains.anko.dip

/**
 * @author sollian on 2017/9/26.
 */
fun EditText.validString() = this.text.toString().trim()

fun Int.dp2px() = BaseContext.context.dip(this)