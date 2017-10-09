package com.sollian.iu.presenter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.sollian.base.view.BasePresenter
import com.sollian.iu.fragment.MockFragment
import org.jetbrains.anko.find

/**
 * @author solli on 2017/9/30.
 */
class MockFragmentPresenter(page: MockFragment) : BasePresenter<MockFragment>(page) {
    private val data = arrayListOf<Int>()
    private val adapter = MockAdapter()

    init {
        data += 0..100
    }

    fun getAdapter(): RecyclerView.Adapter<*> = adapter

    inner class MockAdapter : RecyclerView.Adapter<MockHolder>() {

        private fun getItem(position: Int) = data[position]

        override fun getItemCount() = data.size

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MockHolder {
            val root = LayoutInflater.from(page.context).inflate(android.R.layout.simple_list_item_1, parent, false)
            return MockHolder(root)
        }

        override fun onBindViewHolder(holder: MockHolder, position: Int) {
            holder.vText.text = getItem(position).toString()
        }
    }

    class MockHolder(view: View) : RecyclerView.ViewHolder(view) {
        val vText: TextView = view.find<TextView>(android.R.id.text1)

    }
}