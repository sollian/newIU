package com.sollian.iu.presenter

import android.support.v7.widget.RecyclerView
import android.view.MenuItem
import com.sollian.buz.bean.Mail
import com.sollian.buz.bean.Mailbox
import com.sollian.buz.bean.User
import com.sollian.buz.controller.MailController
import com.sollian.iu.R
import com.sollian.iu.activity.MainActivity
import com.sollian.iu.adapter.MailAdapter
import org.jetbrains.anko.toast

/**
 * @author sollian on 2017/10/10.
 */
class MailPresenter(page: MainActivity) : AbsMainPresenter(page) {
    private val mailController = MailController()
    private val mails = arrayListOf<Mail>()
    private var curPage = DEFAULT_PAGE
    private var totalPage = DEFAULT_PAGE
    private val adapter = MailAdapter(page)

    init {
        mails.addAll(mockMail())
        adapter.setData(mails)
        onRefresh()
    }

    override fun getType() = TYPE_MAIL

    override fun getAdapter(): RecyclerView.Adapter<*>? = adapter

    override fun onRefresh() {
        curPage = DEFAULT_PAGE
        mailController.asyncGetBox(Mailbox.INBOX, curPage) {
            if (!it.success()) {
                getContext()?.toast(it.desc!!)
            } else {
                totalPage = it.obj!!.pagination.page_all_count
                mails.clear()
                mails.addAll(it.obj!!.mail)
            }
            getContext()?.onNotifyDataChanged(this)
        }
    }

    override fun onNextPage() {
        mailController.asyncGetBox(Mailbox.INBOX, curPage + 1) {
            if (!it.success()) {
                getContext()?.toast(it.desc!!)
            } else {
                totalPage = it.obj!!.pagination.page_all_count
                curPage = it.obj!!.pagination.page_current_count
                mails.removeAll(it.obj!!.mail)
                mails.addAll(it.obj!!.mail)
            }
            getContext()?.onNotifyDataChanged(this)
        }
    }

    override fun hasNextPage() = curPage < totalPage

    override fun getTitle(): String? = getContext()!!.getString(R.string.mail)

    override fun getMenuResId() = R.menu.menu_mail
    override fun onMenuClick(item: MenuItem) {
        super.onMenuClick(item)
        when (item.itemId) {
            R.id.menu_write -> {

            }
            else -> {

            }
        }
    }

    private fun mockMail(): List<Mail> {
        val mails = arrayListOf<Mail>()

        for (i in 0..10) {
            val mail = Mail()
            mail.content = "第" + i + "封邮件"
            mail.setIs_read(i > 4)
            mail.title = "标题" + i
            val user = User()
            user.user_name = "我是" + i
            user.face_url = "https://static.byr.cn/uploadFace/B/buerlc.3968.jpg"
            mail.user = user
            mails.add(mail)
        }
        return mails;
    }
}