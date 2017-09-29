package com.sollian.base.Utils.permission

/**
 * @author sollian on 2017/6/29.
 */

interface IPermission {
    fun checkPermission(
            permissions: Array<String>,
            callback: IPermissionCallback)
}
