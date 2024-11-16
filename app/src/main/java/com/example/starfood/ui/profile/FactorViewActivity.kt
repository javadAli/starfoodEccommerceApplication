package com.example.starfood.ui.profile

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.starfood.R
import com.example.starfood.common.StarFoodActivity
import com.example.starfood.common.addNumberSeparator
import com.example.starfood.databinding.ActivityFactorViewBinding
import com.example.starfood.datamodel.profile.Factor
import com.example.starfood.datamodel.profile.factorview.FactorViewDataModel
import com.example.starfood.ui.cart.WebViewShippingActivity
import com.google.gson.Gson
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class FactorViewActivity : StarFoodActivity() {
    lateinit var binding:ActivityFactorViewBinding
    val viewModel:FactorViewModel by viewModel()
    private val sharedPreferences : SharedPreferences by inject()
    var allMoney = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFactorViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var intent = intent.getStringExtra("factor")
        val factor = Gson().fromJson(intent, Factor::class.java)
        viewModel.getFactors(factor.SerialNoHDS)
        viewModel.factorViewLiveData.observe(this){
            binding.numberProductTxt.text = it.factorBYS.size.toString()
            binding.rcv.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
            binding.rcv.adapter = FactorViewAdapter(it.factorBYS)
            if(it.currencyName.split("_")[1]=="Yes"){
                binding.payment.visibility=View.GONE
            }
            for (i in it.factorBYS){
                allMoney += i.Fi.split(".")[0].toInt() * i.Amount.split(".")[0].toInt()
            }
            binding.totalTxt.text= (addNumberSeparator( allMoney.toDouble()/10))+"تومان "
            binding.allMoneyTxt.text = (addNumberSeparator( allMoney.toDouble()/10))+"تومان "
            sharedPreferences.edit().apply{
                putString("factorViewAllMoney",allMoney.toString())
                putString("factorSn",factor.SerialNoHDS)
            }.apply()
        }
        binding.backBtn.setOnClickListener{
            startActivity(Intent(this,ProfileActivity::class.java))
        }
        binding.payment.setOnClickListener{
            viewModel.getFactorPayment(allMoney.toString())
            viewModel.paymentLiveData.observe(this){
                val intent = Intent(this, FactorProfileWebViewActivity::class.java)
                intent.putExtra("myUrl",it)
                startActivity(intent)
            }
        }
    }
}