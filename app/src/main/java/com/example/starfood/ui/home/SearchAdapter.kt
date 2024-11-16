package com.example.starfood.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.starfood.R
import com.example.starfood.datamodel.categorydatamodel.SearchDataModelItem
import com.squareup.picasso.Picasso

class SearchAdapter(
    private val searchItems: List<SearchDataModelItem>
) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
    interface OnItemClickListener {
        fun onItemClick(item: SearchDataModelItem)
    }

    private var listener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.search_adapter, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = searchItems[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = searchItems.size
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener?.onItemClick(searchItems[position])
                }
            }
        }
        private val imageView: ImageView = itemView.findViewById(R.id.image)
        private val nameTextView: TextView = itemView.findViewById(R.id.text2)
        var imgUrl= "https://starfoods.ir/resources/assets/images/kala/"
        fun bind(item: SearchDataModelItem) {
            Picasso.get().load(imgUrl+"${item.GoodSn}"+"_1.jpg").error(R.drawable.star).into(imageView)
            nameTextView.text = item.GoodName.trim()
        }
    }
}
