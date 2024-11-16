package com.example.starfood.ui.cart

import android.R
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import com.example.starfood.common.StarFoodActivity
import com.example.starfood.common.addNumberSeparator
import com.example.starfood.common.networkMonitoring.NetworkStateHolder
import com.example.starfood.dao.CartFactorLocaleDataBase
import com.example.starfood.databinding.ActivityShippingBinding
import com.example.starfood.databinding.DatepickerLayoutBinding
import com.example.starfood.datamodel.addfactor.AddFactorDataModel
import com.example.starfood.datamodel.cartitem.CartDataModel
import com.example.starfood.datamodel.cartitem.CartOrder
import com.example.starfood.datamodel.repository.userlogin.TokenContainer
import com.example.starfood.datamodel.shipping.ShippingDataModel
import com.google.gson.Gson
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import saman.zamani.persiandate.PersianDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class ShippingActivity : StarFoodActivity(),ApplyDiscountDialogFragment.GetTakhfif{
    private lateinit var binding:ActivityShippingBinding
    private val viewModel:ShippingViewModel by viewModel()
    private lateinit var shippingDataModel : ShippingDataModel
    private val sharedPreferences :SharedPreferences by inject()
    private  var spinnerlist =ArrayList<String>()
    private var customerAddress : String? = null
    private var snPeopleAddress : String? = null
    private var walletProfit : Boolean = false
    private var codeProfit : Boolean = false
    private var recivedTime: String? = null
    private var fromData=""
    private var profit:Long = 0
    private var total:Long = 0
    private var takhfifCode:Int = 0
    private var takhfifWallet:Int = 0
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivityShippingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        profit = intent.getLongExtra("profit",0)
        total = intent.getLongExtra("total",0)
        val serializedObject = intent.getStringExtra("cartList")
        val cartList = Gson().fromJson(serializedObject, CartDataModel::class.java)
        viewModel.shippingData(TokenContainer.psn!!,total,profit)
        viewModel.shippingLiveData.observe(this){
            shippingDataModel = it
            if(it.addresses.size>0) {
                for (i in it.addresses) {
                    spinnerlist.add(i.AddressPeopel)
                }
            }else{
                spinnerlist.add(it.customer.peopeladdress)
            }
            val adapter = ArrayAdapter(this, R.layout.simple_spinner_item, spinnerlist)
            adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
            binding.addressSpinner.adapter = adapter
            binding.addressSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long){
                    if (spinnerlist.size>1)
                    {
                        val selectedItem =binding.addressSpinner.selectedItem.toString()
                        for ( i in it.addresses){
                            if (selectedItem == i.AddressPeopel)
                                snPeopleAddress = i.SnPeopelAddress
                        }
                        customerAddress = snPeopleAddress+"_"+selectedItem
                    }else{
                        customerAddress = "0_"+it.customer.peopeladdress
                    }
                }
                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }

            binding.date1.text = it.date1
            binding.date2.text = it.date2
            binding.userTxt.text = it.customer.Name
            if (it.setting.firstDayMoorningActive=="1")
            {
                binding.date1RadioButton.text = it.setting.moorningTimeContent
                binding.date1RadioButtonPm.text = it.setting.moorningTimeContent
            }
            else
            {
                binding.date1RadioButton.text = it.setting.afternoonTimeContent
                binding.date1RadioButtonPm.text = it.setting.afternoonTimeContent
            }

            if(it.setting.secondDayMoorningActive=="1")
            {
                binding.date1RadioButton.text = it.setting.moorningTimeContent
                binding.secondDateRadioButtonPm.text = it.setting.moorningTimeContent
            }
            else
            {
                binding.date1RadioButton.text = it.setting.afternoonTimeContent
                binding.secondDateRadioButtonPm.text = it.setting.afternoonTimeContent
            }
            if (it.setting.FavoriteDateAfternoonActive == "1" || it.setting.FavoriteDateMoorningActive == "1")
            {
                binding.ButtonOfCustomDate.setOnClickListener{
                   // binding.fastSendButton.isChecked = false
                    binding.online.isChecked = true
                    binding.online.isEnabled = true
                    binding.inPerson.isEnabled = false
                    binding.date2RadioGroup.isActivated = false
                    binding.date1RadioGroup.isActivated = false
                  //  binding.priceOfSendingText.visibility = View.GONE
                    showFromDatePickerDialog()
                }
            }
            //binding.takhfifcase.text = addNumberSeparator((it.takhfifCase/it.currency.toInt()).toDouble()) + it.currencyName
            binding.profit.text = addNumberSeparator((it.profit).toDouble()) + it.currencyName
            binding.total.text = addNumberSeparator((it.allMoney + it.profit).toDouble())+ it.currencyName
            binding.allMoney.text =  addNumberSeparator((it.allMoney).toDouble())+it.currencyName
