package com.sollian.buz.controller

import com.sollian.buz.bean.Mailbox
import com.sollian.buz.response.MailResponse
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 * @author solli on 2017/9/24.
 */
class MailController : AbsController() {
    companion object {
        val API_MAIL = API_HEAD + "/mail/"
    }

    /**
     * 获取指定信件信息
     *
     * @param box
     * @param index 信件在信箱的索引,为信箱信息的信件列表中每个信件对象的index值
     */
    fun asyncGet(@Mailbox.BoxType box: String, index: Int,
                 consumer: ((response: MailResponse) -> Unit)?) {
        getObservable("$API_MAIL$box/$index$FORMAT?$APP_KEY")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { response ->
                    consumer?.invoke(MailResponse(getJson(response)))
                }
    }

    /**
     * 发送新信件
     *
     * @param subject 主题
     * @param content 内容
     * @param strTo   收信人id
     */
    fun asyncSend(title: String, content: String, strTo: String,
                  consumer: ((response: MailResponse) -> Unit)?) {
        val params = mapOf<String, String>(
                "title" to title,
                "content" to content,
                "id" to strTo
        )

        postObservable(API_MAIL + "send" + FORMAT + '?' + APP_KEY, params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { response ->
                    consumer?.invoke(MailResponse(getJson(response)))
                }
    }

    /**
     * 转寄邮件
     *
     * @param index
     * @param userId
     */
    fun asyncForward(index: Int, userId: String,
                     consumer: ((response: MailResponse) -> Unit)?) {
        val params = mapOf<String, String>(
                "target" to userId
        )

        postObservable(API_MAIL + "inbox/forward/" + index + FORMAT + '?' + APP_KEY, params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { response ->
                    consumer?.invoke(MailResponse(getJson(response)))
                }
    }

    /**
     * 回复指定信箱中的邮件
     *
     * @param box
     * @param index   信件在信箱的索引,为信箱信息的信件列表中每个信件对象的index值
     * @param title
     * @param content
     */
    fun asyncReply(@Mailbox.BoxType box: String, index: Int, title: String, content: String,
                   consumer: ((response: MailResponse) -> Unit)?) {
        val params = mapOf<String, String>(
                "title" to title,
                "content" to content
        )

        postObservable("$API_MAIL$box/reply/$index$FORMAT?$APP_KEY", params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { response ->
                    consumer?.invoke(MailResponse(getJson(response)))
                }
    }

    /**
     * 删除指定信件
     *
     * @param box
     * @param index 信件在信箱的索引,为信箱信息的信件列表中每个信件对象的index值
     */
    fun asyncDelete(@Mailbox.BoxType box: String, index: Int,
                    consumer: ((response: MailResponse) -> Unit)?) {
        postObservable("$API_MAIL$box/delete/$index$FORMAT?$APP_KEY", null)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { response ->
                    consumer?.invoke(MailResponse(getJson(response)))
                }
    }

    /**
     * 获取指定信箱信息
     *
     * @param box
     * @param page 信箱的页数
     */
    fun asyncGetBox(@Mailbox.BoxType box: String, page: Int,
                    consumer: ((response: MailResponse) -> Unit)?) {
        getObservable("$API_MAIL$box$FORMAT?$APP_KEY&page=$page")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { response ->
                    consumer?.invoke(MailResponse(getJson(response)))
                }
    }

    /**
     * 信箱属性信息，包括是否有新邮件
     */
    fun asyncGetBoxInfo(consumer: ((response: MailResponse) -> Unit)?) {
        getObservable(API_MAIL + "info" + FORMAT + '?' + APP_KEY)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { response ->
                    consumer?.invoke(MailResponse(getJson(response)))
                }
    }
}