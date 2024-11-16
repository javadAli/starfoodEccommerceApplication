package com.example.starfood.ui.home

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.starfood.R
import com.example.starfood.common.networkMonitoring.NetworkStateHolder
import com.example.starfood.databinding.FragmentSliderBinding
import com.smarteist.autoimageslider.SliderViewAdapter
import com.squareup.picasso.Picasso

class SliderAdapter(sliderDataArrayList: List<String>,private val context: Context?) :
    SliderViewAdapter<SliderAdapter.SliderAdapterViewHolder>() {
    private val mSliderItems: List<String>
    val imgUrl ="https://starfoods.ir/resources/assets/images/mainSlider/"
    init {
        mSliderItems = sliderDataArrayList
    }
    override fun onCreateViewHolder(parent: ViewGroup): SliderAdapterViewHolder {
        val itemBinding = FragmentSliderBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SliderAdapterViewHolder(itemBinding)
    }
    override fun onBindViewHolder(viewHolder: SliderAdapterViewHolder, position: Int) {
        viewHolder.bind(mSliderItems[position])
    }
    override fun getCount(): Int {
        return mSliderItems.size
    }
    inner class SliderAdapterViewHolder(private val itemBinding: FragmentSliderBinding) : ViewHolder(itemBinding.root) {
        fun bind(items: String){
            if(!NetworkStateHolder.isConnected) {
                val resourceId = context?.resources?.getIdentifier(items, "drawable",context?.packageName!!)
                if (resourceId != 0) {
                    Glide.with(itemView).load(resourceId).fitCenter().into(itemBinding.img);
                }else{
                    Glide.with(itemView).load(R.drawable.starfood).fitCenter().into(itemBinding.img);
                }
            }else{
                Glide.with(itemView)
                    .load(imgUrl + items)
                    .fitCenter()
                    .into(itemBinding.img);
            }
        }
    }
}