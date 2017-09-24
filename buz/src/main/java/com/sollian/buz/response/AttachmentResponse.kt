package com.sollian.buz.response

import com.sollian.base.Utils.JsonUtil
import com.sollian.buz.bean.Attachment

/**
 * @author sollian on 2017/9/22.
 */
class AttachmentResponse(json: String?) : AbsResponse<Attachment>(json) {
    override fun getObjClass() = Attachment::class.java
}