package com.sollian.buz.response

import android.text.TextUtils
import com.sollian.base.Utils.BaseContext
import com.sollian.base.Utils.JsonUtil
import com.sollian.buz.R
import org.json.JSONException
import org.json.JSONObject

/**
 * @author sollian on 2017/9/22.
 */
abstract class AbsResponse<T>(val json: String?) {
    var desc: String? = null
    var obj: T? = null

    init {
        obj = JsonUtil.parse(json, getObjClass())
        checkError(obj)
    }

    abstract fun getObjClass(): Class<T>

    fun checkError(obj: Any?) {
        if (obj != null) return

        if (TextUtils.isEmpty(json)) {
            desc = BaseContext.context.getString(R.string.unknown_error)
            return
        }

        try {
            val jsonObject = JSONObject(json)
            desc = jsonObject.getString("msg")
        } catch (e: JSONException) {
            e.printStackTrace()
            desc = json
        }
    }

    fun success() = TextUtils.isEmpty(desc)// && obj != null
}