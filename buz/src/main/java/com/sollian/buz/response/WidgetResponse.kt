package com.sollian.buz.response

import com.sollian.buz.bean.Widget

/**
 * @author sollian on 2017/9/22.
 */
class WidgetResponse(json: String?) : AbsResponse<Widget>(json) {
    override fun getObjClass() = Widget::class.java
}