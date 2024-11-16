package com.example.starfood.ui.message

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.starfood.common.StarFoodActivity
import com.example.starfood.databinding.ActivityMessageListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MessageListActivity : StarFoodActivity() {
    private lateinit var binding: ActivityMessageListBinding
    private val viewModel:MessageViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMessageListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.messageRcv.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        viewModel.getMessageList()
        viewModel.messageLiveData.observe(this){
            binding.messageRcv.adapter = MessageAdapter(it.messages)
            binding.messageRcv.scrollToPosition(it.messages.size - 1);
        }

        binding.sendButton.setOnClickListener{
            viewModel.doAddMessage(binding.messageText.text.toString()!!);
            viewModel.messageLiveData.observe(this){
                binding.messageRcv.adapter=MessageAdapter(it.messages)
                binding.messageRcv.scrollToPosition(it.messages.size - 1)
                binding.messageText.setText("")
            }
        }
    }
}