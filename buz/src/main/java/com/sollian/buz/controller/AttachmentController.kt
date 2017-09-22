package com.sollian.buz.controller

import com.sollian.buz.response.AttachmentResponse
import java.io.File

/**
 * @author sollian on 2017/9/22.
 */
class AttachmentController : AbsController() {
    companion object {
        val API_ATTACHMENT = API_HEAD + "/attachment/"
    }

    fun asyncSendAttachment(board: String, id: Int, file: File,
                            consumer: ((response: AttachmentResponse) -> Unit)?) {

    }
}