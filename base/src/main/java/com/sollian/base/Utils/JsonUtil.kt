package com.sollian.base.Utils

import com.alibaba.fastjson.JSON

/**
 * @author sollian on 2017/9/22.
 */
object JsonUtil {
    fun <T> parse(json: String?, clazz: Class<T>): T? {
        if (json.isNullOrEmpty()) {
            return null
        }

        try {
            return JSON.parseObject(json, clazz)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    fun toJson(obj: Any): String? = JSON.toJSONString(obj)
}