package com.sollian.buz.response

import android.text.TextUtils
import com.sollian.base.Utils.BaseContext
import com.sollian.buz.R
import org.json.JSONException
import org.json.JSONObject

/**
 * @author sollian on 2017/9/22.
 */
abstract class AbsResponse(val json: String?) {
    var desc: String? = null

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

    fun success() = TextUtils.isEmpty(desc)
}