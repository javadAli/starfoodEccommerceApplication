package com.example.starfood.ui.favourite

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.starfood.common.StarFoodActivity
import com.example.starfood.databinding.ActivityFavouriteBinding
import com.example.starfood.datamodel.amountofproduct.AmountProductDataModel
import com.example.starfood.datamodel.repository.userlogin.TokenContainer
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavouriteActivity : StarFoodActivity(), FavouriteAdapter.ItemClickListener{
    private lateinit var binding: ActivityFavouriteBinding
    private val viewModel:FavouriteViewModel by viewModel()
    private lateinit var holder: FavouriteAdapter.ViewHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavouriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getList()
        binding.swipeRefresh.setOnRefreshListener {
            binding.swipeRefresh.isRefreshing = true
            getList()
        }

    }

    fun getList(){
        val layoutManager2 = GridLayoutManager(this, 2)
        binding.verticalRecycler.layoutManager = layoutManager2
        viewModel.getFavouriteList( TokenContainer.psn!!)
        viewModel.favouriteLiveData.observe(this){
            binding.swipeRefresh.isRefreshing = false
            binding.verticalRecycler.adapter = FavouriteAdapter(this,it.favorits,it,this)
        }
    }
    fun submitReminder(customerId:String , productId:String){
        viewModel.submitReminder(customerId,productId)
        viewModel.submitReminderLiveData.observe(this) {

        }
    }
    fun cancelReminder(psn:String , gsn:String){
        viewModel.cancelReminder(psn,gsn)
        viewModel.cancelReminderLiveData.observe(this) {

        }
    }
    fun favourite(goodSn:String , psn:String){
        viewModel.submitFavourite(goodSn,psn)
        viewModel.submitFavouriteLiveData.observe(this) {
        }
    }
    fun purchaseRegistration(kalaId:String,amount:String){
        viewModel.purchaseRegistration(kalaId,amount)
        viewModel.purchaseLiveData.observe(this){
            if(!it.isNullOrEmpty()){

            }
        }
    }

    override fun itemClick(holder: FavouriteAdapter.ViewHolder) {
        this.holder = holder
    }

    override fun getItem(totalItem: String, uName: String, secondUnit: String, number: Int) {
        holder.setAmount(totalItem,uName,secondUnit,number)
    }
}