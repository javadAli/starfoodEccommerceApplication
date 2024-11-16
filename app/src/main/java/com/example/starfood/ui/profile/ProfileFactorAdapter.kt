package com.example.starfood.ui.profile

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.starfood.common.addNumberSeparator
import com.example.starfood.databinding.ProfileAdapterBinding
import com.example.starfood.datamodel.profile.Factor
import com.google.gson.Gson

class ProfileFactorAdapter (private val context:Context,private val factorList: List<Factor>) : RecyclerView.Adapter<ProfileFactorAdapter.ViewHolder>()
{

    val imgUrl ="https://starfoods.ir/resources/assets/images/mainGroups/"
    inner class ViewHolder(private val itemBinding: ProfileAdapterBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(items: Factor)
        {
            itemBinding.number.text = items.FactNo
            itemBinding.submitDate.text = items.FactDate
            itemBinding.money.text = addNumberSeparator( items.NetPriceHDS.toDouble()/10)
            itemBinding.showDetail.setOnClickListener{
                val serialized = Gson().toJson(items)
                var intent = Intent(context,FactorViewActivity::class.java)
                intent.putExtra("factor",serialized)
                context.startActivity(intent)
            }
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