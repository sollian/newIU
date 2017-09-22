package com.sollian.base.Utils

import android.text.TextUtils
import com.alibaba.fastjson.JSON

/**
 * @author sollian on 2017/9/22.
 */
object JsonUtil {
    fun <T> parse(json: String?, clazz: Class<T>): T? {
        if (TextUtils.isEmpty(json)) {
            return null
        }

        return JSON.parseObject(json, clazz)
    }

    fun toJson(obj: Any): String? = JSON.toJSONString(obj)
}