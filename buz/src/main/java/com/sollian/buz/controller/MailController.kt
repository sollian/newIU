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

    fun asyncGet(@Mailbox.BoxType box: String, index: Int,
                 consumer: ((response: MailResponse) -> Unit)?) {
        getObservable("$API_MAIL$box/$index$FORMAT?$APP_KEY")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { response ->
                    consumer?.invoke(MailResponse(getJson(response)))
                }
    }

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

    fun asyncDelete(@Mailbox.BoxType box: String, index: Int,
                    consumer: ((response: MailResponse) -> Unit)?) {
        postObservable("$API_MAIL$box/delete/$index$FORMAT?$APP_KEY", null)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { response ->
                    consumer?.invoke(MailResponse(getJson(response)))
                }
    }

    fun asyncGetBox(@Mailbox.BoxType box: String, page: Int,
                    consumer: ((response: MailResponse) -> Unit)?) {
        getObservable("$API_MAIL$box$FORMAT?$APP_KEY&page=$page")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { response ->
                    consumer?.invoke(MailResponse(getJson(response)))
                }
    }

    fun asyncGetBoxInfo(consumer: ((response: MailResponse) -> Unit)?) {
        getObservable(API_MAIL + "info" + FORMAT + '?' + APP_KEY)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { response ->
                    consumer?.invoke(MailResponse(getJson(response)))
                }
    }
}