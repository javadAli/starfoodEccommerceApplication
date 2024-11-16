package com.example.starfood.ui.home.subkalas

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.starfood.databinding.AmountAdapterBinding
import com.example.starfood.datamodel.amountofproduct.AmountProductDataModel

class AmountAdapter(private val listSize: Int,private val secondUnit:String,private val amountUnit:String,private val defaultUnit:String, private val callBackItem: CallBackNumber) : RecyclerView.Adapter<AmountAdapter.ViewHolder>()
{

    val imgUrl ="https://starfoods.ir/resources/assets/images/mainGroups/"
    inner class ViewHolder(private val itemBinding: AmountAdapterBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(items: Int)
        {
            itemBinding.amountTxt.text = items.toString() + secondUnit + " معادل " + amountUnit.split(".")[0].toInt()*items+" " + defaultUnit
            itemBinding.amountTxt.setOnClickListener {
                callBackItem.setNumber((amountUnit.split('.')[0].toInt()*items).toString(),defaultUnit,secondUnit,items,amountUnit)
                Log.e("TestTest","I have clicked")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = AmountAdapterBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position+1)
    }

    override fun getItemCount(): Int {
        return listSize
    }
    interface CallBackNumber{
       fun setNumber(totalAmount:String,uName:String,secondUnit:String,number:Int,amountUnit: String)
    }
}