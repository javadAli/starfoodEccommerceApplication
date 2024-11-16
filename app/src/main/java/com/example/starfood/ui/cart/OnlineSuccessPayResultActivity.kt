package com.example.starfood.ui.cart

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.starfood.common.StarFoodActivity
import com.example.starfood.databinding.ActivityOnlineSuccessPayResultBinding
import com.example.starfood.datamodel.shipping.SuccessPayOnlineDataModel
import com.example.starfood.ui.MainActivity
import com.google.gson.Gson
import org.koin.androidx.viewmodel.ext.android.viewModel

class OnlineSuccessPayResultActivity : StarFoodActivity(){
    private val viewModel:CartViewModel by viewModel()
    private lateinit var binding:ActivityOnlineSuccessPayResultBinding
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivityOnlineSuccessPayResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val serializedObject = intent.getStringExtra("factor")
        val factor = Gson().fromJson(serializedObject, SuccessPayOnlineDataModel::class.java)
        binding.profit.text = factor.profit.toString() +" تومان "
        binding.numFac2.text = factor.factorNo.toString()
        binding.backBtn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        if (factor!=null){
            val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            binding.rcv.layoutManager = layoutManager
            binding.rcv.adapter = OnlineFactorAdapter(factor.factorBYS)
            viewModel.clearLocalList()
        }
    }
}