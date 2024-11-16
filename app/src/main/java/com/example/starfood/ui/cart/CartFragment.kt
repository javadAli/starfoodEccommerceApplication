package com.example.starfood.ui.cart

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.starfood.common.StarFoodFragment
import com.example.starfood.common.addNumberSeparator
import com.example.starfood.common.networkMonitoring.NetworkStateHolder
import com.example.starfood.dao.ProductLocaleSaved
import com.example.starfood.databinding.FragmentCartBinding
import com.example.starfood.datamodel.cartitem.CartDataModel
import com.example.starfood.datamodel.cartitem.CartOrder
import com.example.starfood.datamodel.detailcategorydatamodel.KalaSubGroup
import com.example.starfood.datamodel.repository.userlogin.TokenContainer
import com.google.gson.Gson
import org.koin.androidx.viewmodel.ext.android.viewModel

class CartFragment:StarFoodFragment(), CartAdapter.ItemClick,ChangedItemPriceDialog.ClickListenerChanged{
    private lateinit var binding:FragmentCartBinding
    private val viewModel:CartViewModel by viewModel()
    private var profit:Long = 0
    private var total:Long = 0
    private lateinit var cartDataModel:CartDataModel
    private lateinit var holder: CartAdapter.ViewHolder
    private var isPriceChanged = false
    private lateinit var intervalBetweenBuys : String
    private lateinit var minSalePriceFactor : String
    private var snHDS = ""
    private lateinit var updateItemsList:ArrayList<String>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        binding = FragmentCartBinding.inflate(layoutInflater,container,false)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            showExitDialog()
        }
        return binding.root
    }

    private fun showExitDialog() {
        val builder = activity?.let { AlertDialog.Builder(it) }
        if (builder != null) {
            builder.setTitle("بستن")
                .setMessage("می خواهید برنامه را ببندید؟")
                .setPositiveButton("بله") {  _, _ -> requireActivity().finish()
                    System.exit(0)

                }.setNegativeButton("خیر") { dialog: DialogInterface, _: Int ->
                    dialog.dismiss()
                }.show()
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false )
        binding.cartRecycler.layoutManager = layoutManager
        getCartList()
        if(!NetworkStateHolder.isConnected){
            binding.continueBtnText.visibility=View.GONE
            binding.continueOffline.visibility=View.VISIBLE
        }else{
            binding.continueBuying.visibility=View.VISIBLE
            binding.continueOffline.visibility=View.GONE
        }
        binding.continueBuying.setOnClickListener{
            goToSubmitPurchaseInfo()
        }

    }
    private fun goToSubmitPurchaseInfo(){
        if (!isPriceChanged && intervalBetweenBuys.toInt()<=12 || !isPriceChanged && intervalBetweenBuys.toInt()>12 && minSalePriceFactor.toInt()<=total){
            val intent = Intent(activity, ShippingActivity::class.java)
            intent.putExtra("profit",profit)
            intent.putExtra("total",total)
            intent.putExtra("cartList",Gson().toJson(cartDataModel))
            startActivity(intent)
        }else if (isPriceChanged){
            var dialog = ChangedItemPriceDialog(this,updateItemsList)
            dialog.show(requireActivity().supportFragmentManager,null)
        }else if (intervalBetweenBuys.toInt()>12 && minSalePriceFactor.toInt()>total){
            Toast.makeText(requireContext(),"حداقل سفارش شما باید ${minSalePriceFactor}باشد",Toast.LENGTH_LONG).show()
        }
    }
    private fun getCartList(){
        viewModel.getCartList(TokenContainer.psn!!)
        viewModel.cartLiveData.observe(viewLifecycleOwner){
            cartDataModel = it
            this.intervalBetweenBuys = it.intervalBetweenBuys
            this.minSalePriceFactor = it.minSalePriceFactor
            binding.cartRecycler.adapter = CartAdapter(requireContext(),this, it.orders as ArrayList<CartOrder>,it,this)
        }
    }
    override fun deleteFromCart(snOrder: String, items: CartOrder) {
        if (NetworkStateHolder.isConnected){
            viewModel.deleteFromList(snOrder)
            viewModel.deleteCartFromLocal(items.GoodSn)
        }else{
            viewModel.deleteCartFromLocal(items.GoodSn)
        }
    }
    override fun shippingData(profit: Long, total: Long){
       this.profit = profit
        this.total = total
        var amount = addNumberSeparator(profit.toDouble())
        binding.profit.visibility = View.VISIBLE
        binding.profit.text = " سود شما از این خرید$amount" +" "+ cartDataModel.currencyName
        binding.paymentMoney.text = addNumberSeparator(total.toDouble()) +" "+ cartDataModel.currencyName
    }
    private fun saveLocaleData(amountUnit: Long, kalaId: Long, favourite:Boolean, request:Boolean, cancel:Boolean, buy:Boolean, number: Int,isFirst:Boolean,secondUnitAmount:Int){
        val product = ProductLocaleSaved(id = kalaId +number,goodSn= kalaId, requested = request, cancelRequested = cancel, favourite = favourite, isBuy = buy, amountBuy = amountUnit.toString(),"",isFirst)
        viewModel.addLocaleChangesToTable(product)
        viewModel.changeProductToSold(kalaId.toInt(), amountUnit.toInt(), amountUnit.toInt()/secondUnitAmount)
    }
    private fun saveLocalePurchase(amountUnit: Long, kalaId: Long, kalaItem: KalaSubGroup, number: Int, isFirst: Boolean,secondUnitAmount: Int) {
        saveLocaleData(amountUnit,kalaId,false,request = false,cancel = false,buy = true,1,isFirst,secondUnitAmount)
    }
    override fun updatePurchase(orderBYS: Long, amountUnit: Long, kalaId: Long, orderItem: CartOrder, number: Int){
        if (NetworkStateHolder.isConnected){
            viewModel.updatePurchaseRegistration(orderBYS,amountUnit,kalaId)
        }else{
            val orderItemChanged=KalaSubGroup(orderItem.Amount,orderItem.AmountExist,"5",orderItem.GoodName,orderItem.GoodSn,orderItem.PackAmount,orderItem.Price3,orderItem.Price4,"0",orderItem.SnOrderBYS,orderItem.UName,"0","0","Yes","0","NO","0","0",orderItem.maxSale,"0","0","0",orderItem.secondUnitName,orderItem.SecondUnitAmount)
            saveLocalePurchase(amountUnit,kalaId,orderItemChanged,number,false, secondUnitAmount = 40)
        }
        viewModel.purchaseLiveData.observe(this){
            if(!it.isNullOrEmpty()){
                var aadapter =  CartAdapter(requireContext(),this, ArrayList(),cartDataModel,this)
                aadapter.notifyDataSetChanged()
                getCartList()
            }
        }
    }
    override fun itemClick(holder: CartAdapter.ViewHolder){
        this.holder = holder
    }
    override fun getItem(totalItem: String, uName: String, secondUnit: String, number: Int) {
        holder.setAmount(totalItem,uName,secondUnit,number)
    }
    override fun changedPrices(isChanged: Boolean, snHDS: String, changedItemList: ArrayList<String>) {
       isPriceChanged =isChanged
        this.snHDS = snHDS
        updateItemsList = changedItemList
    }
    override fun applyClick(isContinue: Boolean){
        isPriceChanged = false
        viewModel.updateCart(snHDS)
        var cartAdapter =  CartAdapter(requireContext(),this, ArrayList(),cartDataModel,this)
        getCartList()
        goToSubmitPurchaseInfo()
        cartAdapter.notifyDataSetChanged()
    }
}