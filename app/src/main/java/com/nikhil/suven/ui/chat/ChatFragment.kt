package com.nikhil.suven.ui.chat

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.nikhil.suven.R
import com.nikhil.suven.databinding.FragmentChatBinding
import com.nikhil.suven.ui.chat.adapter.ChatRecyclerAdapter
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class ChatFragment : Fragment(R.layout.fragment_chat) {

    private val chatViewModel: ChatViewModel by viewModels()
    lateinit var binding: FragmentChatBinding
    private val adapter = ChatRecyclerAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentChatBinding.bind(view)
        binding.lifecycleOwner = viewLifecycleOwner

        binding.chatRecyclerView.adapter = adapter
        chatViewModel.list.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
            if (it.isEmpty()) {
                Timber.d("Empty Messages DB")
                chatViewModel.insertDummyMessages()
            } else{
                Timber.d("Messages: ${it.size}")
            }
        })
    }

}