package com.nikhil.suven.ui.dashboard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nikhil.suven.app.domain_model.Transaction
import com.nikhil.suven.databinding.ItemPurchaseBinding

class PurchaseRecyclerAdapter :
    ListAdapter<Transaction, PurchaseViewHolder>(PurchaseDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PurchaseViewHolder {
        return PurchaseViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: PurchaseViewHolder, position: Int) {
        val item = getItem(position)
        holder.bindItem(item)
    }

}

class PurchaseViewHolder private constructor(val binding: ItemPurchaseBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindItem(item: Transaction) {
        binding.pojo = item
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): PurchaseViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemPurchaseBinding.inflate(layoutInflater, parent, false)
            return PurchaseViewHolder(binding)
        }
    }
}

class PurchaseDiffCallback : DiffUtil.ItemCallback<Transaction>() {
    override fun areItemsTheSame(oldItem: Transaction, newItem: Transaction): Boolean {
        return oldItem.dateCreated == newItem.dateCreated
    }

    override fun areContentsTheSame(oldItem: Transaction, newItem: Transaction): Boolean {
        return oldItem == newItem
    }
}
