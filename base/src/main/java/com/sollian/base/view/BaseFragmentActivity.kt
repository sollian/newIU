package com.sollian.base.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.sollian.base.R
import org.jetbrains.anko.find

/**
 * @author sollian on 2017/9/26.
 */
abstract class BaseFragmentActivity<T : BasePresenter<*>> : AppCompatActivity(), IContext {
    protected var presenter: T? = null

    override fun getContext() = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = initPresenter()
        presenter?.onCreate(savedInstanceState)
    }

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)

        val vDecor = window.decorView;
        vDecor.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        val viewGroup: ViewGroup = vDecor.find(android.R.id.content)
        viewGroup.getChildAt(0)?.fitsSystemWindows = true

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = resources.getColor(R.color.theme)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.onDestroy()
    }

    open fun getStatusbarColor() = resources.getColor(R.color.theme)

    open fun initPresenter(): T? = null
}