//            binding.profitWalletSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
//                if (isChecked){
//                    if (!codeProfit){
//                        walletProfit = true
//                        takhfifWallet = it.takhfifCase/it.currency.toInt()
//                        binding.allMoney.text = addNumberSeparator((it.allMoney - it.takhfifCase/it.currency.toInt()).toDouble())
//                        total = (it.allMoney - it.takhfifCase/it.currency.toInt()).toLong()
//                    }
//                    else{
//                        Toast.makeText(this," شما میتوانید از کیف تخفیف یا از کد تخفیف استفاده کنید. ",Toast.LENGTH_LONG).show()
//
//                    }
//                }
//                else{
//                    binding.allMoney.text = addNumberSeparator((it.allMoney).toDouble())
//
//                }
//            }
        }

        binding.date1RadioButton.setOnCheckedChangeListener {  compoundButton, checked ->
          if (checked)
          {
              binding.secondDateRadioButton.isChecked = false
              binding.secondDateRadioButtonPm.isChecked = false
              binding.date1RadioButton.isChecked = true
              binding.inPerson.isEnabled = true
              binding.online.isEnabled = true
             // binding.fastSendButton.isEnabled = true
              //binding.fastSendButton.isChecked = false
              binding.date1RadioButton.isEnabled = true
              binding.ButtonOfCustomDate.text = ""
              //binding.priceOfSendingText.visibility = View.GONE
              recivedTime = "1,"+shippingDataModel.tomorrowDate
          }
        }
        binding.date1RadioButtonPm.setOnCheckedChangeListener {  compoundButton, checked ->
          if (checked)
          {
              binding.secondDateRadioButton.isChecked = false
              binding.secondDateRadioButtonPm.isChecked = false
              binding.date1RadioButtonPm.isChecked = true
              binding.online.isEnabled = true
              binding.inPerson.isEnabled = true
             // binding.fastSendButton.isChecked = false
              binding.ButtonOfCustomDate.text = ""
             // binding.priceOfSendingText.visibility = View.GONE
              recivedTime = "2,"+shippingDataModel.tomorrowDate
          }
        }
        binding.secondDateRadioButton.setOnCheckedChangeListener { compoundButton, checked ->
            if (checked)
            {
                binding.secondDateRadioButton.isChecked = true
                binding.secondDateRadioButtonPm.isChecked = false
                binding.inPerson.isEnabled = true
               // binding.fastSendButton.isChecked = false
                binding.online.isEnabled = true
                binding.ButtonOfCustomDate.text = ""
                binding.date1RadioButton.isChecked = false
                binding.date1RadioButtonPm.isChecked = false
               // binding.priceOfSendingText.visibility = View.GONE
                recivedTime = "1,"+shippingDataModel.afterTomorrowDate
            }
        }
        binding.secondDateRadioButtonPm.setOnCheckedChangeListener { compoundButton, checked ->
            if (checked)
            {
                binding.secondDateRadioButtonPm.isChecked = true
                binding.inPerson.isEnabled = true
                //binding.fastSendButton.isChecked = false
                binding.online.isEnabled = true
                binding.ButtonOfCustomDate.text = ""
                binding.date1RadioButton.isChecked = false
                binding.date1RadioButtonPm.isChecked = false
                //binding.priceOfSendingText.visibility = View.GONE
                recivedTime = "2,"+shippingDataModel.afterTomorrowDate
            }
        }
