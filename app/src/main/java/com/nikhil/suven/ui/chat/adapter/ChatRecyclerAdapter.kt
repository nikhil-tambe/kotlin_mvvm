package com.nikhil.suven.ui.chat.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nikhil.suven.app.domain_model.Message
import com.nikhil.suven.databinding.ItemMessageBotBinding
import com.nikhil.suven.databinding.ItemMessageUserBinding

const val VIEW_TYPE_OWNER = 1
const val VIEW_TYPE_BOT = 2

class ChatRecyclerAdapter :
    ListAdapter<Message, RecyclerView.ViewHolder>(ChatDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_OWNER) {
            OwnerMessageViewHolder.from(parent)
        } else {
            BotMessageViewHolder.from(parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val message = getItem(position)
        if (holder is OwnerMessageViewHolder) {
            holder.bindData(message)
        } else if (holder is BotMessageViewHolder) {
            holder.bindData(message)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return if (item.isOwner) VIEW_TYPE_OWNER else VIEW_TYPE_BOT
    }

}

class OwnerMessageViewHolder private constructor(val binding: ItemMessageUserBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindData(message: Message) {
        binding.userMessage = message
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): OwnerMessageViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemMessageUserBinding.inflate(inflater, parent, false)
            return OwnerMessageViewHolder(binding)
        }
    }

}

class BotMessageViewHolder private constructor(val binding: ItemMessageBotBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindData(message: Message) {
        binding.botMessage = message
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): BotMessageViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemMessageBotBinding.inflate(inflater, parent, false)
            return BotMessageViewHolder(binding)
        }
    }

}

class ChatDiffCallback : DiffUtil.ItemCallback<Message>() {
    override fun areItemsTheSame(oldItem: Message, newItem: Message): Boolean {
        return oldItem.timestamp == newItem.timestamp
    }

    override fun areContentsTheSame(oldItem: Message, newItem: Message): Boolean {
        return oldItem == newItem
    }

}