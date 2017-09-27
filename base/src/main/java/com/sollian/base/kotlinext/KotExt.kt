package com.sollian.base.kotlinext

import android.widget.EditText

/**
 * @author sollian on 2017/9/26.
 */
fun EditText.validString() = this.text.toString().trim()