package com.example.starfood.ui.home.subkalas

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.starfood.databinding.SimilarAdapterBinding
import com.example.starfood.datamodel.productexplaindatamodel.AssameKalaX
import com.google.gson.Gson
import com.squareup.picasso.Picasso

class SimilarProductAdapter (private val activity: Activity,private val product: List<AssameKalaX>) : RecyclerView.Adapter<SimilarProductAdapter.ViewHolder>()
{

    val imgUrl ="https://starfoods.ir/resources/assets/images/kala/"
    inner class ViewHolder(private val itemBinding: SimilarAdapterBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(items: AssameKalaX)
        {
            itemBinding.title.text = items.GoodName.trim()
            Picasso.get().load(imgUrl+"${items.GoodSn}_1.jpg").into(itemBinding.img)
            itemBinding.img.setOnClickListener {
                val serializedObject = Gson().toJson(items)
                val intent = Intent(activity, ProductExplainActivity::class.java)
                intent.putExtra("goodSn",items.GoodSn)
                intent.putExtra("firstGroupId",items.GoodGroupSn)
                activity.startActivity(intent)
            }

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = SimilarAdapterBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(product[position])
    }

    // Returns the total count of items in the list
    override fun getItemCount(): Int {
        return product.size
    }
}