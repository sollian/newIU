package com.sollian.iu.activity

import android.app.Activity
import android.os.Bundle

import com.sollian.iu.R

class UserInfoActivity : Activity() {
    companion object {
        val KEY_USER_ID = "user_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)
    }
}
