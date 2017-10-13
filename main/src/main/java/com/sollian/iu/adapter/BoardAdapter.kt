package com.sollian.iu.adapter

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView
import co.revely.gradient.RevelyGradient
import com.sollian.base.Utils.IUUtil
import com.sollian.buz.bean.Article
import com.sollian.buz.bean.User
import com.sollian.buz.controller.ArticleController
import com.sollian.buz.sharepref.SharePrefs
import com.sollian.iu.R
import com.sollian.iu.activity.UserInfoActivity
import com.sollian.iu.utils.GlideUtil
import de.hdodenhof.circleimageview.CircleImageView
import org.jetbrains.anko.find
import org.jetbrains.anko.toast

/**
 * @author sollian on 2017/10/10.
 */
open class BoardAdapter(
        val context: Context
) : RecyclerView.Adapter<BoardAdapter.BoardHolder>(), View.OnClickListener {
    private var columnWidth = SharePrefs.columnWidth
    private val maxPhotoCount = 3
    private val articleController = ArticleController()
    private val data = ArrayList<Article>()

    fun setData(articles: Array<Article>?) {
        data.clear()
        if (articles != null)
            data.addAll(articles.asList())
    }

    fun setData(articles: List<Article>?) {
        data.clear()
        if (articles != null)
            data.addAll(articles)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BoardHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_article, parent, false)
        return BoardHolder(view)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: BoardHolder, position: Int) {
        val article = getItem(position)

        holder.root.tag = position
        holder.root.setOnClickListener(this)
        holder.head.setTag(R.id.tag, article.user)
        holder.head.setOnClickListener(this)

        val boardColor = IUUtil.str2Color(article.board_name)

        holder.name.text = article.user.user_name
        holder.head.borderColor = boardColor
        if (article.user.didValid()) {
            GlideUtil.load(context, article.user.face_url, holder.head)
        } else {
            holder.head.setImageResource(R.drawable.iu_default_gray)
        }
        holder.time.text = IUUtil.formatTime(article.post_time)
        holder.readed.visibility = if (article.isReaded()) View.VISIBLE else View.GONE
        holder.readed.setTextColor(boardColor)

        holder.title.text = article.title

        holder.replyCount.text = context.getString(R.string.reply_count, article.reply_count)
        if (article.isCollected()) {
            holder.collect.imageTintList = ColorStateList.valueOf(boardColor)
        } else {
            holder.collect.imageTintList = ColorStateList.valueOf(context.resources.getColor(R.color.widget_normal_light))
        }
        holder.collect.tag = position
        holder.collect.setOnClickListener(this)

        RevelyGradient.linear()
                .colors(intArrayOf(0, Article.getMarkColor(context, article)))
                .angle(90.0f)
                .onBackgroundOf(holder.mark)

        if (columnWidth == 0) {
            columnWidth = holder.title.measuredWidth / maxPhotoCount
            if (columnWidth != 0) {
                SharePrefs.columnWidth = columnWidth
            }
        }

        val adapter: ImageGridAdapter
        if (article.photos.isNullOrEmpty()) {
            holder.imgGrid.visibility = View.GONE
            if (holder.imgGrid.adapter != null) {
                adapter = holder.imgGrid.adapter as ImageGridAdapter
                adapter.setData(null)
                adapter.notifyDataSetChanged()
            }
        } else {
            holder.imgGrid.visibility = View.VISIBLE
            var photoList = article.photos.split(Article.SPLITTER)
            if (photoList.size > maxPhotoCount) {
                photoList = photoList.subList(0, maxPhotoCount)
            }

            if (holder.imgGrid.adapter == null) {
                adapter = ImageGridAdapter(context, columnWidth)
                adapter.setData(photoList)
                holder.imgGrid.adapter = adapter
            } else {
                adapter = holder.imgGrid.adapter as ImageGridAdapter
                adapter.imgSize = columnWidth
                adapter.clearLastTasks()
                adapter.setData(photoList)
                adapter.notifyDataSetChanged()
            }
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.root -> {
                val position = v.tag as Int
                val article = getItem(position)
                if (!article.isReaded()) {
                    article.setReaded(true)
                    articleController.markRead(article.id)
                    notifyItemChanged(position)
                }
                //TODO:
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
            R.id.collect -> {
                val position = v.tag as Int
                val article = getItem(position)
                article.setCollected(!article.isCollected())
                articleController.markCollected(article.id, article.isCollected())
                notifyItemChanged(position)
                context.toast(if (article.isCollected()) R.string.collected else R.string.cancel_collect)
            }
            else -> {
            }
        }
    }

    internal fun getItem(position: Int) = data[position]

    open class BoardHolder(view: View) : RecyclerView.ViewHolder(view) {
        val root = view
        //        val boardName = view.find<TextView>(R.id.boardName)
        val name = view.find<TextView>(R.id.name)
        val head = view.find<CircleImageView>(R.id.head)
        val time = view.find<TextView>(R.id.time)
        val replyCount = view.find<TextView>(R.id.replyCount)
        val title = view.find<TextView>(R.id.title)
        val imgGrid = view.find<GridView>(R.id.imgGrid)
        val mark = view.find<View>(R.id.mark)
        val readed = view.find<TextView>(R.id.readed)
        val collect = view.find<ImageView>(R.id.collect)
    }
}