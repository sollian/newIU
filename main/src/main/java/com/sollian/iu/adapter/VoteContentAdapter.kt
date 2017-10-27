package com.sollian.iu.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.TextView
import com.akexorcist.roundcornerprogressbar.TextRoundCornerProgressBar
import com.sollian.base.Utils.IUUtil
import com.sollian.buz.bean.Vote
import com.sollian.iu.R
import org.jetbrains.anko.find

/**
 * @author sollian on 2017/10/26.
 */
class VoteContentAdapter(
        val context: Context
) : RecyclerView.Adapter<VoteContentAdapter.ViewHolder>(), View.OnClickListener {

    companion object {
        private val MAX_PROGRESS = 100.0f
    }

    private var vote: Vote? = null

    fun setData(vote: Vote) {
        this.vote = vote
        calculateRelativeNum()
    }

    fun getData() = vote

    private fun calculateRelativeNum() {
        var max = 0
        vote!!.options.forEach {
            if (max < it.num) max = it.num
        }

        vote!!.options.forEach {
            it.num_relative = MAX_PROGRESS * it.num / max
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_vote_content, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val option = getItem(position)

        holder.root.tag = position
        if (vote!!.canVote())
            holder.root.setOnClickListener(this)

        holder.desc.text = option.label
        holder.progress.max = MAX_PROGRESS
        holder.progress.progress = option.num_relative
        holder.progress.progressText = option.num.toString()
        holder.progress.progressColor = IUUtil.str2Color(option.label)

        if (vote!!.didMultiVote()) {
            holder.radio.visibility = View.GONE
            holder.checkbox.visibility = View.VISIBLE
        } else {
            holder.radio.visibility = View.VISIBLE
            holder.checkbox.visibility = View.GONE
        }

        if (vote!!.didVoted()) {
            holder.radio.isChecked = vote!!.voted.viid.contains(option.viid)
            holder.checkbox.isChecked = vote!!.voted.viid.contains(option.viid)
        } else if (vote!!.is_end) {
            holder.radio.visibility = View.GONE
            holder.checkbox.visibility = View.GONE
        } else {
            holder.radio.isChecked = option.isChecked
            holder.checkbox.isChecked = option.isChecked

            if (vote!!.didMultiVote()) {
                if (getCurSelCount() < vote!!.limit) {
                    holder.checkbox.isEnabled = true
                } else {
                    holder.checkbox.isEnabled = holder.checkbox.isChecked
                    if (!holder.checkbox.isChecked) {
                        holder.root.setOnClickListener(null)
                    }
                }
            }
        }
    }

    private fun getCurSelCount(): Int {
        var count = 0
        vote?.options?.forEach {
            if (it.isChecked) count++
        }
        return count
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.root -> {
                val position = v.tag as Int
                if (vote!!.didMultiVote()) {
                    vote!!.options[position].isChecked = vote!!.options[position].isChecked.not()
                } else {
                    if (vote!!.options[position].isChecked) return
                    vote!!.options.forEach {
                        it.isChecked = false
                    }
                    vote!!.options[position].isChecked = true
                }
            }
        }
        notifyDataSetChanged()
    }

    override fun getItemCount() = vote?.options?.size ?: 0

    private fun getItem(position: Int): Vote.Option = vote!!.options[position]

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val root = view
        val desc = view.find<TextView>(R.id.desc)
        val progress = view.find<TextRoundCornerProgressBar>(R.id.progress)
        val radio = view.find<RadioButton>(R.id.radioButton)
        val checkbox = view.find<CheckBox>(R.id.checkbox)
    }
}