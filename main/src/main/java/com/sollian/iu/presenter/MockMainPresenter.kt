package com.sollian.iu.presenter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.sollian.buz.bean.User
import com.sollian.iu.activity.MainActivity
import org.jetbrains.anko.find

/**
 * @author solli on 2017/9/30.
 */
class MockMainPresenter(page: MainActivity) : AbsMainPresenter(page) {
    val data = arrayListOf<Int>()
    val adapter = MockAdapter()

    init {
        for (i in 0..100) {
            data.add(i)
        }
    }

    override fun getAdapter(): RecyclerView.Adapter<*> = adapter

    inner class MockAdapter : RecyclerView.Adapter<MockHolder>() {

        fun getItem(position: Int) = data[position]

        override fun getItemCount() = data.size

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MockHolder {
            val root = LayoutInflater.from(page).inflate(android.R.layout.simple_list_item_1, parent, false)
            return MockHolder(root)
        }

        override fun onBindViewHolder(holder: MockHolder, position: Int) {
            holder.vText.text = getItem(position).toString()
        }
    }

    class MockHolder(view: View) : RecyclerView.ViewHolder(view) {
        val vText: TextView

        init {
            vText = view.find<TextView>(android.R.id.text1)
        }
    }
}