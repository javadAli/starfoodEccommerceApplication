package com.example.starfood.ui.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.starfood.databinding.ProfileAdapterBinding
import com.example.starfood.datamodel.profile.Order

class ProfileOrderAdapter (private val factorList: List<Order>) : RecyclerView.Adapter<ProfileOrderAdapter.ViewHolder>()
{

    val imgUrl ="https://starfoods.ir/resources/assets/images/mainGroups/"
    inner class ViewHolder(private val itemBinding: ProfileAdapterBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(items: Order)
        {
            itemBinding.number.text = items.OrderNo
            itemBinding.submitDate.text = items.OrderDate
            //itemBinding.detail.text = items.OrderDesc
            itemBinding.money.text = items.Price.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = ProfileAdapterBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(factorList[position])
    }

    override fun getItemCount(): Int {
        return factorList.size
    }
}