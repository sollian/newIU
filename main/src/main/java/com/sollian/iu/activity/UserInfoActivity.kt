package com.sollian.iu.activity

import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.view.View
import co.revely.gradient.RevelyGradient
import com.sollian.base.Utils.IUUtil
import com.sollian.base.view.BaseFragmentActivity
import com.sollian.base.view.BasePresenter
import com.sollian.buz.bean.User
import com.sollian.buz.controller.UserController
import com.sollian.iu.R
import kotlinx.android.synthetic.main.activity_user_info.*
import org.jetbrains.anko.toast

class UserInfoActivity : BaseFragmentActivity<BasePresenter<*>>(), View.OnClickListener {

    companion object {
        val KEY_USER_ID = "user_id"
    }

    private var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)

        val userId = intent.getStringExtra(KEY_USER_ID)
        if (userId != null) {
            user = UserController().syncQuery(userId)
        }

        user = User()
        user?.user_name = "我是sss"
        user?.face_url = "https://static.byr.cn/uploadFace/B/buerlc.3968.jpg"

        if (user == null) {
            toast(R.string.no_user)
            finish()
            return
        }

        initView()
    }

    private fun initView() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            finish()
        }

        val colorTheme = resources.getColor(R.color.theme)

        appbar.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
            private val collapseColor = Color.WHITE
            private val expandedColor = colorTheme

            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
                toolbar.navigationIcon?.setTint(
                        IUUtil.blendColors(collapseColor, expandedColor,
                                1 - Math.abs(verticalOffset).toFloat() / appbar.totalScrollRange))
            }
        })

        collapse.title = user?.user_name
        collapse.setExpandedTitleColor(colorTheme)
        collapse.setCollapsedTitleTextColor(resources.getColor(R.color.text_first_invert))

        mail.setOnClickListener(this)

        val colorTransTheme = resources.getColor(R.color.theme_transparent)
        RevelyGradient.linear()
                .colors(intArrayOf(colorTransTheme, colorTransTheme, colorTheme, colorTransTheme, colorTransTheme))
                .onBackgroundOf(base_info)
        sex.text = user?.sex
        astro.text = user?.astro
        qq.text = user?.qq
        msn.text = user?.msn
        home.text = user?.home_page

        RevelyGradient.linear()
                .colors(intArrayOf(colorTransTheme, colorTransTheme, colorTheme, colorTransTheme, colorTransTheme))
                .onBackgroundOf(forum_info)
        level.text = user?.level
        life.text = user?.life.toString()
        post.text = user?.post_count.toString()
        score.text = user?.score.toString()
        online.text = user?.online
        firstLogin.text = IUUtil.formatTime(user!!.first_login_time)
        lastLogin.text = IUUtil.formatTime(user!!.last_login_time)
        role.text = user?.role
        loginCount.text = user?.login_count.toString()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.mail -> {
                //TODO
            }
        }
    }
}
