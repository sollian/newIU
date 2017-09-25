package com.sollian.iu

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.util.Log
import android.view.View
import com.sollian.base.kotlinext.DelegatesExt
import com.sollian.buz.controller.UserController
import com.sollian.buz.http.IUHttpManager
import com.sollian.buz.response.UserResponse
import com.sollian.buz.sharepref.PrefKeys

class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        IUHttpManager.instance.initUserInfo("sollian", "111111")
        var name: String? by DelegatesExt.preference(this, PrefKeys.USER_NAME, null)
        name = "sollian"
    }

    fun login(view: View) {
        UserController().asyncLogin { response: UserResponse ->
            Log.e("---", response.obj.toString())
        }
    }
}
