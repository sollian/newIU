package com.sollian.iu.activity

import android.os.Bundle
import android.view.WindowManager
import com.sollian.base.view.BaseFragmentActivity
import com.sollian.iu.R
import com.sollian.iu.presenter.LoadPresenter

class LoadActivity : BaseFragmentActivity<LoadPresenter>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_load)
    }

    override fun initPresenter() = LoadPresenter(this)
}
