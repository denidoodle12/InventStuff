package com.ppm.inventstuff.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ppm.inventstuff.data.local.Room
import com.ppm.inventstuff.databinding.ItemsRoomBinding
import com.ppm.inventstuff.ui.room.InventoryItemActivity

class RoomAdapter : ListAdapter<Room, RoomAdapter.MyViewHolder>(WordsComparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemsRoomBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val getRoom = getItem(position)
        holder.bind(getRoom)
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, InventoryItemActivity::class.java).apply {
                putExtra(ROOM_ID, getRoom.idRoom)
            }
            holder.itemView.context.startActivity(intent)
        }
    }

    class MyViewHolder(private val binding: ItemsRoomBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Room) {
            Glide.with(binding.ivItemPhoto.context)
                .load(data.imageRoom)
                .into(binding.ivItemPhoto)
            binding.tvItemName.text = data.nameRoom
            binding.tvItemDescription.text = data.descriptionRoom

        }
    }

    class WordsComparator : DiffUtil.ItemCallback<Room>() {
        override fun areItemsTheSame(oldItem: Room, newItem: Room): Boolean {
            return oldItem.idRoom == newItem.idRoom
        }

        override fun areContentsTheSame(oldItem: Room, newItem: Room): Boolean {
            return oldItem == newItem
        }
    }

    companion object {
        const val ROOM_ID = "ROOM_ID"
    }

}