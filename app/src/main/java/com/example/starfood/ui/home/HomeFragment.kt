package com.example.starfood.ui.home

import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.preference.PreferenceManager
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.activity.addCallback
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.starfood.R
import com.example.starfood.common.StarFoodFragment
import com.example.starfood.common.networkMonitoring.NetworkStateHolder
import com.example.starfood.databinding.FragmentHomeBinding
import com.example.starfood.datamodel.categorydatamodel.SearchDataModelItem
import com.example.starfood.datamodel.repository.userlogin.TokenContainer
import com.example.starfood.ui.category.CategoryListAdapter
import com.example.starfood.ui.home.subkalas.ProductExplainActivity
import com.example.starfood.ui.login.LoginActivity
import com.smarteist.autoimageslider.SliderView
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel
class HomeFragment:StarFoodFragment(){
    private lateinit var binding: FragmentHomeBinding
    private val viewModel:HomeViewModel by viewModel()
    val imgUrl ="https://starfoods.ir/resources/assets/images/smallSlider/"
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?):View?{
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            showExitDialog()
        }
        return binding.root
    }

    private fun showExitDialog() {
        val pubSearch : EditText = binding.pubSearch
        val recyclerView: RecyclerView = binding.resultrecycler
        var searchValue = pubSearch.getText().toString();

        if (searchValue.isNotEmpty()){
            pubSearch.setText("")
            recyclerView.visibility=View.GONE
        }else{
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
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        if(NetworkStateHolder.isConnected){
            checkUserAuthentication()
            viewModel.moveOrdersToOnline()
            viewModel.getSliders()
            viewModel.sliderLiveData.observe(viewLifecycleOwner) {
                val adapter = SliderAdapter(it.bigSliderList, context)
                binding.bannerSliderViewPager.autoCycleDirection = SliderView.LAYOUT_DIRECTION_LTR
                binding.bannerSliderViewPager.setSliderAdapter(adapter)
                binding.bannerSliderViewPager.scrollTimeInSec = 3
                binding.bannerSliderViewPager.isAutoCycle = true
                binding.bannerSliderViewPager.startAutoCycle()

                val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity)

                if(it.sliders[0].fourthPic.toInt()==1){
                    val editor = sharedPreferences.edit()
                    editor.putInt("exitButton", 1)
                    editor.apply()
                }else{
                    val editor = sharedPreferences.edit()
                    editor.putInt("exitButton", 0)
                    editor.apply()
                }
                if (it.smallSlider[0].activeOrNot.toInt() == 1) {
                    binding.smallSiler.visibility = View.VISIBLE
                    Picasso.get()
                        .load(imgUrl + it.smallSlider[0].firstPic)
                        .error(R.drawable.starfood)
                        .fit() // Ensures the image fits within the bounds of the ImageView
                        .into(binding.firstImage);
                        val manager = activity?.packageManager
                        val info = activity?.let { it1 ->
                            manager!!.getPackageInfo(
                                it1.packageName,
                                PackageManager.GET_ACTIVITIES
                            )
                        }
                        if (info!!.versionCode < it.sliders[0].fifthPic.toInt()) {
                            Toast.makeText(
                                activity,
                                "لطفا ورژن جدید برنامه را دانلود کنید!",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                } else {
                    binding.smallSiler.visibility = View.GONE
                }
            }
        }else{
            val adapter = SliderAdapter(listOf("slider_130_1","slider_130_2","slider_130_3","slider_130_4","slider_130_5"), context)
            binding.bannerSliderViewPager.autoCycleDirection = SliderView.LAYOUT_DIRECTION_LTR
            binding.bannerSliderViewPager.setSliderAdapter(adapter)
            binding.bannerSliderViewPager.scrollTimeInSec = 3
            binding.bannerSliderViewPager.isAutoCycle = true
            binding.bannerSliderViewPager.startAutoCycle()
            binding.smallSiler.visibility=View.GONE
        }

        binding.products.layoutManager = GridLayoutManager(context, 3)
        viewModel.getCategory()
        viewModel.categoryLiveData.observe(viewLifecycleOwner){
            binding.products.adapter = CategoryListAdapter(requireContext(),it.toMutableList())
        }
        binding.pubSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                s?.let {
                    if (it.isNotEmpty()) {
                        viewModel.searchList(TokenContainer.psn!!, changeToArabicLetterAndEngNumber(it.toString()))
                        viewModel.searchLiveData.observe(viewLifecycleOwner) { searchResults ->
                            viewModel.searchLiveData.removeObservers(viewLifecycleOwner)
                            if (searchResults.isNotEmpty()){
                                val adapter = SearchAdapter(searchResults)
                                binding.resultrecycler.adapter = adapter
                                binding.resultrecycler.layoutManager = LinearLayoutManager(requireContext())
                                binding.resultrecycler.visibility = View.VISIBLE
                                adapter.notifyDataSetChanged()
                                adapter.setOnItemClickListener(object : SearchAdapter.OnItemClickListener{
                                    override fun onItemClick(item:SearchDataModelItem){
                                        val intent = Intent(activity,ProductExplainActivity::class.java)
                                        intent.putExtra("goodSn",item.GoodSn)
                                        intent.putExtra("firstGroupId",item.firstGroupId)
                                        startActivity(intent)
                                    }
                                })
                            }else{
                                binding.resultrecycler.visibility = View.GONE
                            }
                        }
                    }else{
                        binding.resultrecycler.visibility=View.GONE
                    }
                }?: run {
                    binding.resultrecycler.visibility = View.GONE
                }
            }
            override fun afterTextChanged(s: Editable?){
            }
        })

    }
    fun changeToArabicLetterAndEngNumber(str: String): String {
        val dictionary = mapOf(
            'ی' to 'ي', 'ک' to 'ک', 'ە' to 'ه', 'ێ' to 'ي', 'ھ' to 'ه',
            'ۆ' to 'و', 'ۇ' to 'و', 'ۈ' to 'و', 'ۋ' to 'و', 'ې' to 'ي',
            'ۊ' to 'و', 'ؤ' to "",  'أ' to "",  'إ' to "",  'ئ' to "",
            '۰' to '0', '۱' to '1', '۲' to '2', '۳' to '3',
            '۴' to '4', '۵' to '5', '۶' to '6',
            '۷' to '7', '۸' to '8', '۹' to '9'
        )

        // Replace each character based on the dictionary
        return str.map { char ->
            dictionary[char] ?: char // Use original character if not found in dictionary
        }.joinToString("")
    }
    fun checkUserAuthentication(){
        viewModel.checkUserLogin()
        viewModel.checklogin.observe(viewLifecycleOwner){
            if(it!="YES"){
                val intent=Intent(requireActivity(),LoginActivity::class.java)
                startActivity(intent)
            }
        }

    }

}

