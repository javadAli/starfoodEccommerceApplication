package com.example.starfood.ui.message

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.starfood.databinding.ReplayItemBinding
import com.example.starfood.message.Replay

class ReplyAdapter(private val replies: List<Replay>) : RecyclerView.Adapter<ReplyAdapter.ReplyViewHolder>() {

    inner class ReplyViewHolder(private val itemBinding: ReplayItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(items: Replay)
        {
                itemBinding.replyLayout.visibility = View.VISIBLE
                itemBinding.dateTxtReply.text=items.replayHijriDate
                itemBinding.replayText.text=items.replayContent.trim()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReplyViewHolder {
        val view: ReplayItemBinding = ReplayItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ReplyViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReplyViewHolder, position: Int) {
        holder.bind(replies[position])
    }

    override fun getItemCount(): Int {
        return replies.size
    }
}