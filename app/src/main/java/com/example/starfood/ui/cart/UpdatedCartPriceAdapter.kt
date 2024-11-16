package com.example.starfood.ui.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.starfood.databinding.AdapterchangedPriceNameBinding

class UpdatedCartPriceAdapter (
    private val items: List<String>
) : RecyclerView.Adapter<UpdatedCartPriceAdapter.ViewHolder>()
{

    inner class ViewHolder(private val itemBinding: AdapterchangedPriceNameBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(items: String)
        {
           itemBinding.name.text = items
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = AdapterchangedPriceNameBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}