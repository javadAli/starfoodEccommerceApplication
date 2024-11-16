package com.example.starfood.ui.login

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.starfood.common.IS_LOGIN
import com.example.starfood.common.ItemClickListener
import com.example.starfood.common.StarFoodActivity
import com.example.starfood.databinding.ActivityConfirmLoginBinding
import com.example.starfood.datamodel.logindatamodel.LoginResponse
import com.example.starfood.datamodel.repository.userlogin.TokenContainer
import com.example.starfood.ui.MainActivity
import com.google.gson.Gson
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
class ConfirmLoginActivity : StarFoodActivity() {
    private lateinit var binding: ActivityConfirmLoginBinding
    private var itemClickListener: ItemClickListener? = null
    var adapter: ConfirmLoginAdapter? = null
    private val viewModel:ConfirmLoginViewModel by viewModel()
    val sharedPreferences: SharedPreferences by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfirmLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val serializedObject = intent.getStringExtra("platformList")
        val platformList = Gson().fromJson(serializedObject,LoginResponse::class.java)
        val exitterToken=platformList.token;
        itemClickListener = object : ItemClickListener {
            override fun onClick(s: String) {
                binding.confirmRecycler.post(Runnable {
                    adapter?.notifyDataSetChanged()
                })
            }
        }
        if (platformList!=null){
            binding.confirmRecycler.setHasFixedSize(true)
            val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            binding.confirmRecycler.layoutManager = layoutManager
            adapter = ConfirmLoginAdapter(platformList.loginInfo!!, itemClickListener as ItemClickListener,this@ConfirmLoginActivity)
            binding.confirmRecycler.adapter = adapter
        }
        binding.continueBtn.setOnClickListener{
           viewModel.getConfirm(TokenContainer.psn!!, TokenContainer.token!!)
            viewModel.confirmLiveData.observe(this){
                TokenContainer.tokenUpdate(it.token)
                sharedPreferences.edit().apply {
                    putInt(IS_LOGIN, 1)
                    putString("token",it.token)
                }.apply()
                val gson = Gson()
                val serializedObj = gson.toJson(it)
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("platformList",serializedObj)
                startActivity(intent)
                finish()
            }
        }
        binding.cancelBtn.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}