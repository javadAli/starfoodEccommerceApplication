package com.example.starfood.ui.cart

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.starfood.common.StarFoodActivity
import com.example.starfood.databinding.ActivityFactorBinding
import com.example.starfood.datamodel.addfactor.AddFactorDataModel
import com.example.starfood.ui.MainActivity
import com.google.gson.Gson
import org.koin.androidx.viewmodel.ext.android.viewModel

class FactorActivity : StarFoodActivity() {
    private lateinit var binding:ActivityFactorBinding
    private val viewModel:CartViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFactorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val serializedObject = intent.getStringExtra("factor")
        val factor = Gson().fromJson(serializedObject, AddFactorDataModel::class.java)
        binding.numberFactor.text = factor.orderNo.toString()
        binding.numberFactor2.text = factor.orderNo.toString()
        binding.profitTxt.text = factor.profit
        binding.backBtn.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rcv.layoutManager = layoutManager
        binding.rcv.adapter = FactorAdapter(factor.orderBYS)
        viewModel.clearLocalList()
    }
}