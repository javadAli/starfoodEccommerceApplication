package com.example.starfood.ui.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.starfood.common.addNumberSeparator
import com.example.starfood.databinding.FactorAdapterLayoutBinding
import com.example.starfood.datamodel.addfactor.OrderBYS

class FactorAdapter(private val orderBYS: List<OrderBYS>) :
    RecyclerView.Adapter<FactorAdapter.ViewHolder>() {
    inner class ViewHolder(private val itemBinding: FactorAdapterLayoutBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(items: OrderBYS) {
            itemBinding.radif.text=(position + 1).toString()
            itemBinding.name.text = items.GoodName.trim()
            itemBinding.amount.text = addNumberSeparator(items.Amount.split(".")[0].toDouble())
            itemBinding.total.text = addNumberSeparator(items.Price.toDouble()/10)+" تومان "
            itemBinding.unitOfMoney.text = addNumberSeparator(items.Fi.split(".")[0].toDouble()/10)+" تومان "
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = FactorAdapterLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(itemBinding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(orderBYS[position])
    }
    override fun getItemCount(): Int {
        return orderBYS.size
    }
}