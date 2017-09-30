package com.sollian.iu.activity

import android.content.Intent
import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.view.Gravity
import android.view.View
import com.sollian.base.view.BaseFragmentActivity
import com.sollian.iu.R
import com.sollian.iu.presenter.AbsMainPresenter
import com.sollian.iu.presenter.MockMainPresenter
import com.sollian.iu.utils.GlideUtil
import com.sollian.iu.view.SmoothLinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.navi_header.*

class MainActivity : BaseFragmentActivity<AbsMainPresenter>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        refreshViewWithPresenter()
    }

    override fun initPresenter(): AbsMainPresenter = MockMainPresenter(this)

    private fun initView() {
        menu.setOnClickListener {
            drawerLayout.openDrawer(Gravity.START)
        }

        navi.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_settings -> startActivity(Intent(this, SettingActivity::class.java))
                R.id.menu_about -> startActivity(Intent(this, AboutActivity::class.java))
                else -> {
                }
            }
            true
        }

        drawerLayout.addDrawerListener(object : DrawerLayout.SimpleDrawerListener() {
            var hasInit = false
            override fun onDrawerOpened(drawerView: View) {
                if (!hasInit && header != null) {
                    GlideUtil.load(this@MainActivity, presenter!!.user.face_url, header)
                    hasInit = true
                }
            }

            override fun onDrawerClosed(drawerView: View) {}
        })

        list.layoutManager = SmoothLinearLayoutManager(this)
    }

    fun resetPresenter(presenter: AbsMainPresenter) {
        this.presenter = presenter

    }

    fun refreshViewWithPresenter() {
        list.adapter = presenter!!.getAdapter()
    }
}
