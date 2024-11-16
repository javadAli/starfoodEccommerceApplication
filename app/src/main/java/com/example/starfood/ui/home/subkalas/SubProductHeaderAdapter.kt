package com.example.starfood.ui.home.subkalas

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.starfood.R
import com.example.starfood.databinding.ProducthdetailHeaderAdapterBinding
import com.example.starfood.datamodel.detailcategorydatamodel.HeaderDetailCategoryModelItem
import com.squareup.picasso.Picasso


class SubProductHeaderAdapter(private val product: List<HeaderDetailCategoryModelItem>, private val clickedSubKala : AppendSubKalaListener) : RecyclerView.Adapter<SubProductHeaderAdapter.ViewHolder>()
{
    private var selectedPosition = RecyclerView.NO_POSITION
    val imgUrl ="https://starfoods.ir/resources/assets/images/subgroup/"
    inner class ViewHolder(private val itemBinding: ProducthdetailHeaderAdapterBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(items: HeaderDetailCategoryModelItem)
        {
            itemBinding.title.text = items.title.trim()
            Picasso.get().load(imgUrl+"${items.id}.jpg").error(R.drawable.starfood).into(itemBinding.img)
            if (selectedPosition === position) {
                itemBinding.rootLayout.setBackgroundColor(
                    Color.RED
                )
            } else {
                itemBinding.rootLayout.setBackgroundColor(
                    Color.WHITE
                )
            }

            itemBinding.rootLayout.setOnClickListener {
                val previousPosition: Int = selectedPosition
                selectedPosition = adapterPosition
                notifyItemChanged(previousPosition)
                notifyItemChanged(selectedPosition)
                itemBinding.rootLayout.setBackgroundColor(Color.RED)
                clickedSubKala.getSubKala(items.selfGroupId,items.id)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = ProducthdetailHeaderAdapterBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(product[position])
    }

    // Returns the total count of items in the list
    override fun getItemCount(): Int {
        return product.size
    }
    interface AppendSubKalaListener{
        fun getSubKala(mainId:String,subKalaId:String)
    }
}