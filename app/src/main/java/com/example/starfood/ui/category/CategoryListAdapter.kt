package com.example.starfood.ui.category
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.starfood.databinding.CategoryAdapterBinding
import com.example.starfood.datamodel.categorydatamodel.CategoryDataModelItem
import com.example.starfood.ui.home.subkalas.SubProductActivity
import com.google.gson.Gson
import com.squareup.picasso.Picasso

class CategoryListAdapter(private val context: Context, private val category: MutableList<CategoryDataModelItem>) : RecyclerView.Adapter<CategoryListAdapter.ViewHolder>()
{
    val imgUrl ="https://starfoods.ir/resources/assets/images/mainGroups/"
    inner class ViewHolder(private val itemBinding: CategoryAdapterBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(items: CategoryDataModelItem){
           itemBinding.title.text = items.title.trim()
           Picasso.get().load(imgUrl+"${items.id}.jpg").into(itemBinding.img)
            itemBinding.linearLayout.setOnClickListener {
                val serializedObject = Gson().toJson(items)
                val intent = Intent(context, SubProductActivity::class.java)
                intent.putExtra("detailProduct",serializedObject)
                context.startActivity(intent)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val itemBinding = CategoryAdapterBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(itemBinding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(category[position])
    }
    override fun getItemCount(): Int {
        return category.size
    }
    fun addList(newList: List<CategoryDataModelItem>) {
        category.addAll(newList)
    }
}