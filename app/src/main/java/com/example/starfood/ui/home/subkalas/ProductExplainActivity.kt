package com.example.starfood.ui.home.subkalas
import android.graphics.Paint
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.starfood.R
import com.example.starfood.common.StarFoodActivity
import com.example.starfood.common.addNumberSeparator
import com.example.starfood.common.networkMonitoring.NetworkStateHolder
import com.example.starfood.dao.ProductLocaleSaved
import com.example.starfood.databinding.ActivityProductExplainBinding
import com.example.starfood.datamodel.productexplaindatamodel.AssameKalaX
import com.example.starfood.datamodel.productexplaindatamodel.ProductExplainDataModel
import com.example.starfood.datamodel.repository.userlogin.TokenContainer
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel
class ProductExplainActivity : StarFoodActivity(), SelectAmountDialog.SelectAmountCallBack{
    private lateinit var binding:ActivityProductExplainBinding
    private val viewModel: ProductExplainViewModel by viewModel()
    private var similarProduct : List<AssameKalaX> ?=null
    private lateinit var firstGroupId:String
    private lateinit var goodSn:String
    private var firstSelect:Boolean = false
    private var updateSelect:Boolean = false
    private var packAmount:Int = 0
    var orderBYS = ""
    var imgUrl="https://starfoods.ir/resources/assets/images/kala/"
    private lateinit var productExplainDataModel: ProductExplainDataModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductExplainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //viewModel.progressLiveData.observe(this) { setProgressIndicator(it) }
        firstGroupId = intent.getStringExtra("firstGroupId")!!
        goodSn = intent.getStringExtra("goodSn")!!
        if(NetworkStateHolder.isConnected) {
            viewModel.getExplainProduct(firstGroupId, TokenContainer.psn!!, goodSn)
            viewModel.productExplainInfo.observe(this){
                productExplainDataModel = it
                Picasso.get().load(imgUrl+ it.GoodSn +"_1.jpg").error(R.drawable.starfood).into(binding.productIv)
                similarProduct = it.assameKala
                binding.codeTxtView.text = it.GoodCde
                binding.mainGroupTxtView.text = it.groupName
                binding.nameProduct.text = it.GoodName
                binding.description.text = it.descKala
                if(it.AmountExist!!.toInt()>0) {
                    binding.profitPercent.text =it.activeTakhfifPercent+"%" + " تخفیف "
                    if (it.bought == "Yes") {
                        binding.selectNumberLayout.visibility = View.GONE
                        binding.setAmountText.visibility = View.VISIBLE
                        binding.setAmountText.text =
                            it.PackAmount.toString()+" "+it.secondUnit+" معادل "+it.Amount +it.UNAME
                    }else{
                        binding.selectNumberLayout.visibility = View.VISIBLE
                        binding.setAmountText.visibility = View.GONE
                    }
                    if(!it.Price4.isNullOrEmpty() && it.Price4 != "0" && it.Price4 != "") {
                        binding.previousPriceTv.text =
                        addNumberSeparator(it.Price4!!.toDouble()/10) + " تومان"
                        binding.previousPriceTv.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                    }
                    if(!it.Price3.isNullOrEmpty() && it.Price3 != "0" && it.Price3 != "") {
                        binding.currentPriceTv.text =
                        addNumberSeparator(it.Price3!!.toDouble()/10) + " تومان"
                    }
                    binding.selectNumberLayout.setOnClickListener{
                        firstSelect = true
                        updateSelect = false
                        var dialog = SelectAmountDialog(
                        this@ProductExplainActivity,
                            goodSn,
                            productExplainDataModel.SecondUnitAmount,
                            productExplainDataModel.UNAME,
                            productExplainDataModel.secondUnit,
                            productExplainDataModel.maxSale,
                            productExplainDataModel.AmountExist!!
                        )
                        dialog.show(supportFragmentManager,null)
                    }
                    binding.setAmountText.setOnClickListener{
                        firstSelect = false
                        updateSelect = true
                        var dialog = SelectAmountDialog(
                            this@ProductExplainActivity,
                            goodSn,
                            productExplainDataModel.SecondUnitAmount,
                            productExplainDataModel.UNAME,
                            productExplainDataModel.secondUnit,
                            productExplainDataModel.maxSale,
                            productExplainDataModel.AmountExist!!
                        )
                        dialog.show(supportFragmentManager,null)
                    }
                }else{
                    binding.unavailableLayout.visibility=View.VISIBLE
                    binding.selectNumberLayout.visibility=View.GONE
                }
                if(!it.assameKala!!.isNullOrEmpty()){
                    binding.similarProductRecycler.layoutManager = LinearLayoutManager(this)
                    binding.similarProductRecycler.adapter = SimilarProductAdapter(this,it.assameKala!!)
                }
                if(it.favorite=="YES"){
                    binding.likeBtn.visibility = View.VISIBLE
                    binding.favoriteBtn.visibility = View.GONE
                }else{
                    binding.likeBtn.visibility = View.GONE
                    binding.favoriteBtn.visibility = View.VISIBLE
                }
            }
        }else{
            viewModel.getExplainProductLocal(goodSn)
            viewModel.productExplainInfo.observe(this){
                productExplainDataModel = it
                Picasso.get().load(imgUrl+ it.GoodSn +"_1.jpg").error(R.drawable.starfood).into(binding.productIv)
                similarProduct = it.assameKala
                binding.codeTxtView.text = it.GoodCde
                binding.mainGroupTxtView.text = it.groupName
                binding.nameProduct.text = it.GoodName
                binding.description.text = it.descKala
                if(it.AmountExist!!.toInt()>0){
                    binding.profitPercent.text =it.activeTakhfifPercent+"%" + " تخفیف "
                    if (it.bought == "Yes") {
                        binding.selectNumberLayout.visibility = View.GONE
                        binding.setAmountText.visibility = View.VISIBLE
                        binding.setAmountText.text =it.PackAmount.toString() + it.secondUnit + " معادل " + it.Amount + " " + it.UNAME
                    }else{
                        binding.selectNumberLayout.visibility = View.VISIBLE
                        binding.setAmountText.visibility = View.GONE
                    }
                    if(!it.Price4.isNullOrEmpty() && it.Price4 != "0" && it.Price4 != "") {
                        binding.previousPriceTv.text = addNumberSeparator(it.Price4!!.toDouble()/10) + " تومان"
                        binding.previousPriceTv.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                    }
                    if(!it.Price3.isNullOrEmpty() && it.Price3 != "0" && it.Price3 != "") {
                        binding.currentPriceTv.text = addNumberSeparator(it.Price3!!.toDouble()/10) + " تومان"
                    }
                    binding.selectNumberLayout.setOnClickListener{
                        firstSelect = true
                        updateSelect = false
                        var dialog = SelectAmountDialog(
                            this@ProductExplainActivity,
                            goodSn,
                            productExplainDataModel.SecondUnitAmount,
                            productExplainDataModel.UNAME,
                            productExplainDataModel.secondUnit,
                            productExplainDataModel.maxSale,
                            productExplainDataModel.AmountExist!!
                        )
                        dialog.show(supportFragmentManager,null)
                    }
                    binding.setAmountText.setOnClickListener{
                        firstSelect = false
                        updateSelect = true
                        var dialog = SelectAmountDialog(
                            this@ProductExplainActivity,
                            goodSn,
                            productExplainDataModel.SecondUnitAmount,
                            productExplainDataModel.UNAME,
                            productExplainDataModel.secondUnit,
                            productExplainDataModel.maxSale,
                            productExplainDataModel.AmountExist!!
                        )
                        dialog.show(supportFragmentManager,null)
                    }
                }else{
                    binding.unavailableLayout.visibility=View.VISIBLE
                    binding.selectNumberLayout.visibility=View.GONE
                }
                if(!it.assameKala!!.isNullOrEmpty()){
                    binding.similarProductRecycler.layoutManager = LinearLayoutManager(this)
                    binding.similarProductRecycler.adapter = SimilarProductAdapter(this,it.assameKala!!)
                }
                if(it.favorite=="YES"){
                    binding.likeBtn.visibility = View.VISIBLE
                    binding.favoriteBtn.visibility = View.GONE
                }else{
                    binding.likeBtn.visibility = View.GONE
                    binding.favoriteBtn.visibility = View.VISIBLE
                }
            }
        }
        binding.mainGroupTxtView.setOnClickListener{
            onBackPressed()
        }
        binding.favoriteBtn.setOnClickListener{
            binding.likeBtn.visibility = View.VISIBLE
            binding.favoriteBtn.visibility = View.GONE
            productExplainDataModel.favorite = "No"
            if(NetworkStateHolder.isConnected){
                viewModel.submitFavourite(goodSn,TokenContainer.psn!!)
            }else{
                val product = ProductLocaleSaved(id = goodSn.toLong()+6,goodSn=goodSn.toLong(), requested = false, cancelRequested = false, favourite = false, isBuy = false, amountBuy = "","",true)
                viewModel.addLocaleChangesToTable(product)
            }
        }
        binding.likeBtn.setOnClickListener{
            productExplainDataModel.favorite = "Yes"
            binding.likeBtn.visibility = View.GONE
            binding.favoriteBtn.visibility = View.VISIBLE
            if(NetworkStateHolder.isConnected){
                viewModel.submitFavourite(goodSn,TokenContainer.psn!!)
            }else{
                val product = ProductLocaleSaved(id = goodSn.toLong()+6,goodSn=goodSn.toLong(), requested = false, cancelRequested = false, favourite = true, isBuy = false, amountBuy = "","",true)
                viewModel.addLocaleChangesToTable(product)
            }
        }
    }
    override fun onAddAmount(
        totalItem: String,
        uName: String?,
        secondUnit: String?,
        number: Int,
        amountUnit: String
    ) {
        binding.setAmountText.text = "$number$secondUnit معادل $totalItem $uName"
        binding.setAmountText.visibility = View.VISIBLE
        binding.selectNumberLayout.visibility = View.GONE
        packAmount = number
        productExplainDataModel.PackAmount = packAmount
        productExplainDataModel.bought = "Yes"
        productExplainDataModel.secondUnit = secondUnit!!
        productExplainDataModel.UNAME = uName!!
        productExplainDataModel.Amount = totalItem.toInt()
        if (firstSelect){
            if (NetworkStateHolder.isConnected){
                viewModel.purchaseRegistration(goodSn,totalItem)
                saveLocalePurchase(totalItem.toLong(),productExplainDataModel.GoodSn.toLong(),true,amountUnit.toInt())
            }else{
                val product = ProductLocaleSaved(id = goodSn.toLong()+1,goodSn=goodSn.toLong(), requested = false, cancelRequested = false, favourite = false, isBuy = true, amountBuy = totalItem,"",true)
                viewModel.addLocaleChangesToTable(product)
                saveLocalePurchase(totalItem.toLong(),productExplainDataModel.GoodSn.toLong(),true,amountUnit.toInt())
            }
        }else{
            if (NetworkStateHolder.isConnected){
                viewModel.updatePurchaseRegistration(orderBYS.toLong(),totalItem.toLong(),goodSn.toLong())
                viewModel.purchaseLiveData.observe(this){
                    orderBYS = it
                    productExplainDataModel.SnOrderBYS = it.toInt()
                }
            }else{
                val product = ProductLocaleSaved(id = goodSn.toLong()+1,goodSn=goodSn.toLong(), requested = false, cancelRequested = false, favourite = false, isBuy = true, amountBuy = totalItem,productExplainDataModel.SnOrderBYS.toString(),false)
                viewModel.addLocaleChangesToTable(product)
                saveLocalePurchase(totalItem.toLong(),productExplainDataModel.GoodSn.toLong(),true,amountUnit.toInt())
            }
        }
    }
    private fun saveLocalePurchase(amountUnit: Long, kalaId: Long, isFirst: Boolean, secondUnitAmount: Int) {
        saveLocaleData(amountUnit,kalaId,false,request = false,cancel = false,buy = true,1,isFirst,secondUnitAmount)
    }
    private fun saveLocaleData(amountUnit: Long, kalaId: Long, favourite:Boolean, request:Boolean, cancel:Boolean, buy:Boolean, number: Int,isFirst:Boolean,secondUnitAmount:Int){
        val product = ProductLocaleSaved(id = kalaId +number,goodSn= kalaId, requested = request, cancelRequested = cancel, favourite = favourite, isBuy = buy, amountBuy = amountUnit.toString(),"",isFirst)
        viewModel.addLocaleChangesToTable(product)
        viewModel.changeProductToSold(kalaId.toInt(), amountUnit.toInt(), amountUnit.toInt()/secondUnitAmount)
    }
}