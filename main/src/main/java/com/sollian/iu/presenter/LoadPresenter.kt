package com.sollian.iu.presenter

import android.Manifest
import android.content.Intent
import android.os.Bundle
import com.sollian.base.Utils.BaseContext
import com.sollian.base.Utils.permission.IPermissionCallback
import com.sollian.base.view.BasePresenter
import com.sollian.buz.sharepref.SharePrefs
import com.sollian.iu.R
import com.sollian.iu.activity.LoadActivity
import com.sollian.iu.activity.MainActivity
import com.sollian.iu.activity.SignActivity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.toast
import java.util.concurrent.TimeUnit

/**
 * @author sollian on 2017/9/26.
 */
class LoadPresenter(page: LoadActivity) : BasePresenter<LoadActivity>(page) {
    val permissions = arrayOf(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Observable.create<Boolean> {
            it.onNext(SharePrefs.name.isNotEmpty() and SharePrefs.password.isNotEmpty())
        }
                .delay(0, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    tryJump(it)
                }

    }

    private fun tryJump(toMain: Boolean) {
        page.checkPermission(permissions, object : IPermissionCallback {
            override fun onCheckPermission(failedPermissions: List<String>) {
                if (failedPermissions.isEmpty()) {
                    BaseContext.initWithPermissions()

                    val intent: Intent =
                            if (toMain) Intent(page, MainActivity::class.java)
                            else Intent(page, SignActivity::class.java)
                    page.startActivity(intent)
                    page.finish()
                } else {
                    page.toast(R.string.no_storage_permission)
                    page.finish()
                }
            }
        })
    }
}