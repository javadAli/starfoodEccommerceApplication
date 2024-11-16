package com.example.starfood.ui.cart

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.starfood.databinding.ChangedPriceCartDialogBinding

class ChangedItemPriceDialog(private val click: ClickListenerChanged, private val itemList:ArrayList<String>):DialogFragment() {

    private lateinit var callback: ClickListenerChanged
    private lateinit var  binding : ChangedPriceCartDialogBinding
    private lateinit var context :Context
    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.context = context
        callback = click
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog{
        binding = ChangedPriceCartDialogBinding.inflate(layoutInflater)
        val dialog = Dialog(requireContext())
        dialog.setContentView(binding.root)
        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        binding.itemRcv.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        binding.itemRcv.adapter =UpdatedCartPriceAdapter(itemList)
        binding.noContinue.setOnClickListener{
            dismiss()
        }
        binding.keepOn.setOnClickListener{
            callback.applyClick(true)
            dismiss()
        }
        return dialog
    }

    interface ClickListenerChanged{
        fun applyClick(isContinue:Boolean)
    }
}