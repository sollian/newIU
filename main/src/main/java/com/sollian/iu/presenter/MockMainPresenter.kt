package com.sollian.iu.presenter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.sollian.iu.activity.MainActivity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import org.jetbrains.anko.find
import java.util.concurrent.TimeUnit

/**
 * @author solli on 2017/9/30.
 */
class MockMainPresenter(page: MainActivity) : AbsMainPresenter(page) {
    override fun getType() = TYPE_WIDGET

    override fun getTitle(): String? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private val data = arrayListOf<Int>()
    private val adapter = MockAdapter()

    var upper = 20

    init {
        data += 0..20
    }

    override fun getAdapter(): RecyclerView.Adapter<*> = adapter

    override fun onRefresh() {
        Observable.timer(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    data.clear()
                    data += 0..20
                    upper = 20
                    getContext()?.onNotifyDataChanged(this)
                }
    }

    override fun onNextPage() {
        Observable.timer(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    data += upper + 1..upper + 20
                    upper += 20
                    getContext()?.onNotifyDataChanged(this)
                }
    }

    override fun hasNextPage() = upper < 100

    inner class MockAdapter : RecyclerView.Adapter<MockHolder>() {

        private fun getItem(position: Int) = data[position]

        override fun getItemCount() = data.size

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MockHolder {
            val root = LayoutInflater.from(getContext()?.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false)
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