package com.sollian.buz.controller

import com.sollian.buz.response.SectionResponse
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 * @author solli on 2017/9/24.
 */
class SectionController : AbsController() {
    companion object {
        val API_SECTION = API_HEAD + "/section"
    }

    /**
     * 获取所有根分区信息
     *
     */
    fun asyncGetRoot(consumer: ((response: SectionResponse) -> Unit)?) {
        getObservable(API_SECTION + FORMAT + '?' + APP_KEY)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { response ->
                    consumer?.invoke(SectionResponse(getJson(response)))
                }
    }

    /**
     * 获取指定分区的信息
     *
     * @param section 合法的分区名称
     */
    fun asyncGet(section: String, consumer: ((response: SectionResponse) -> Unit)?) {
        getObservable("$API_SECTION/$section$FORMAT?$APP_KEY")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { response ->
                    consumer?.invoke(SectionResponse(getJson(response)))
                }
    }
}