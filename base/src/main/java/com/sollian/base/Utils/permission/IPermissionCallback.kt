package com.sollian.base.Utils.permission

/**
 * @author sollian on 2017/6/30.
 */

interface IPermissionCallback {
    fun onCheckPermission(failedPermissions: List<String>)
}
