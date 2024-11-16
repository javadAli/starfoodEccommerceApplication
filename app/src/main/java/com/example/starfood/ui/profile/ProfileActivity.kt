package com.example.starfood.ui.profile

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.starfood.common.StarFoodActivity
import com.example.starfood.databinding.ActivityProfileBinding
import com.example.starfood.datamodel.repository.userlogin.TokenContainer
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileActivity : StarFoodActivity() {
    private lateinit var binding:ActivityProfileBinding
    private val viewModel:ProfileViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.firstFactorRecycler.setHasFixedSize(false)
        binding.firstFactorRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        viewModel.getProfile(TokenContainer.psn!!)
        viewModel.profileLiveData.observe(this){
            binding.personNameTxt.text =it.profile.Name.trim()
            binding.mobileNumberTxt.text = it.profile.PhoneStr.trim()
            if (!it.factors.isNullOrEmpty()){
                binding.firstFactorRecycler.adapter = ProfileFactorAdapter(this,it.factors)
            }
//            if (!it.orders.isNullOrEmpty()){
//                binding.firstFactorRecycler.adapter = ProfileOrderAdapter(it.orders)
//            }
        }
    }
}