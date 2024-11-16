package com.example.starfood.ui.home.subkalas

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.star.SubProductAdapter
import com.example.starfood.R
import com.example.starfood.common.StarFoodActivity
import com.example.starfood.common.networkMonitoring.NetworkStateHolder
import com.example.starfood.dao.ProductLocaleSaved
import com.example.starfood.databinding.ActivityHomeDetailBinding
import com.example.starfood.datamodel.categorydatamodel.CategoryDataModelItem
import com.example.starfood.datamodel.detailcategorydatamodel.SubProductCategoryDataModel
import com.example.starfood.datamodel.detailcategorydatamodel.KalaSubGroup
import com.example.starfood.datamodel.repository.userlogin.TokenContainer
import com.google.gson.Gson
import org.koin.androidx.viewmodel.ext.android.viewModel
class SubProductActivity : StarFoodActivity(),SubProductHeaderAdapter.AppendSubKalaListener {
    private lateinit var binding:ActivityHomeDetailBinding
    private val viewModel: SubProductViewModel by viewModel()
    private lateinit var holder: SubProductAdapter.ViewHolder
    private lateinit var objectOfKala: KalaSubGroup
    private lateinit var categorydatamodel: CategoryDataModelItem
    private lateinit var subProductDataModel: SubProductCategoryDataModel
    private lateinit var adapter: SubProductAdapter
    private var currentPage=1
    private var firstOrSecond=1
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivityHomeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val serializedObject = intent.getStringExtra("detailProduct")
        categorydatamodel = Gson().fromJson(serializedObject, CategoryDataModelItem::class.java)
        binding.verticalRecycler.layoutManager = GridLayoutManager(this, 2)
        loadItems(currentPage)
        binding.horizontalRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        viewModel.productDetailInfo.observe(this){ subProduct ->
            if(subProduct.listKala.isEmpty()){
                if(NetworkStateHolder.isConnected) {
                    findViewById<ImageView>(R.id.beforeImage).visibility = View.VISIBLE
                }else{
                    findViewById<ImageView>(R.id.beforeImage).visibility = View.GONE
                    findViewById<TextView>(R.id.textBackup).visibility=View.VISIBLE
                }
                findViewById<RecyclerView>(R.id.verticalRecycler).visibility=View.GONE
            }else{
                findViewById<TextView>(R.id.textBackup).visibility=View.GONE
                findViewById<ImageView>(R.id.beforeImage).visibility=View.GONE
                findViewById<RecyclerView>(R.id.verticalRecycler).visibility=View.VISIBLE
            }
            adapter=SubProductAdapter(this,subProduct.listKala.toMutableList(),subProduct.currencyName,subProduct.logoPos,subProduct.maxSale)
            binding.verticalRecycler.adapter=adapter
        }
        viewModel.getHeaderList(categorydatamodel.id)
        viewModel.productDetailHeaderInfo.observe(this){
            binding.horizontalRecycler.adapter = SubProductHeaderAdapter(it,this@SubProductActivity)
        }
        binding.verticalRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (firstOrSecond==1){
                    currentPage++
                    loadItems(currentPage)
                    firstOrSecond++
                }
            }
        })
    }
    private fun loadItems(page: Int) {
        Handler(Looper.getMainLooper()).postDelayed({
            viewModel.getSubProductList(categorydatamodel.id, TokenContainer.psn!!, page)
            viewModel.productDetailInfo.observe(this) { subProduct ->
                if (subProduct.listKala.isNotEmpty()) {
                    val startIndex = subProduct.listKala.size
                    adapter.addItems(subProduct.listKala)
                    adapter.notifyItemRangeInserted(startIndex, subProduct.listKala.size)
                }
            }
        }, 300)
    }
    fun submitReminder(customerId: String, productId: String, kalaItem: KalaSubGroup){
        viewModel.submitReminder(customerId,productId)
    }
    fun cancelReminder(psn: String, gsn: String, kalaItem: KalaSubGroup){
        if (NetworkStateHolder.isConnected){
            viewModel.cancelReminder(psn,gsn)
        }else{
            saveLocaleData(0,gsn.toLong(),false,true,false,false,2,false,kalaItem.SecondUnitAmount!!.toInt())
            val kalaList=ArrayList<KalaSubGroup>()
            var counter = 0
            for (i in subProductDataModel.listKala){
                ++counter
                if (i.GoodSn == kalaItem.GoodSn){
                    val subProductObject = KalaSubGroup(kalaItem.Amount,kalaItem.BoughtAmount.toString(),kalaItem.CompanyNo,kalaItem.GoodName,kalaItem.GoodSn,kalaItem.PackAmount.toString(),kalaItem.Price3,kalaItem.Price4, kalaItem.SnGoodPriceSale,kalaItem.SnOrderBYS,kalaItem.UName,kalaItem.activePishKharid,kalaItem.activeTakhfifPercent,kalaItem.bought,kalaItem.callOnSale,kalaItem.favorite,kalaItem.firstGroupId,
                        kalaItem.freeExistance,kalaItem.maxSale,kalaItem.overLine,"0",kalaItem.secondGroupId,kalaItem.secondUnit,kalaItem.SecondUnitAmount)
                    kalaList.add(subProductObject)
                }else{
                    kalaList.add(i)
                }
                if (counter>=subProductDataModel.listKala.size){
                    var subProductDataModelObj = SubProductCategoryDataModel(subProductDataModel.currency,subProductDataModel.currencyName,subProductDataModel.listGroups,kalaList.toList(),subProductDataModel.logoPos,subProductDataModel.maxSale,subProductDataModel.mainGrId)
                    viewModel.updateSubProductTable(subProductDataModelObj)
                }
            }
        }
    }
    fun favourite(goodSn: String, psn: String, kalaItem: KalaSubGroup, favouriteResult: String, isFavourite: Boolean){
        if (NetworkStateHolder.isConnected){
            viewModel.submitFavourite(goodSn,psn)
            viewModel.submitFavouriteLiveData.observe(this){
            }
        }else{
            saveLocaleData(0,goodSn.toLong(),isFavourite,false,false,false,6,false,kalaItem.SecondUnitAmount!!.toInt())
            val kalaList=ArrayList<KalaSubGroup>()
            var counter = 0
            for (i in subProductDataModel.listKala){
                ++counter
                if (i.GoodSn == kalaItem.GoodSn){
                    var subProductObject = KalaSubGroup(kalaItem.Amount,kalaItem.BoughtAmount.toString(),kalaItem.CompanyNo,kalaItem.GoodName,kalaItem.GoodSn,kalaItem.PackAmount.toString(),kalaItem.Price3,kalaItem.Price4,
                        kalaItem.SnGoodPriceSale,kalaItem.SnOrderBYS,kalaItem.UName,kalaItem.activePishKharid,kalaItem.activeTakhfifPercent,kalaItem.bought,kalaItem.callOnSale,favouriteResult,kalaItem.firstGroupId,
                        kalaItem.freeExistance,kalaItem.maxSale,kalaItem.overLine,kalaItem.requested,kalaItem.secondGroupId,kalaItem.secondUnit,kalaItem.SecondUnitAmount)
                    kalaList.add(subProductObject)
                }else{
                    kalaList.add(i)
                }
                if (counter>=subProductDataModel.listKala.size){
                    var subProductDataModelObj = SubProductCategoryDataModel(subProductDataModel.currency,subProductDataModel.currencyName,subProductDataModel.listGroups,kalaList.toList(),subProductDataModel.logoPos,subProductDataModel.maxSale,subProductDataModel.mainGrId)
                    viewModel.updateSubProductTable(subProductDataModelObj)
                }
            }
        }
    }
    fun itemClick(holder: SubProductAdapter.ViewHolder, objectOfKala: KalaSubGroup) {
        this.holder = holder
        this.objectOfKala= objectOfKala
    }
    fun getItem(totalItem: String, uName: String?, secondUnit: String?, number: Int) {
        holder.setAmount(totalItem,uName!!,secondUnit!!,number)
    }
    fun purchaseRegisteration(amountUnit: Long, kalaId: Long,kalaItem:KalaSubGroup,number:Int){
        if(NetworkStateHolder.isConnected){
            viewModel.purchaseRegistration(kalaId.toString(),amountUnit.toString())
            viewModel.purchaseLiveData.observe(this){
                objectOfKala.SnOrderBYS = it
            }
            saveLocalePurchase(amountUnit, kalaId,kalaItem,number,true,kalaItem.SecondUnitAmount!!.toInt())
        }else{
            saveLocalePurchase(amountUnit, kalaId,kalaItem,number,true,kalaItem.SecondUnitAmount!!.toInt())
        }
    }
    private fun saveLocalePurchase(amountUnit: Long, kalaId: Long, kalaItem: KalaSubGroup, number: Int, isFirst: Boolean,secondUnitAmount: Int) {
        saveLocaleData(amountUnit,kalaId,false,request = false,cancel = false,buy = true,1,isFirst,secondUnitAmount)
    }
    private fun saveLocaleData(amountUnit: Long, kalaId: Long, favourite:Boolean, request:Boolean, cancel:Boolean, buy:Boolean, number: Int,isFirst:Boolean,secondUnitAmount:Int){
        val product = ProductLocaleSaved(id = kalaId +number,goodSn= kalaId, requested = request, cancelRequested = cancel, favourite = favourite, isBuy = buy, amountBuy = amountUnit.toString(),"",isFirst)
        viewModel.addLocaleChangesToTable(product)
        viewModel.changeProductToSold(kalaId.toInt(), amountUnit.toInt(), amountUnit.toInt()/secondUnitAmount)
    }
    fun updatePurchase(orderBYS: Long, amountUnit: Long, kalaId: Long,kalaItem:KalaSubGroup,number:Int){
        if (NetworkStateHolder.isConnected){
            viewModel.updatePurchaseRegistration(orderBYS,amountUnit,kalaId)
            viewModel.purchaseLiveData.observe(this){
                objectOfKala.SnOrderBYS = it
            }
        }else{
            saveLocalePurchase(amountUnit, kalaId,kalaItem,number,false,kalaItem.SecondUnitAmount!!.toInt())
        }
    }
    override fun getSubKala(mainId: String, subKalaId: String){
        viewModel.appendSubProduct(mainId,subKalaId)
        viewModel.appendProductLiveData.observe(this){
            binding.verticalRecycler.adapter = SubProductAdapter(this,it.listKala.toMutableList(),it.currencyName,it.logoPos,it.maxSale)
        }
    }
}