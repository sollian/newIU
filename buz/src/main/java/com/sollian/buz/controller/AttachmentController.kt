package com.sollian.buz.controller

import com.sollian.buz.response.AttachmentResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import java.io.File

/**
 * @author sollian on 2017/9/22.
 */
class AttachmentController : AbsController() {
    companion object {
        val API_ATTACHMENT = API_HEAD + "/attachment/"
    }

    /**
     * 上传附件
     *
     * @param board 合法的版面名称
     * @param id    文章或主题id
     * @param file  文件
     */
    fun asyncSend(board: String, id: Int, file: File,
                  consumer: ((response: AttachmentResponse) -> Unit)?) {
        postFileObservable(
                "$API_ATTACHMENT$board/add/$id$FORMAT?$APP_KEY", file)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { response ->
                    consumer?.invoke(AttachmentResponse(getJson(response)))
                }
    }

    /**
     * 获取附件信息
     *
     * @param board 合法的版面名称
     * @param id    文章或主题id
     */
    fun asyncGet(board: String, id: Int,
                 consumer: ((response: AttachmentResponse) -> Unit)?) {
        getObservable("$API_ATTACHMENT$board/$id$FORMAT?$APP_KEY")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { response ->
                    consumer?.invoke(AttachmentResponse(getJson(response)))
                }
    }

    /**
     * 删除附件
     *
     * @param board      合法的版面名称
     * @param id         文章或主题id
     * @param attachName 附件名
     */
    fun asyncDelete(board: String, id: Int, attachName: String,
                    consumer: ((response: AttachmentResponse) -> Unit)?) {
        val params = mapOf<String, String>(
                "name" to attachName
        )

        postObservable("${API_ATTACHMENT}$board/delete/$id$FORMAT?$APP_KEY", params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { response ->
                    consumer?.invoke(AttachmentResponse(getJson(response)))
                }
    }
}