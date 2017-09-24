package com.sollian.buz.controller

import com.sollian.buz.bean.Refer
import com.sollian.buz.response.ReferResponse
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 * @author solli on 2017/9/24.
 */
class ReferController : AbsController() {
    companion object {
        val API_REFER = API_HEAD + "/refer/"
    }

    /**
     * 获取指定提醒类型列表
     *
     * @param type
     * @param page 提醒列表的页数
     */
    fun asyncGet(@Refer.ReferType type: String, page: Int,
                 consumer: ((response: ReferResponse) -> Unit)?) {
        getObservable("$API_REFER$type$FORMAT?page=$page&$APP_KEY")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { response ->
                    consumer?.invoke(ReferResponse(getJson(response)))
                }
    }

    /**
     * 获取指定类型提醒的属性信息
     *
     * @param type
     */
    fun asyncGetReferInfo(@Refer.ReferType type: String,
                          consumer: ((response: ReferResponse) -> Unit)?) {
        getObservable("$API_REFER$type/info$FORMAT?$APP_KEY")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { response ->
                    consumer?.invoke(ReferResponse(getJson(response)))
                }
    }

    /**
     * 设置指定提醒为已读
     *
     * @param type
     * @param index 提醒的索引，为提醒元数据中的index值。如果此参数不存在则设置此类型的所有提醒已读
     */
    fun asyncSetRead(@Refer.ReferType type: String, index: Int?,
                     consumer: ((response: ReferResponse) -> Unit)?) {
        val url = if (index == null) "$API_REFER$type/setRead$FORMAT?$APP_KEY"
        else "$API_REFER$type/setRead/$index$FORMAT?$APP_KEY"

        postObservable(url, null)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { response ->
                    consumer?.invoke(ReferResponse(getJson(response)))
                }
    }

    /**
     * 删除指定提醒
     *
     * @param type
     * @param index 提醒的索引，为提醒元数据中的index值。如果此参数不存在则删除此类型的所有提醒
     * @return
     */
    fun asyncDelete(@Refer.ReferType type: String, index: Int?,
                    consumer: ((response: ReferResponse) -> Unit)?) {
        val url = if (index == null) "$API_REFER$type/delete$FORMAT?$APP_KEY"
        else "$API_REFER$type/delete/$index$FORMAT?$APP_KEY"

        postObservable(url, null)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { response ->
                    consumer?.invoke(ReferResponse(getJson(response)))
                }
    }
}