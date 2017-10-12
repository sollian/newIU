package com.sollian.iu.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.widget.DrawerLayout
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import com.gordonwong.materialsheetfab.MaterialSheetFab
import com.sollian.base.kotlinext.dp2px
import com.sollian.base.view.BaseFragmentActivity
import com.sollian.iu.R
import com.sollian.iu.presenter.*
import com.sollian.iu.utils.GlideUtil
import com.sollian.iu.utils.MarginItemDecoration
import com.sollian.iu.view.CustomFloatButton
import com.sollian.iu.view.SmoothLinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.navi_header.*
import org.jetbrains.anko.find

class MainActivity : BaseFragmentActivity<AbsMainPresenter>(),
        View.OnClickListener {
    private var isRefreshing = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        refreshViewWithPresenter()
    }

    override fun initPresenter(): AbsMainPresenter = WidgetPresenter(this)

    private fun initView() {
        menu.setOnClickListener(this)
        more.setOnClickListener(this)

        navi.setNavigationItemSelectedListener(MyNaviItemSelectedListener())

        drawerLayout.addDrawerListener(MyDrawerListener())

        swipeRefreshLayout.setColorSchemeColors(resources.getColor(R.color.theme))
        swipeRefreshLayout.setOnRefreshListener(MyRefreshListener())

        list.layoutManager = SmoothLinearLayoutManager(this)
        list.addItemDecoration(MarginItemDecoration(5.dp2px()))
        list.addOnScrollListener(MyScrollListener())

        val materialSheetFab = MaterialSheetFab<CustomFloatButton>(
                floatbtn, sheetList, overlay,
                Color.WHITE, resources.getColor(R.color.theme))
    }

    fun setPresenter(presenter: AbsMainPresenter) {
        this.presenter = presenter
        refreshViewWithPresenter(true)
    }

    private fun refreshViewWithPresenter(forceInitAdapter: Boolean = false) {
        setRefreshing(true)

        if (forceInitAdapter || list.adapter == null) {
            list.adapter = presenter!!.getAdapter()
        } else
            list.adapter?.notifyDataSetChanged()


        find<TextView>(R.id.title).text = presenter!!.getTitle()

        val menuAdapter = presenter!!.getMenuAdapter()
        floatbtn.setForceHide(menuAdapter == null)
        sheetList.adapter = menuAdapter
    }

    fun onNotifyDataChanged(presenter: AbsMainPresenter) {
        if (presenter != this.presenter) return

        list.adapter?.notifyDataSetChanged()
        setRefreshing(false)
    }

    private fun setRefreshing(refreshing: Boolean) {
        if (isRefreshing == refreshing) return

        isRefreshing = refreshing
        swipeRefreshLayout.isRefreshing = refreshing
        if (!refreshing) {
            swipeRefreshLayout.isEnabled = true
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.menu -> drawerLayout.openDrawer(Gravity.START)
            R.id.more -> presenter!!.onClickMore()
            else -> {
            }
        }
    }

    inner class MyNaviItemSelectedListener : NavigationView.OnNavigationItemSelectedListener {
        override fun onNavigationItemSelected(item: MenuItem): Boolean {
            drawerLayout.closeDrawer(Gravity.START)
            when (item.itemId) {
                R.id.menu_settings -> startActivity(Intent(this@MainActivity, SettingActivity::class.java))
                R.id.menu_about -> startActivity(Intent(this@MainActivity, AboutActivity::class.java))

                R.id.menu_home -> changePresenter(AbsMainPresenter.TYPE_WIDGET)
                R.id.menu_collect -> changePresenter(AbsMainPresenter.TYPE_COLLECT)
                R.id.menu_reply -> changePresenter(AbsMainPresenter.TYPE_REPLY)
                R.id.menu_at -> changePresenter(AbsMainPresenter.TYPE_AT)
                R.id.menu_mail -> changePresenter(AbsMainPresenter.TYPE_MAIL)
                R.id.menu_vote -> changePresenter(AbsMainPresenter.TYPE_VOTE)
                else -> {
                }
            }
            return true
        }

        private fun changePresenter(type: Int) {
            if (type == presenter!!.getType()) return

            when (type) {
                AbsMainPresenter.TYPE_WIDGET -> setPresenter(WidgetPresenter(this@MainActivity))
//                AbsMainPresenter.TYPE_BOARD->setPresenter(BoardPresenter(this@MainActivity))
                AbsMainPresenter.TYPE_COLLECT -> setPresenter(CollectPresenter(this@MainActivity))
                AbsMainPresenter.TYPE_REPLY -> setPresenter(ReplyPresenter(this@MainActivity))
                AbsMainPresenter.TYPE_AT -> setPresenter(AtPresenter(this@MainActivity))
                AbsMainPresenter.TYPE_MAIL -> setPresenter(MailPresenter(this@MainActivity))
                AbsMainPresenter.TYPE_VOTE -> setPresenter(VotePresenter(this@MainActivity))
                else -> {
                }
            }
        }
    }

    inner class MyDrawerListener : DrawerLayout.SimpleDrawerListener() {
        private var hasInit = false
        override fun onDrawerOpened(drawerView: View) {
            if (!hasInit && head != null) {
//                GlideUtil.load(this@MainActivity, presenter!!.user.face_url, header)
                hasInit = true
            }
        }
    }

    inner class MyRefreshListener : SwipeRefreshLayout.OnRefreshListener {
        override fun onRefresh() {
            isRefreshing = true
            presenter!!.onRefresh()
        }
    }

    inner class MyScrollListener : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            requestNextPage(recyclerView)
//            updateGlideState(newState)
        }

        private fun updateGlideState(newState: Int) {
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                GlideUtil.resume(this@MainActivity)
            } else {
                GlideUtil.pause(this@MainActivity)
            }
        }

        private fun requestNextPage(recyclerView: RecyclerView) {
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
    }
}
