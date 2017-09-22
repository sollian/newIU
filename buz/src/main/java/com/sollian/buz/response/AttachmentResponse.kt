package com.sollian.buz.response

import com.sollian.base.Utils.JsonUtil
import com.sollian.buz.bean.Attachment

/**
 * @author sollian on 2017/9/22.
 */
class AttachmentResponse(json: String?) : AbsResponse(json) {
    var attachment: Attachment? = null

    init {
        attachment = JsonUtil.parse(json, Attachment::class.java)
        checkError(attachment)
    }
}