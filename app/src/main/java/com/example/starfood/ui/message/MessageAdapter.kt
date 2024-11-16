package com.example.starfood.ui.message

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.starfood.databinding.OtherMessageBinding
import com.example.starfood.message.Message

class MessageAdapter(private val messageListDataModel: List<Message>)  : RecyclerView.Adapter<MessageAdapter.ViewHolder>(){
/*    private var messages: MutableList<Message>? = null
    init {
        messages = LinkedList()
    }*/
    inner class ViewHolder(private val itemBinding: OtherMessageBinding) : RecyclerView.ViewHolder(itemBinding.root){
        fun bind(items: Message)
        {
            itemBinding.dateTxt.text = items.messageHijriDate
            itemBinding.messageTxt.text = items.messageContent
            if (!items.replay.isNullOrEmpty()) {
                itemBinding.replyLayout.visibility = View.VISIBLE
                val replyAdapter = ReplyAdapter(items.replay)
                itemBinding.recyclerViewReplies.layoutManager = LinearLayoutManager(itemBinding.root.context)
                itemBinding.recyclerViewReplies.adapter = replyAdapter
            } else {
                itemBinding.replyLayout.visibility = View.GONE
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = OtherMessageBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(messageListDataModel!![position])
    }


    override fun getItemCount(): Int {
        return if (messageListDataModel == null) 0 else messageListDataModel!!.size

    }
/*
    fun add(item: Message) {
        messages!!.add(item)
        notifyItemInserted(messages!!.size-1)
    }

    fun addCurrentMessage(item: Message) {
        messages?.reverse()
        messages!!.add(item)
        messages!!.reverse()
        notifyItemInserted(0)
    }

    internal fun addAll(results: List<Message>) {
        Collections.reverse(results)
        for (result in results) {
            add(result)
        }
    }

    fun listCount():Int{
        return messages!!.size
    }

    fun clear(){
        messages!!.clear()
    }*/

}