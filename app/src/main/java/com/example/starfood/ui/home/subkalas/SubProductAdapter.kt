package com.example.star

import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.starfood.common.addNumberSeparator
import com.example.starfood.databinding.DetailCategoryAdapterBinding
import com.example.starfood.datamodel.detailcategorydatamodel.KalaSubGroup
import com.example.starfood.datamodel.repository.userlogin.TokenContainer
import com.example.starfood.ui.home.subkalas.ProductExplainActivity
import com.example.starfood.ui.home.subkalas.SelectAmountDialog
import com.example.starfood.ui.home.subkalas.SubProductActivity
import com.squareup.picasso.Picasso
class SubProductAdapter(
    private val activity: SubProductActivity,
    private var product: MutableList<KalaSubGroup>,
    private val currencyName : String?,
    private val logoPos : String?,
    private var satMaxSale:String
):RecyclerView.Adapter<SubProductAdapter.ViewHolder>(), SelectAmountDialog.SelectAmountCallBack
{
    val imgUrl ="https://starfoods.ir/resources/assets/images/kala/"
    private lateinit var kalaId:String
    private  var total:Long=0
    private  var orderBYS:Long=0
    private lateinit var selectedKala:KalaSubGroup
    private var firstSelect:Boolean = false
    private var updateSelect:Boolean = false
    inner class ViewHolder(private val itemBinding: DetailCategoryAdapterBinding) : RecyclerView.ViewHolder(itemBinding.root){
        fun bind(items: KalaSubGroup) {
            itemBinding.title.text = items.GoodName!!.trim()
            Picasso.get().load(imgUrl+"${items.GoodSn}"+"_1.jpg").error(com.example.starfood.R.drawable.starfood).into(itemBinding.img)
            if(items.Amount?.toInt()!! >0){
                if (items.Price3?.toInt()!! >0){
                        itemBinding.profitPrice.text = addNumberSeparator(items.Price3!!.toDouble()/10)+" "+currencyName
                }
                if (items.Price4?.toInt()!! >0){
                    itemBinding.firstPrice.text = addNumberSeparator(items.Price4!!.toDouble()/10) +" "+currencyName
                    itemBinding.firstPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                }
                if (items.Price3?.toInt()!! >0 && items.Price4?.toInt()!! >0){
                    if (items.Price3?.toInt()!!  <  items.Price4?.toInt()!! ) {
                        itemBinding.profitIcon.visibility = View.VISIBLE
                        itemBinding.profitIcon.text ="%" + (((Integer.parseInt(items.Price4) - Integer.parseInt(items.Price3!!)) * 100) / (Integer.parseInt(
                                items.Price4!!
                            ))).toString() + " "
                    }else{
                        itemBinding.profitIcon.visibility = View.GONE
                    }
                }else{
                    itemBinding.profitIcon.visibility = View.GONE
                }
                itemBinding.selectNumberLayout.setOnClickListener{
                    kalaId = items.GoodSn
                    if(items.maxSale.toInt()!=-1){
                        satMaxSale= 43.toString()
                    }
                    var dialog = SelectAmountDialog(this@SubProductAdapter,items.GoodSn
                        ,items.SecondUnitAmount,items.UName,items.secondUnit,satMaxSale, items.Amount!!
                    )
                    dialog.show(activity.supportFragmentManager,null)
                    firstSelect = true
                    updateSelect = false
                    selectedKala= items
                    activity.itemClick(this,items)
                }
                itemBinding.setAmountText.setOnClickListener{
                    var dialog = SelectAmountDialog(
                        this@SubProductAdapter,
                        items.GoodSn,
                        items.SecondUnitAmount,
                        items.UName,
                        items.secondUnit,
                        satMaxSale,
                        items.Amount!!
                    )
                    dialog.show(activity.supportFragmentManager,null)
                    if (!items.SnOrderBYS.isNullOrEmpty()){
                        orderBYS = items.SnOrderBYS!!.toLong()
                    }
                    firstSelect = false
                    selectedKala= items
                    updateSelect = true
                    activity.itemClick(this,items)
                }
                itemBinding.profitPrice.visibility=View.VISIBLE;
                itemBinding.firstPrice.visibility=View.VISIBLE;
                if(items.bought=="Yes"){
                    itemBinding.setAmountText.text=items.PackAmount+" "+items.secondUnit+" معادل "+items.BoughtAmount +" "+items.UName
                    itemBinding.selectNumberLayout.visibility=View.GONE
                    itemBinding.setAmountText.visibility=View.VISIBLE
                }else{
                    itemBinding.selectNumberLayout.visibility=View.VISIBLE
                    itemBinding.setAmountText.visibility=View.GONE
                }
            }else{
                itemBinding.profitIcon.visibility=View.GONE;
                itemBinding.profitPrice.visibility=View.GONE;
                itemBinding.firstPrice.visibility=View.GONE;
                itemBinding.selectNumberLayout.visibility=View.GONE
                itemBinding.setAmountText.visibility=View.GONE
                itemBinding.unavailableLayout.visibility=View.VISIBLE
                if(items.requested!!.toInt()==1){
                    itemBinding.cancelRemindLayout.visibility=View.VISIBLE
                    itemBinding.remindLayout.visibility=View.GONE
                }else{
                    itemBinding.cancelRemindLayout.visibility=View.GONE
                    itemBinding.remindLayout.visibility=View.VISIBLE
                }
            }
            if(logoPos!!.toInt()==1){
                itemBinding.logoLeft.visibility=View.VISIBLE
                itemBinding.logoRight.visibility=View.GONE
            }else{
                itemBinding.logoLeft.visibility=View.GONE
                itemBinding.logoRight.visibility=View.VISIBLE
            }
            if(items.favorite == "NO"){
                itemBinding.likeBtn.visibility=View.GONE
                itemBinding.likeBorderBtn.visibility=View.VISIBLE
            }else{
                itemBinding.likeBtn.visibility=View.VISIBLE
                itemBinding.likeBorderBtn.visibility=View.GONE
            }
            itemBinding.img.setOnClickListener{
                val intent = Intent(activity, ProductExplainActivity::class.java)
                intent.putExtra("goodSn",items.GoodSn)
                intent.putExtra("firstGroupId",items.firstGroupId)
                activity.startActivity(intent)
            }
            itemBinding.cancelRemindLayout.setOnClickListener {
                activity.cancelReminder(TokenContainer.psn!!,items.GoodSn,items)
                itemBinding.cancelRemindLayout.visibility = View.GONE
                itemBinding.remindLayout.visibility=View.VISIBLE
            }
            itemBinding.remindLayout.setOnClickListener{
                activity.submitReminder(TokenContainer.psn!!,items.GoodSn,items)
                itemBinding.cancelRemindLayout.visibility = View.VISIBLE
                itemBinding.remindLayout.visibility=View.GONE
            }
            itemBinding.likeBtn.setOnClickListener {
                activity.favourite(items.GoodSn,TokenContainer.psn!!,items,"NO",false)
                itemBinding.likeBtn.visibility=View.GONE
                itemBinding.likeBorderBtn.visibility=View.VISIBLE
            }
            itemBinding.likeBorderBtn.setOnClickListener {
                activity.favourite(items.GoodSn,TokenContainer.psn!!,items,"Yes",true)
                itemBinding.likeBtn.visibility=View.VISIBLE
                itemBinding.likeBorderBtn.visibility=View.GONE
            }
        }
        fun setAmount(totalItem: String,uName: String,secondUnit: String,number: Int){
            itemBinding.setAmountText.visibility = View.VISIBLE
            itemBinding.selectNumberLayout.visibility = View.GONE
            itemBinding.setAmountText.text = "$number$secondUnit معادل $totalItem $uName"
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PrefetchViewPool.getPrefetchedItemBinding(com.example.starfood.R.layout.detail_category_adapter)
            ?: DetailCategoryAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = product[position]
        holder.bind(data)
    }
    override fun getItemCount(): Int {
        return product.size
    }
    fun addItems(newItems: List<KalaSubGroup>) {
        product.addAll(newItems)
    }
    override fun onAddAmount(
        totalItem: String,
        uName: String?,
        secondUnit: String?,
        number: Int,
        amountUnit: String
    ) {
        total = totalItem.toLong()
        if (firstSelect) {
            activity.purchaseRegisteration(total, kalaId.toLong(), selectedKala, number)
            activity.getItem(totalItem, uName, secondUnit, number)
        }else{
            activity.getItem(totalItem, uName, secondUnit, number)
            activity.updatePurchase(orderBYS, total, selectedKala.GoodSn.toLong(), selectedKala, number)
        }
    }
    object PrefetchViewPool {
        private val viewPool = mutableMapOf<Int, MutableList<DetailCategoryAdapterBinding>>()
        fun prefetchViews(context: Context, layoutId: Int, count: Int) {
            val bindings = mutableListOf<DetailCategoryAdapterBinding>()
            repeat(count) {
                val binding = DetailCategoryAdapterBinding.inflate(LayoutInflater.from(context), null, false)
                bindings.add(binding)
            }
            viewPool[layoutId] = bindings
        }
        fun getPrefetchedItemBinding(layoutId: Int): DetailCategoryAdapterBinding? {
            val bindings = viewPool[layoutId]
            return if (bindings != null && bindings.isNotEmpty()) {
                bindings.removeAt(0)
            } else {
                null
            }
        }
    }
}