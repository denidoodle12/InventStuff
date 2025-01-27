package com.ppm.inventstuff.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ppm.inventstuff.R
import com.ppm.inventstuff.data.local.ItemsRoom
import com.ppm.inventstuff.databinding.ItemsInventoryBinding
import com.ppm.inventstuff.ui.room.InventoryItemDetailActivity
import java.text.NumberFormat
import java.util.*

class InventoryItemAdapter : ListAdapter<ItemsRoom, InventoryItemAdapter.MyViewHolder>(WordsComparator()) {

    var onEditClicked: ((ItemsRoom) -> Unit)? = null
    var onDeleteClicked: ((ItemsRoom) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemsInventoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val getData = getItem(position)
        holder.bind(getData)
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, InventoryItemDetailActivity::class.java).apply {
                putExtra(IMG_ITEM, getData.imageInventoryItem)
                putExtra(KD_ITEM, getData.kdItem)
                putExtra(NAME_ITEM, getData.nameItems)
                putExtra(STOCK_ITEM, getData.stockItems)
                putExtra(PRICE_ITEM, getData.priceItems)
//                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
            }
            holder.itemView.context.startActivity(intent)
        }
    }

    inner class MyViewHolder(private val binding: ItemsInventoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ItemsRoom) {
            // Load image
            Glide.with(binding.ivItemImage.context)
                .load(data.imageInventoryItem)
                .into(binding.ivItemImage)

            // Set formatted data
            val formatPriceID = formatCurrency(data.priceItems)

            binding.tvItemCode.text = binding.root.context.getString(R.string.kdItem_format, data.kdItem)
            binding.tvItemName.text = data.nameItems
            binding.tvItemStock.text = binding.root.context.getString(R.string.stock_format, data.stockItems)
            binding.tvItemPrice.text = binding.root.context.getString(R.string.price_format, formatPriceID)

            // Handle button actions
            binding.buttonEdit.setOnClickListener {
                onEditClicked?.invoke(data)
            }

            binding.buttonDelete.setOnClickListener {
                onDeleteClicked?.invoke(data)
            }
        }

        private fun formatCurrency(amount: Int): String {
            val formatter = NumberFormat.getCurrencyInstance(Locale("id", "ID"))
            return formatter.format(amount)
            // .replace("Rp", "Rp.")
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

    companion object {
        const val IMG_ITEM = "IMG_ITEM"
        const val KD_ITEM = "KD_ITEM"
        const val NAME_ITEM = "NAME_ITEM"
        const val STOCK_ITEM = "STOCK_ITEM"
        const val PRICE_ITEM = "PRICE_ITEM"
    }
}
