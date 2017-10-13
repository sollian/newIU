package com.sollian.iu.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import co.revely.gradient.RevelyGradient
import com.sollian.base.Utils.IUUtil
import com.sollian.buz.bean.Refer
import com.sollian.buz.bean.User
import com.sollian.buz.controller.ReferController
import com.sollian.iu.R
import com.sollian.iu.activity.UserInfoActivity
import com.sollian.iu.utils.GlideUtil
import de.hdodenhof.circleimageview.CircleImageView
import org.jetbrains.anko.find
import org.jetbrains.anko.toast

/**
 * @author sollian on 2017/10/10.
 */
open class ReferAdapter(
        val context: Context,
        private @Refer.ReferType val referType: String
) : RecyclerView.Adapter<ReferAdapter.ReferHolder>(), View.OnClickListener {
    private val referController = ReferController()
    private val data = ArrayList<Refer>()

    fun setData(articles: List<Refer>?) {
        data.clear()
        if (articles != null)
            data.addAll(articles)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReferHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_refer, parent, false)
        return ReferHolder(view)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ReferHolder, position: Int) {
        val refer = getItem(position)

        holder.root.tag = position
        holder.root.setOnClickListener(this)
        holder.root.isSelected = !refer.is_read

        holder.boardName.text = refer.board_name
        val boardColor = IUUtil.str2Color(refer.board_name)
        RevelyGradient.linear()
                .colors(intArrayOf(boardColor, 0))
                .onBackgroundOf(holder.boardName)

        holder.head.setTag(R.id.tag, refer.user)
        holder.head.setOnClickListener(this)

        holder.name.text = refer.user.user_name
        holder.head.borderColor = boardColor
        if (refer.user.didValid()) {
            GlideUtil.load(context, refer.user.face_url, holder.head)
        } else {
            holder.head.setImageResource(R.drawable.iu_default_gray)
        }
        holder.time.text = IUUtil.formatTime(refer.time)

        holder.title.text = refer.title
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.root -> {
                val position = v.tag as Int
                val refer = getItem(position)
                if (!refer.is_read) {
                    referController.asyncSetRead(referType, refer.index) {
                        if (it.success()) {
                            refer.setIs_read(true)
                            notifyItemChanged(position)
                            //TODO:
                        } else {
                            context.toast(it.desc!!)
                        }
                    }
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

    private fun getItem(position: Int) = data[position]

    open class ReferHolder(view: View) : RecyclerView.ViewHolder(view) {
        val root = view
        val boardName = view.find<TextView>(R.id.boardName)
        val name = view.find<TextView>(R.id.name)
        val head = view.find<CircleImageView>(R.id.head)
        val time = view.find<TextView>(R.id.time)
        val title = view.find<TextView>(R.id.title)
    }
}