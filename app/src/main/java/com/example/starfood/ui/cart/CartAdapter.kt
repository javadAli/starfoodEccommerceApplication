package com.example.starfood.ui.cart

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.starfood.common.addNumberSeparator
import com.example.starfood.databinding.CartAdapterBinding
import com.example.starfood.databinding.CartDeleteAlertBinding
import com.example.starfood.datamodel.cartitem.CartDataModel
import com.example.starfood.datamodel.cartitem.CartOrder
import com.example.starfood.ui.home.subkalas.SelectAmountDialog
import com.squareup.picasso.Picasso

class CartAdapter(private val context: Context, private val itemClick:ItemClick
                  , private val product: ArrayList<CartOrder>, private val dataModel:CartDataModel, private val fragment:Fragment) : RecyclerView.Adapter<CartAdapter.ViewHolder>(),
    SelectAmountDialog.SelectAmountCallBack
{
    val imgUrl ="https://starfoods.ir/resources/assets/images/kala/"
    private var sud : Long = 0
    private var total : Long = 0
    private var kalaId : Long = 0
    private var orderBys : Long = 0
    private lateinit var orderItem : CartOrder
    private var changedItemList = ArrayList<String>()
    inner class ViewHolder(private val itemBinding: CartAdapterBinding) : RecyclerView.ViewHolder(itemBinding.root){
        fun bind(items: CartOrder, position: Int)
        {
            itemBinding.productName.text = items.GoodName
            if (!items.Fi.isNullOrEmpty() && items.Fi!="0"){
                itemBinding.priceOneTxt.text = addNumberSeparator((items.Fi!!.toDouble())/dataModel.currency) +" "+ dataModel.currencyName
                total += (((items.Fi!!.toLong())/dataModel.currency)*items.Amount!!.toLong())
                itemClick.shippingData(sud,total)
            }
            itemBinding.priceTotal.text = addNumberSeparator(((items.Fi!!.toDouble())/dataModel.currency)*items.Amount!!.toInt()) +" "+ dataModel.currencyName
            itemBinding.numberBtn.text = items.PackAmount!! +" "+ items.secondUnitName + " معادل " + items.Amount!! +" "+ items.UName
            Picasso.get().load(imgUrl+"${items.GoodSn}_1.jpg").error(com.example.starfood.R.drawable.star).into(itemBinding.cartImg)
            if (items.Price3!="" && items.Price4!=""){
                sud += (((items.Price4!!.toLong())/dataModel.currency - (items.Price3!!.toLong())/dataModel.currency))*items.Amount!!.toLong()
                itemClick.shippingData(sud,total)
            }
            if (items.changedPrice == "1"){
                changedItemList.add(items.GoodName!!)
                itemClick.changedPrices(true,items.SnHDS!!,changedItemList)
            }
            itemBinding.delete.setOnClickListener {
                val customDialog = AlertDialog.Builder(context, 0).create()
                val dialogBinding: CartDeleteAlertBinding =
                    CartDeleteAlertBinding.inflate(LayoutInflater.from(context))
                customDialog.apply {
                    setView(dialogBinding.root)
                    setCancelable(false) }.show()
                dialogBinding.ok.setOnClickListener {
                        itemClick.deleteFromCart(items.SnOrderBYS!!, items)
                        product.removeAt(position)
                        notifyItemRemoved(position)
                        notifyItemRangeChanged(position, product.size)
                        if (items.Fi != "" && items.Price4 != "") {
                            sud -= (((items.Price4!!.toLong()) / dataModel.currency - (items.Price3!!.toLong()) / dataModel.currency)) * items.Amount!!.toLong()
                            total -= (((items.Fi!!.toLong()) / dataModel.currency) * items.Amount!!.toLong())
                            itemClick.shippingData(sud, total)
                        }
                    customDialog.dismiss()
                }
                dialogBinding.cancel.setOnClickListener {
                    customDialog.dismiss()
                }
            }
            itemBinding.numberBtn.setOnClickListener{
                var dialog = SelectAmountDialog(
                    this@CartAdapter,
                    items.GoodSn!!,
                    items.SecondUnitAmount,
                    items.UName,
                    items.secondUnitName,
                    items.Amount,
                    items.AmountExist!!
                )
                dialog.show(fragment.requireFragmentManager(),null)
                itemClick.itemClick(this)
                kalaId=items.GoodSn!!.toLong()
                orderBys =items.SnOrderBYS!!.toLong()
                orderItem=items
            }
        }
        fun setAmount(totalItem: String,uName: String,secondUnit: String,number: Int){
            itemBinding.numberBtn.text = "$number $secondUnit معادل $totalItem $uName"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        val itemBinding = CartAdapterBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(product[position],position)
    }

    override fun getItemCount(): Int {
        return product.size
    }

    interface ItemClick{
        fun deleteFromCart(snOrder: String, items: CartOrder)
        fun shippingData(profit:Long,total:Long)
        fun updatePurchase(
            orderBYS: Long,
            amountUnit: Long,
            kalaId: Long,
            orderItem: CartOrder,
            number: Int
        )
        fun itemClick(holder: CartAdapter.ViewHolder)
        fun getItem(totalItem: String,uName:String,secondUnit:String,number:Int)
        fun changedPrices(isChanged: Boolean, snHDS: String, changedItemList: ArrayList<String>)
    }
    override fun onAddAmount(
        totalItem: String,
        uName: String?,
        secondUnit: String?,
        number: Int,
        amountUnit: String
    )
    {
        itemClick.updatePurchase(orderBys,totalItem.toLong(),kalaId,orderItem,number)
        itemClick.getItem(totalItem,uName!!,secondUnit!!,number)
    }
}