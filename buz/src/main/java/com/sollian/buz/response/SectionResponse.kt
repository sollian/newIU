package com.sollian.buz.response

import com.sollian.buz.bean.Section

/**
 * @author sollian on 2017/9/22.
 */
class SectionResponse(json: String?) : AbsResponse<Section>(json) {
    override fun getObjClass() = Section::class.java
}