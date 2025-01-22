package com.ppm.inventstuff.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ppm.inventstuff.R
import com.ppm.inventstuff.data.local.ItemsRoom
import com.ppm.inventstuff.data.local.Room
import com.ppm.inventstuff.databinding.ItemsInventoryBinding
import com.ppm.inventstuff.databinding.ItemsRoomBinding

class InventoryItemAdapter : ListAdapter<ItemsRoom, InventoryItemAdapter.MyViewHolder>(WordsComparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemsInventoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: InventoryItemAdapter.MyViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {  }
    }

    class MyViewHolder(private val binding: ItemsInventoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ItemsRoom) {
            Glide.with(binding.ivItemImage.context)
                .load(data.imageInventoryItem)
                .into(binding.ivItemImage)

            binding.tvItemCode.text = data.kdItem.toString()
            binding.tvItemName.text = data.nameItems
            binding.tvItemStock.text = data.stockItems.toString()
            binding.tvItemPrice.text = data.priceItems.toString()

        }
    }

    class WordsComparator : DiffUtil.ItemCallback<ItemsRoom>() {
        override fun areItemsTheSame(oldItem: ItemsRoom, newItem: ItemsRoom): Boolean {
            return oldItem.kdItem == newItem.kdItem
        }

        override fun areContentsTheSame(oldItem: ItemsRoom, newItem: ItemsRoom): Boolean {
            return oldItem == newItem
        }
    }

}