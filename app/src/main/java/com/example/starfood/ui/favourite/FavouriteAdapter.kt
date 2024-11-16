package com.example.starfood.ui.favourite

import android.content.Intent
import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.starfood.R
import com.example.starfood.common.addNumberSeparator
import com.example.starfood.databinding.DetailCategoryAdapterBinding
import com.example.starfood.datamodel.favourite.Favorit
import com.example.starfood.datamodel.favourite.FavouriteDataModel
import com.example.starfood.datamodel.repository.userlogin.TokenContainer
import com.example.starfood.ui.home.subkalas.ProductExplainActivity
import com.example.starfood.ui.home.subkalas.SelectAmountDialog
import com.google.gson.Gson
import com.squareup.picasso.Picasso

class FavouriteAdapter (
    private val activity: FavouriteActivity,
    private val product: List<Favorit>,
    private val detailProduct: FavouriteDataModel,
    private val itemClick: ItemClickListener
) : RecyclerView.Adapter<FavouriteAdapter.ViewHolder>(), SelectAmountDialog.SelectAmountCallBack
{
    val imgUrl ="https://starfoods.ir/resources/assets/images/kala/"
    private lateinit var kalaId:String
    inner class ViewHolder(private val itemBinding: DetailCategoryAdapterBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(items: Favorit)
        {
            val secondUnitName=items.secondUnit
            itemBinding.title.text = items.GoodName.trim()
            if (!items.Price3.isNullOrEmpty() && items.Price3!=".000"&& items.Price3!=""){
                if (detailProduct.currencyName=="تومان")
                    itemBinding.profitPrice.text = addNumberSeparator(items.Price3.split(".")[0].toDouble()/10)+detailProduct.currencyName
            }
            if (!items.Price4.isNullOrEmpty() && items.Price4!=".000"&& items.Price4!="") {
                if (detailProduct.currencyName=="تومان")
                    itemBinding.firstPrice.text = addNumberSeparator(items.Price4.split(".")[0].toDouble()/10) +detailProduct.currencyName
                itemBinding.firstPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            }
            Picasso.get().load(imgUrl+"${items.GoodSn}"+"_1.jpg").into(itemBinding.img)
            if (!items.Price3.isNullOrEmpty() && items.Price3!=".000"&& items.Price3!="" && !items.Price4.isNullOrEmpty() && items.Price4!=".000"&& items.Price4!=""){
                if(items.Price4> items.Price3) {
                    itemBinding.profitIcon.visibility = View.VISIBLE
                    itemBinding.profitIcon.text =
                        "%" + (((Integer.parseInt(items.Price4.split(".")[0]) - Integer.parseInt(
                            items.Price3.split(".")[0]
                        )) * 100) / (Integer.parseInt(items.Price4.split(".")[0]))).toString() + " "
                }else{
                    itemBinding.profitIcon.visibility = View.GONE
                }
            }else {
                itemBinding.profitIcon.visibility = View.GONE
            }
            if (detailProduct.logoPos.toInt()==1){
                itemBinding.logoLeft.visibility=View.VISIBLE
                itemBinding.logoRight.visibility=View.GONE
            }else{
                itemBinding.logoLeft.visibility=View.GONE
                itemBinding.logoRight.visibility=View.VISIBLE
            }
            if(items.activePishKharid.toInt()<1){
                if (items.Amount.toDouble()>0 || items.freeExistance.toInt()>0){
                    if (items.callOnSale == 0){
                        if (items.bought == "Yes"){
                            itemBinding.numberLinear.visibility = View.VISIBLE
                            itemBinding.setAmountText.visibility = View.VISIBLE
                            itemBinding.selectNumberLayout.visibility = View.GONE
                            itemBinding.remindLayout.visibility = View.GONE
                            itemBinding.unavailableLayout.visibility = View.GONE
                            itemBinding.cancelRemindLayout.visibility = View.GONE
                            itemBinding.setAmountText.text = items.BoughtPackAmount.toString().split(".")[0] + secondUnitName + " معادل " + items.BoughtAmount+" " + items.UNAME
                        }else{
                            itemBinding.numberLinear.visibility = View.VISIBLE
                            itemBinding.selectNumberLayout.visibility = View.VISIBLE
                            itemBinding.remindLayout.visibility = View.GONE
                            itemBinding.unavailableLayout.visibility = View.GONE
                            itemBinding.cancelRemindLayout.visibility = View.GONE
                            itemBinding.setAmountText.visibility =View.GONE
                        }
                    }else{
                        itemBinding.numberLinear.visibility = View.VISIBLE
                        itemBinding.selectNumberLayout.visibility = View.GONE
                        itemBinding.remindLayout.visibility = View.GONE
                        itemBinding.unavailableLayout.visibility = View.GONE
                        itemBinding.cancelRemindLayout.visibility = View.GONE
                        itemBinding.setAmountText.visibility =View.GONE

                    }
                }else{
                    if (items.requested==0){
                        itemBinding.remindLayout.visibility = View.VISIBLE
                        itemBinding.cancelRemindLayout.visibility = View.GONE
                        itemBinding.selectNumberLayout.visibility = View.GONE
                    }else{
                        itemBinding.cancelRemindLayout.visibility = View.VISIBLE
                        itemBinding.remindLayout.visibility = View.GONE
                        itemBinding.selectNumberLayout.visibility = View.GONE
                    }
                    itemBinding.unavailableLayout.visibility = View.VISIBLE
                    itemBinding.numberLinear.visibility = View.GONE
                    itemBinding.selectNumberLayout.visibility = View.GONE
                    itemBinding.setAmountText.visibility = View.GONE
                    itemBinding.profitIcon.visibility = View.GONE
                }
            }else{
                if(items.activePishKharid.toInt()>0){
                    itemBinding.numberLinear.visibility = View.GONE
                    itemBinding.selectNumberLayout.visibility = View.GONE
                    itemBinding.remindLayout.visibility = View.GONE
                    itemBinding.unavailableLayout.visibility = View.GONE
                    itemBinding.cancelRemindLayout.visibility = View.GONE
                    itemBinding.setAmountText.visibility =View.GONE
                }else{
                    itemBinding.numberLinear.visibility = View.GONE
                    itemBinding.selectNumberLayout.visibility = View.GONE
                    itemBinding.remindLayout.visibility = View.GONE
                    itemBinding.unavailableLayout.visibility = View.GONE
                    itemBinding.cancelRemindLayout.visibility = View.GONE
                    itemBinding.setAmountText.visibility =View.GONE
                }
            }
            if (items.favorite ==0){
                itemBinding.likeBtn.visibility=View.GONE
                itemBinding.likeBorderBtn.visibility=View.VISIBLE
            }else{
                itemBinding.likeBtn.visibility=View.VISIBLE
                itemBinding.likeBorderBtn.visibility=View.GONE
            }

            itemBinding.img.setOnClickListener{
                val intent = Intent(activity, ProductExplainActivity::class.java)
                intent.putExtra("goodSn",items.GoodSn)
                intent.putExtra("firstGroupId",items.GoodGroupSn)
                activity.startActivity(intent)
            }
            itemBinding.cancelRemindLayout.setOnClickListener {
                activity.cancelReminder(TokenContainer.psn!!,items.GoodSn)
                itemBinding.cancelRemindLayout.visibility = View.GONE
                itemBinding.remindLayout.visibility=View.VISIBLE
            }
            itemBinding.remindLayout.setOnClickListener{
                activity.submitReminder(TokenContainer.psn!!,items.GoodSn)
                itemBinding.cancelRemindLayout.visibility = View.VISIBLE
                itemBinding.remindLayout.visibility=View.GONE
            }
            itemBinding.likeBtn.setOnClickListener {
                itemBinding.likeBtn.setImageResource(R.drawable.favorite)
                itemBinding.likeBtn.setColorFilter(Color.RED)
                activity.favourite(items.GoodSn,TokenContainer.psn!!)
            }
            itemBinding.likeBtn.setOnClickListener {
                activity.favourite(items.GoodSn,TokenContainer.psn!!)
                itemBinding.likeBtn.visibility=View.GONE
                itemBinding.likeBorderBtn.visibility=View.VISIBLE
            }
            itemBinding.likeBorderBtn.setOnClickListener {
                activity.favourite(items.GoodSn,TokenContainer.psn!!)
                itemBinding.likeBtn.visibility=View.VISIBLE
                itemBinding.likeBorderBtn.visibility=View.GONE
            }
            itemBinding.selectNumberLayout.setOnClickListener {
                kalaId = items.GoodSn.split(".")[0]
                var dialog = SelectAmountDialog(
                    this@FavouriteAdapter,
                    items.GoodSn.split(".")[0],
                    items.SecondUnitAmount,
                    items.UNAME,
                    secondUnitName,
                    items.Amount.split(".")[0],
                    items.Amount.split(".")[0]
                )
                dialog.show(activity.supportFragmentManager,null)
                itemClick.itemClick(this)
            }
            itemBinding.setAmountText.setOnClickListener {
                kalaId = items.GoodSn.split(".")[0]
                var dialog = SelectAmountDialog(
                    this@FavouriteAdapter,
                    items.GoodSn.split(".")[0],
                    items.SecondUnitAmount,
                    items.UNAME,
                    secondUnitName,
                    items.Amount.split(".")[0],
                    items.Amount.split(".")[0]
                )
                dialog.show(activity.supportFragmentManager,null)
                itemClick.itemClick(this)
            }
        }
        fun setAmount(totalItem: String,uName: String,secondUnit: String,number: Int){
            itemBinding.setAmountText.visibility = View.VISIBLE
            itemBinding.selectNumberLayout.visibility = View.GONE
            itemBinding.setAmountText.text = "$number$secondUnit معادل $totalItem $uName"
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = DetailCategoryAdapterBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(product[position])
    }

    override fun getItemCount(): Int {
        return product.size
    }

    override fun onAddAmount(
        totalItem: String,
        uName: String?,
        secondUnit: String?,
        number: Int,
        amountUnit: String
    ) {
        activity.purchaseRegistration(kalaId,totalItem)
        itemClick.getItem(totalItem,uName!!,secondUnit!!,number)
    }
    interface ItemClickListener{
        fun  itemClick(holder: FavouriteAdapter.ViewHolder)
        fun getItem(totalItem: String,uName: String, secondUnit: String,number:Int)
    }
}