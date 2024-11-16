package com.example.starfood.ui.home.subkalas

import android.app.Dialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.starfood.common.addNumberSeparator
import com.example.starfood.databinding.SelectNumberDialogLayoutBinding
import com.example.starfood.datamodel.amountofproduct.AmountProductDataModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class SelectAmountDialog(
    private var selectAmountCallBack: SelectAmountCallBack,
    private val goodSn: String,
    private val secondUnitAmount: String?,
    private val uName: String?,
    private val secondUnit: String?,
    private val amount: String?,
    private val amountExists:String
): DialogFragment(),
    AmountAdapter.CallBackNumber {
    private lateinit var callback: SelectAmountCallBack
    private lateinit var  binding : SelectNumberDialogLayoutBinding
    private lateinit var amountDataModel :AmountProductDataModel
    private val viewModel: AmountDialogViewModel by viewModel()
    override fun onAttach(context: Context){
        super.onAttach(context)
        callback = selectAmountCallBack
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog{
        binding = SelectNumberDialogLayoutBinding.inflate(layoutInflater)
        val dialog = Dialog(requireContext())
        dialog.setContentView(binding.root)
        dialog.window?.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT)
        binding.amountRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.amountRecycler.adapter = AmountAdapter(amount!!.toInt(),secondUnit!!,secondUnitAmount!!,uName!!,this)
        return dialog
    }
    interface SelectAmountCallBack{
        fun onAddAmount(
            totalItem: String,
            uName: String?,
            secondUnit: String?,
            number: Int,
            amountUnit: String
        )
    }
    override fun setNumber(totalAmount: String, uName: String, secondUnit: String, number: Int,amountUnit:String) {
        if(totalAmount.toInt() < amountExists!!.toInt()){
            callback.onAddAmount(totalAmount,uName,secondUnit,number,amountUnit)
            dismiss()
        }else{
            showCustomToast(" مقدار "+ addNumberSeparator(amountExists.toDouble())+" "+ uName+" می باشد. ")
        }
    }
    fun showCustomToast(message: String?) {
        val inflater = getLayoutInflater()
        val layout: View = inflater.inflate(com.example.starfood.R.layout.custome_toast, null)
        val text = layout.findViewById<TextView>(com.example.starfood.R.id.toast_text)
        text.text = message
        val icon = layout.findViewById<ImageView>(com.example.starfood.R.id.toast_icon)
        icon.setImageResource(com.example.starfood.R.drawable.star)
        val toast = Toast(context)
        toast.setDuration(Toast.LENGTH_SHORT)
        toast.setView(layout)
        toast.show()
    }
}