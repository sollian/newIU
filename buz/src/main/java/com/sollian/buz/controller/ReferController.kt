package com.sollian.buz.controller

import com.sollian.buz.bean.Refer
import com.sollian.buz.response.MailResponse
import com.sollian.buz.response.ReferResponse
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 * @author solli on 2017/9/24.
 */
class ReferController : AbsController() {
    companion object {
        val API_REFER = API_HEAD + "/refer/"
    }

    fun asyncGet(@Refer.ReferType type: String, page: Int,
                 consumer: ((response: ReferResponse) -> Unit)?) {
        getObservable("$API_REFER$type$FORMAT?page=$page&$APP_KEY")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { response ->
                    consumer?.invoke(ReferResponse(getJson(response)))
                }
    }
}