//        binding.fastSendButton.setOnCheckedChangeListener { compoundButton, checked ->
//          if (checked)
//          {
//              binding.online.isActivated = true
//              binding.online.isEnabled = true
//              binding.online.isChecked = true
//              var currentDateTime = LocalDateTime.now()
//              val formatter =DateTimeFormatter.ofPattern("yyyy-mm-dd'T'HH:mm:ss.SSSSSS")
//              val formatedDateTime = currentDateTime.format(formatter)
//              binding.priceOfSendingText.visibility = View.VISIBLE
//              binding.inPerson.isEnabled = false
//              binding.secondDateRadioButton.isChecked = false
//              binding.secondDateRadioButtonPm.isChecked = false
//              binding.date1RadioButton.isChecked = false
//              binding.date1RadioButtonPm.isChecked = false
//              recivedTime = "0,$formatedDateTime"
//
//          }
//        }
        binding.sendFactor.setOnClickListener {
            if (customerAddress!=null && recivedTime!=null){
            //if (NetworkStateHolder.isConnected){
                viewModel.addFactor(TokenContainer.psn!!,total,customerAddress!!,recivedTime!!,profit)
                viewModel.addFactorLiveData.observe(this){
                    val intent = Intent(this, FactorActivity::class.java)
                    val gson = Gson()
                    val serializedObject = gson.toJson(it)
                    intent.putExtra("factor",serializedObject)
                    startActivity(intent)
                }
//            }else{
//                val cartLocaleObject = CartFactorLocaleDataBase(1,total.toString(),customerAddress!!,recivedTime!!,profit.toString())
//                viewModel.saveToLocaleFactor(cartLocaleObject)
//                val intent = Intent(this, OfflineCartFactorActivity::class.java)
//                val gson = Gson()
//                val serializedObject = gson.toJson(cartList)
//                intent.putExtra("factor",serializedObject)
//                startActivity(intent)
//                finish()
//            }
        }else{
                Toast.makeText(this,"لطفا اطلاعات را به درستی پر کنید",Toast.LENGTH_LONG).show()
            }

        }
//        binding.codeTakhfifBtn.setOnClickListener {
//            var dialog = ApplyDiscountDialogFragment(this)
//            dialog.show(supportFragmentManager,null)
//        }
        binding.inPerson.setOnCheckedChangeListener{  compoundButton, checked ->
            if (checked){
                binding.sendFactor.visibility = View.VISIBLE
                binding.paymentAndSendFactor.visibility = View.GONE
            }
        }
        binding.online.setOnCheckedChangeListener{  compoundButton, checked ->
            if (checked){
                binding.sendFactor.visibility = View.GONE
                binding.paymentAndSendFactor.visibility = View.VISIBLE
            }
        }
        binding.paymentAndSendFactor.setOnClickListener{
            if (customerAddress!=null && recivedTime!=null){
                 viewModel.getPaymentForm(TokenContainer.psn!!,total)
                 viewModel.paymentFormLiveData.observe(this){
                     sharedPreferences.edit().apply{
                         putString("receviedAddress",customerAddress)
                         putString("recivedTime",recivedTime)
                         putString("allMoney",total.toString())
                         putString("takhfif",takhfifWallet.toString())
                         putString("takhfifCode",takhfifCode.toString())
                     }.apply()
                     val intent = Intent(this,WebViewShippingActivity::class.java)
                     intent.putExtra("myUrl",it.toString())
                     startActivity(intent)
                 }
            }else{
                Toast.makeText(this,"لطفا اطلاعات را به درستی پر کنید",Toast.LENGTH_LONG).show()
            }
        }
    }
    private fun showFromDatePickerDialog(){
        val customDialog = AlertDialog.Builder(this, 0).create()
        val dialogBinding: DatepickerLayoutBinding =
            DatepickerLayoutBinding.inflate(layoutInflater)
        customDialog.apply {
            setView(dialogBinding.root)
            setCancelable(false) }.show()
        var day=""
        var month=""
        val pdate = PersianDate()
        dialogBinding.datePicker.setDate(pdate.shYear,pdate.shMonth, pdate.shDay)
        dialogBinding.submitButton.setOnClickListener {
            binding.ButtonOfCustomDate.text=dialogBinding.datePicker.getFormattedDate()
            if (dialogBinding.datePicker.getSelectedDay().toString().length==2)
            {
                day=dialogBinding.datePicker.getSelectedDay().toString()
            }
            else
                day="0${dialogBinding.datePicker.getSelectedDay()}"
            if (dialogBinding.datePicker.getSelectedMonth().toString().length==2)
            {
                month =dialogBinding.datePicker.getSelectedMonth().toString()
            }
            else{
                month = "0${dialogBinding.datePicker.getSelectedMonth()}"
            }
            var year = dialogBinding.datePicker.getSelectedYear().toString()
            fromData="${year}/${month}/${day}"
            customDialog.dismiss()
        }
    }

    override fun applyTakhfifMoney(disCountMoney: Int){
      //  binding.codeTakhfifTxt.text = disCountMoney.toString()
        takhfifCode = disCountMoney
        if (!walletProfit){
            codeProfit = true
            binding.allMoney.text = addNumberSeparator((total - disCountMoney).toDouble())
            total = (total - disCountMoney)
        }
        else{
            Toast.makeText(this," شما میتوانید از کیف تخفیف یا از کد تخفیف استفاده کنید. ",Toast.LENGTH_LONG).show()
        }
    }
}