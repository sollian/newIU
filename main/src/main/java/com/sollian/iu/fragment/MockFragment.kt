package com.sollian.iu.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sollian.base.view.BaseFragment
import com.sollian.iu.R
import com.sollian.iu.presenter.MockFragmentPresenter
import com.sollian.iu.view.SmoothLinearLayoutManager
import kotlinx.android.synthetic.main.fragment_mock.*

/**
 * @author sollian on 2017/10/9.
 */
class MockFragment : BaseFragment<MockFragmentPresenter>() {
    override fun initPresenter() = MockFragmentPresenter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.fragment_mock, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        list.layoutManager = SmoothLinearLayoutManager(activity)
        list.adapter = presenter!!.getAdapter()
    }
}