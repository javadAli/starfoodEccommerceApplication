package com.example.starfood.ui.cart

import android.app.Dialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import com.example.starfood.databinding.ApplydiscountDialogFragmentBinding
import com.example.starfood.datamodel.amountofproduct.AmountProductDataModel
import com.example.starfood.datamodel.repository.userlogin.TokenContainer
import org.koin.androidx.viewmodel.ext.android.viewModel

class ApplyDiscountDialogFragment(private var applyTakhfifListen: GetTakhfif): DialogFragment(){

    private lateinit var callback: GetTakhfif
    private lateinit var  binding : ApplydiscountDialogFragmentBinding
    private var money = 0
    private val viewModel:ShippingViewModel by viewModel()

    override fun onAttach(context: Context){
        super.onAttach(context)
        callback = applyTakhfifListen
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = ApplydiscountDialogFragmentBinding.inflate(layoutInflater)
        val dialog = Dialog(requireContext())
        dialog.setContentView(binding.root)
        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        binding.applyBtn.setOnClickListener {
            viewModel.checkTakhfif(TokenContainer.psn!!,binding.codeEdit.text.toString())
            viewModel.checkTakhfifLiveData.observe(this){
                money = it.takhfifCodeMoneyInToman
                callback.applyTakhfifMoney(it.takhfifCodeMoneyInToman)
                dismiss()
            }
        }
        return dialog
    }
    interface GetTakhfif{
        fun applyTakhfifMoney(disCountMoney: Int)
    }
}