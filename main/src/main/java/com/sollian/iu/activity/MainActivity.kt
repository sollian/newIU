package com.sollian.iu.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import com.sollian.base.view.BaseFragmentActivity
import com.sollian.base.view.BasePresenter
import com.sollian.buz.controller.UserController
import com.sollian.buz.http.IUHttpManager
import com.sollian.buz.response.UserResponse
import com.sollian.buz.sharepref.SharePrefs
import com.sollian.iu.R

class MainActivity : BaseFragmentActivity<BasePresenter<*>>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun login(view: View) {
    }
}
