package com.sollian.iu.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import com.gordonwong.materialsheetfab.MaterialSheetFab
import com.sollian.base.view.BaseFragmentActivity
import com.sollian.iu.R
import com.sollian.iu.presenter.AbsMainPresenter
import com.sollian.iu.presenter.MockMainPresenter
import com.sollian.iu.utils.GlideUtil
import com.sollian.iu.view.CustomFloatButton
import com.sollian.iu.view.SmoothLinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.navi_header.*

class MainActivity : BaseFragmentActivity<AbsMainPresenter>() {

    var isRefreshing = false

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

        swipeRefreshLayout.setColorSchemeColors(resources.getColor(R.color.theme))
        swipeRefreshLayout.setOnRefreshListener {
            isRefreshing = true
            presenter!!.onRefresh()
        }
        list.layoutManager = SmoothLinearLayoutManager(this)
        list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (isRefreshing || !presenter!!.hasNextPage()) return

                val llm = recyclerView.layoutManager as LinearLayoutManager
                val totalItemCount = recyclerView.adapter.itemCount
                val lastVisibleItemPosition = llm.findLastVisibleItemPosition()
                val visibleItemCount = recyclerView.childCount

                if (lastVisibleItemPosition == totalItemCount - 1
                        && visibleItemCount > 0) {
                    isRefreshing = true
                    swipeRefreshLayout.isEnabled = false
                    presenter!!.onNextPage()
                }
            }
        })

        val materialSheetFab = MaterialSheetFab<CustomFloatButton>(
                floatbtn, sheet, overlay,
                Color.WHITE, resources.getColor(R.color.theme))
    }

    fun setPresenter(presenter: AbsMainPresenter) {
        this.presenter = presenter

        refreshViewWithPresenter()
    }

    fun refreshViewWithPresenter() {
        list.adapter = presenter!!.getAdapter()
//        val fragment = MockFragment()
//        val fragment = Fragment.instantiate(this, MockFragment::class.java.name)
//        supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commitAllowingStateLoss()
    }

    fun onNotifyDataChanged() {
        list.adapter?.notifyDataSetChanged()
        isRefreshing = false
        swipeRefreshLayout.isRefreshing = false
        swipeRefreshLayout.isEnabled = true
    }
}
