package com.example.starfood.ui.profile

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import com.example.starfood.R
import com.example.starfood.databinding.ActivityFactorProfileWebViewBinding
import com.example.starfood.databinding.ActivityWebViewShippingBinding
import com.example.starfood.ui.cart.OnlineSuccessPayResultActivity
import com.example.starfood.ui.cart.WebViewShippingViewModel
import com.google.gson.Gson
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class FactorProfileWebViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFactorProfileWebViewBinding
    private val viewModel : WebViewShippingViewModel by viewModel()
    private val sharedPreferences : SharedPreferences by inject()
    override fun onCreate(savedInstanceState:Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivityFactorProfileWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val url = intent.getStringExtra("myUrl")
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.loadUrl("${url!!}")
        binding.webView.webViewClient = object : WebViewClient(){
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ):Boolean{
                val uri = Uri.parse(request?.url.toString())
                if(uri.getQueryParameter("status")=="cancel" || uri.getQueryParameter("status")=="expire"){
                    startActivity(Intent(this@FactorProfileWebViewActivity,FactorViewActivity::class.java))
                    finish()
                    return false
                }else {
                    if(!request?.url.toString().isNullOrEmpty()){
                        val tref = "tref"
                        val iN = uri.getQueryParameter("invoiceId")
                        val iD = uri.getQueryParameter("referenceNumber")
                        viewModel.successFactorPaymentResult(
                            sharedPreferences.getString("factorViewAllMoney", "")!!,
                            sharedPreferences.getString("factorSn","")!!,
                            tref!!,
                            iN!!,
                            iD!!
                        )
                        viewModel.successResultLiveData.observe(this@FactorProfileWebViewActivity){
                            if (it.result == "OK") {
                                Toast.makeText(
                                    this@FactorProfileWebViewActivity,
                                    it.result + "پرداخت شما موفقانه انجام شد.",
                                    Toast.LENGTH_LONG
                                ).show()
                                val intent = Intent(
                                    this@FactorProfileWebViewActivity,
                                    OnlineSuccessPayResultActivity::class.java
                                )
                                val gson = Gson()
                                val serializedObject = gson.toJson(it)
                                intent.putExtra("factor", serializedObject)
                                startActivity(intent)
                                finish()
                            } else {
                                Toast.makeText(
                                    this@FactorProfileWebViewActivity,
                                    it.result + "ارتباط شما با درگاه بانکی برقرار نشد دوباره تلاش کنید",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                    }
                    return true
                }
            }
        }
    }
}