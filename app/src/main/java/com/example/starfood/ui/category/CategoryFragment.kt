package com.example.starfood.ui.category

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.activity.addCallback
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.starfood.R
import com.example.starfood.common.StarFoodFragment
import com.example.starfood.databinding.FragmentCategoryBinding
import com.example.starfood.ui.MainActivity
import com.example.starfood.ui.login.LoginActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class CategoryFragment:StarFoodFragment() {
    private lateinit var binding : FragmentCategoryBinding
    private val categoryViewModel : CategoryViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ):View{
        binding = FragmentCategoryBinding.inflate(inflater,container,false)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            showExitDialog()
        }
        return binding.root
    }
    private fun showExitDialog() {
        val builder = activity?.let { AlertDialog.Builder(it) }
        if (builder != null) {
            builder.setTitle("بستن")
                .setMessage("می خواهید برنامه را ببندید؟")
                .setPositiveButton("بله") {  _, _ -> requireActivity().finish()
                    System.exit(0)

                }.setNegativeButton("خیر") { dialog: DialogInterface, _: Int ->
                    dialog.dismiss()
                }.show()
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        categoryViewModel.categoryLiveData.observe(viewLifecycleOwner){
            val layoutManager = GridLayoutManager(context, 3)
            binding.categoryRc.layoutManager = layoutManager
            binding.categoryRc.adapter = CategoryListAdapter(requireContext(),it.toMutableList())
        }

    }
}