package com.sollian.iu

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.util.Log
import android.view.View
import com.sollian.buz.controller.UserController
import com.sollian.buz.http.IUHttpManager
import com.sollian.buz.response.UserResponse

class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        IUHttpManager.instance.initUserInfo("sollian", "743251")
    }

    fun login(view: View) {
        UserController().asyncLogin { response: UserResponse ->
            Log.e("---", response.obj.toString())
        }
    }
}
