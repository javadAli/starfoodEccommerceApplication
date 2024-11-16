package com.example.starfood.ui.login

import android.preference.PreferenceManager
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.starfood.common.ItemClickListener
import com.example.starfood.databinding.ConfirmLoginAdapterBinding
import com.example.starfood.datamodel.logindatamodel.LoginInfo
import com.example.starfood.datamodel.repository.userlogin.TokenContainer

class ConfirmLoginAdapter(
    private val loginInfo: List<LoginInfo>,
    private val itemClickListener: ItemClickListener,
    private val confirmLoginActivity: ConfirmLoginActivity
):
    RecyclerView.Adapter<ConfirmLoginAdapter.ViewHolder>() {
    var selectedPosition = -1
    inner class ViewHolder(private val itemBinding: ConfirmLoginAdapterBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(items: LoginInfo, holder: ViewHolder, position: Int){
            itemBinding.browser.text = items.browser.trim()
            itemBinding.systemOs.text = items.platform
            itemBinding.sessionIdText.text=items.sessionId
            itemBinding.radioBtn.isChecked = position == selectedPosition
            itemBinding.radioBtn.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    notifyItemChanged(selectedPosition)
                    selectedPosition = holder.adapterPosition
                    notifyItemChanged(selectedPosition)
                    val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(confirmLoginActivity)
                    val editor = sharedPreferences.edit()
                    TokenContainer.sessionIdUPdate(itemBinding.sessionIdText.text.toString())
                    editor.putString("token", itemBinding.sessionIdText.text.toString())
                    editor.putString("message_data", "")
                    editor.putString("message_title", "")
                    editor.apply()
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = ConfirmLoginAdapterBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(loginInfo[position],holder,position)
    }

    override fun getItemCount(): Int {
        return loginInfo.size
    }
}