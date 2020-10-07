package com.nikhil.suven.ui.chat

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.nikhil.suven.R
import com.nikhil.suven.databinding.FragmentChatBinding
import com.nikhil.suven.ui.chat.adapter.ChatRecyclerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatFragment : Fragment(R.layout.fragment_chat) {

    private val chatViewModel: ChatViewModel by viewModels()
    lateinit var binding: FragmentChatBinding
    private val adapter = ChatRecyclerAdapter()

    companion object{
        fun newInstance(): ChatFragment {
            val args = Bundle()
            val fragment = ChatFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentChatBinding.bind(view)
        binding.vm = chatViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val linearLayoutManager = LinearLayoutManager(requireContext())
        linearLayoutManager.reverseLayout = true
        binding.chatRecyclerView.layoutManager = linearLayoutManager
        binding.chatRecyclerView.adapter = adapter

        chatViewModel.list.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
            if (it.isEmpty()) {
                chatViewModel.insertDummyMessages()
            } else {
                binding.chatRecyclerView.scrollToPosition(0)
            }
        })
    }

}