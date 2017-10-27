package com.sollian.iu.presenter

import android.content.Intent
import com.sollian.base.view.BasePresenter
import com.sollian.buz.controller.UserController
import com.sollian.buz.http.IUHttpManager
import com.sollian.buz.sharepref.SharePrefs
import com.sollian.iu.activity.MainActivity
import com.sollian.iu.activity.SignActivity
import org.jetbrains.anko.toast

/**
 * @author sollian on 2017/9/26.
 */
class SignPresenter(page: SignActivity) : BasePresenter<SignActivity>(page) {
    val userController by lazy {
        UserController()
    }

    fun signIn(name: String, password: String) {
        IUHttpManager.getInstance().initUserInfo(name, password)
        userController.asyncLogin { response ->
            getContext()?.onPostLogin(response.success())
            if (response.success()) {
                SharePrefs.name = name
                SharePrefs.password = password
                getContext()?.startActivity(Intent(getContext(), MainActivity::class.java))
                getContext()?.finish()
            } else {
                getContext()?.toast(response.desc!!)
            }
        }
    }
}