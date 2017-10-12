package com.sollian.iu.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.TextView
import co.revely.gradient.RevelyGradient
import com.sollian.base.Utils.IUUtil
import com.sollian.buz.bean.Article
import com.sollian.buz.bean.User
import com.sollian.buz.sharepref.SharePrefs
import com.sollian.iu.R
import com.sollian.iu.utils.GlideUtil
import de.hdodenhof.circleimageview.CircleImageView
import org.jetbrains.anko.find
import org.jetbrains.anko.toast

/**
 * @author sollian on 2017/10/10.
 */
class WidgetAdapter(
        val context: Context
) : RecyclerView.Adapter<WidgetAdapter.WidgetHolder>(), View.OnClickListener {
    val MAX_PHOTO_COUNT = 3
    var columnWidth = SharePrefs.columnWidth

    val data = ArrayList<Article>()

    fun setData(articles: Array<Article>?) {
        data.clear()
        if (articles != null)
            data.addAll(articles.asList())
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): WidgetHolder {
        val view = View.inflate(context, R.layout.item_widget, null)
        return WidgetHolder(view)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: WidgetHolder, position: Int) {
        val article = getItem(position)

        holder.root.tag = article
        holder.root.setOnClickListener(this)
        holder.head.setTag(R.id.tag, article.user)
        holder.head.setOnClickListener(this)

        holder.boardName.text = article.board_name
        val boardColor = IUUtil.str2Color(article.board_name)
        RevelyGradient.linear()
                .colors(intArrayOf(boardColor, 0))
                .onBackgroundOf(holder.boardName)
//        holder.boardName.setBackgroundColor(boardColor)

        holder.name.text = article.user.user_name
        holder.head.borderColor = boardColor
        if (article.user.didValid()) {
            GlideUtil.load(context, article.user.face_url, holder.head)
        } else {
            holder.head.setImageResource(R.drawable.iu_default_gray)
        }
        holder.time.text = IUUtil.formatTime(article.post_time)
        holder.replyCount.text = context.getString(R.string.reply_count, article.reply_count)

        holder.title.text = article.title

        RevelyGradient.linear()
                .colors(intArrayOf(0, Article.getMarkColor(context, article)))
                .angle(90.0f)
                .onBackgroundOf(holder.mark)
//        holder.mark.setBackgroundColor(Article.getMarkColor(context, article))

        if (columnWidth == 0) {
            columnWidth = holder.title.measuredWidth / MAX_PHOTO_COUNT
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
            if (photoList.size > MAX_PHOTO_COUNT) {
                photoList = photoList.subList(0, MAX_PHOTO_COUNT)
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
                val article = v.tag as Article
                //TODO:
            }
            R.id.head -> {
                val user = v.getTag(R.id.tag) as User
                if (user.didValid()) {
                    //TODO:
                } else {
                    context.toast(R.string.invalid_user)
                }
            }
            else -> {
            }
        }
    }

    private fun getItem(position: Int) = data[position]

    class WidgetHolder(view: View) : RecyclerView.ViewHolder(view) {
        val root = view
        val boardName = view.find<TextView>(R.id.boardName)
        val name = view.find<TextView>(R.id.name)
        val head = view.find<CircleImageView>(R.id.head)
        val time = view.find<TextView>(R.id.time)
        val replyCount = view.find<TextView>(R.id.replyCount)
        val title = view.find<TextView>(R.id.title)
        val imgGrid = view.find<GridView>(R.id.imgGrid)
        val mark = view.find<View>(R.id.mark)
    }
}