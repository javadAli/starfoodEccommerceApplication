package com.example.starfood.ui.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.starfood.common.addNumberSeparator
import com.example.starfood.databinding.FactorviewAdapterBinding
import com.example.starfood.datamodel.profile.factorview.FactorBYS

class FactorViewAdapter(private val factorList: List<FactorBYS>) : RecyclerView.Adapter<FactorViewAdapter.ViewHolder>()
{
    val imgUrl ="https://starfoods.ir/resources/assets/images/mainGroups/"
    inner class ViewHolder(private val itemBinding: FactorviewAdapterBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(items: FactorBYS)
        {
            itemBinding.dateTxt.text = items.factorDate
            itemBinding.nameTxt.text = items.GoodName
            itemBinding.priceTxt.text = addNumberSeparator(((items.Fi.split(".")[0].toInt()*items.Amount.split(".")[0].toInt())/10).toDouble())+" تومان"
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        val itemBinding = FactorviewAdapterBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(itemBinding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(factorList[position])
    }
    override fun getItemCount(): Int {
        return factorList.size
    }
}