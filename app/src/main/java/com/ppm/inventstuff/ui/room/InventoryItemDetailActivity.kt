package com.ppm.inventstuff.ui.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.ppm.inventstuff.R
import com.ppm.inventstuff.adapter.InventoryItemAdapter
import com.ppm.inventstuff.databinding.ActivityInventoryItemDetailBinding

class InventoryItemDetailActivity : AppCompatActivity() {

    private var _binding: ActivityInventoryItemDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityInventoryItemDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        showData()
    }

    private fun showData() {
        val imgItem = intent.getStringExtra(InventoryItemAdapter.IMG_ITEM)
        val kdItem = intent.getIntExtra(InventoryItemAdapter.KD_ITEM, -1)
        val nameItem = intent.getStringExtra(InventoryItemAdapter.NAME_ITEM)
        val stockItem = intent.getIntExtra(InventoryItemAdapter.STOCK_ITEM, 0)
        val priceItem = intent.getIntExtra(InventoryItemAdapter.PRICE_ITEM, 0)

        val totalItem = stockItem * priceItem

        Log.d("imgItem", "imgItem: $imgItem")
        Log.d("kdItem", "kdItem: $kdItem")
        Log.d("nameItem", "nameItem: $nameItem")
        Log.d("stockItem", "stockItem: $stockItem")
        Log.d("priceItem", "priceItem: $priceItem")
        Log.d("totalItem", "totalItem: $totalItem")

        Glide.with(this@InventoryItemDetailActivity)
            .load(imgItem)
            .into(binding.ivDetailInventoryItemPhoto)

        binding.tvInventoryCode.text = kdItem.toString()
        binding.tvInventoryName.text = nameItem
        binding.tvInventoryStock.text = stockItem.toString()
        binding.tvInventoryPrice.text = priceItem.toString()
        binding.tvInventoryTotal.text = totalItem.toString()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}