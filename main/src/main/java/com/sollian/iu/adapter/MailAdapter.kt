package com.sollian.iu.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.sollian.base.Utils.IUUtil
import com.sollian.buz.bean.Mail
import com.sollian.buz.bean.User
import com.sollian.buz.controller.MailController
import com.sollian.iu.R
import com.sollian.iu.activity.UserInfoActivity
import com.sollian.iu.utils.GlideUtil
import de.hdodenhof.circleimageview.CircleImageView
import org.jetbrains.anko.find
import org.jetbrains.anko.toast

/**
 * @author sollian on 2017/10/23.
 */
class MailAdapter(
        val context: Context
) : RecyclerView.Adapter<MailAdapter.ViewHolder>(), View.OnClickListener {

    private val data = arrayListOf<Mail>()
    private val mailController = MailController()

    fun setData(data: List<Mail>?) {
        this.data.clear()
        if (data != null)
            this.data.addAll(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_mail, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mail = getItem(position)

        holder.root.tag = position
        holder.root.setOnClickListener(this)
        holder.root.isSelected = !mail.is_read

        holder.head.setTag(R.id.tag, mail.user)
        holder.head.setOnClickListener(this)

        holder.name.text = mail.user.user_name
        if (mail.user.didValid()) {
            GlideUtil.load(context, mail.user.face_url, holder.head)
        } else {
            holder.head.setImageResource(R.drawable.iu_default_gray)
        }
        holder.time.text = IUUtil.formatTime(mail.post_time)

        holder.title.text = mail.title
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.root -> {
                val position = v.tag as Int
                val mail = getItem(position)
                if (!mail.is_read) {
                    //TODO:
                }
            }
            R.id.head -> {
                val user = v.getTag(R.id.tag) as User
                if (user.didValid()) {
                    val intent = Intent(context, UserInfoActivity::class.java)
                    intent.putExtra(UserInfoActivity.KEY_USER_ID, user.id)
                    context.startActivity(intent)
                } else {
                    context.toast(R.string.invalid_user)
                }
            }
            else -> {
            }
        }
    }

    override fun getItemCount() = data.size

    private fun getItem(position: Int) = data[position]

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val root = view
        val name = view.find<TextView>(R.id.name)
        val head = view.find<CircleImageView>(R.id.head)
        val time = view.find<TextView>(R.id.time)
        val title = view.find<TextView>(R.id.title)
    }
}