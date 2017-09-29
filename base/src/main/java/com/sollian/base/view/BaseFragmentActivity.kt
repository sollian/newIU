package com.sollian.base.view

import android.os.Bundle
import android.support.annotation.NonNull
import android.support.v7.app.AppCompatActivity
import com.sollian.base.R
import com.sollian.base.Utils.permission.IPermission
import com.sollian.base.Utils.permission.IPermissionCallback
import com.sollian.base.Utils.permission.IUPermissionHelper

/**
 * @author sollian on 2017/9/26.
 */
open class BaseFragmentActivity<T : BasePresenter<*>> : AppCompatActivity(),
        IContext, IPermission {
    protected var presenter: T? = null
    private val permissionHelper: IUPermissionHelper by lazy {
        IUPermissionHelper(this)
    }

    override fun getContext() = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = initPresenter()
        presenter?.onCreate(savedInstanceState)
    }

    final override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.onDestroy()
    }

    open fun getStatusbarColor() = resources.getColor(R.color.theme)

    open fun initPresenter(): T? = null

    override fun checkPermission(@NonNull permissions: Array<String>,
                                 callback: IPermissionCallback) {
        if (permissions.isEmpty()) return

        permissionHelper.checkPermission(permissions, callback)
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            @NonNull permissions: Array<String>,
                                            @NonNull grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        permissionHelper.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}