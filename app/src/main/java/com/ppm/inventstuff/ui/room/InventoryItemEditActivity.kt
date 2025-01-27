package com.ppm.inventstuff.ui.room

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.content.FileProvider
import com.bumptech.glide.Glide
import com.ppm.inventstuff.BuildConfig
import com.ppm.inventstuff.R
import com.ppm.inventstuff.ViewModelFactory
import com.ppm.inventstuff.adapter.InventoryItemAdapter
import com.ppm.inventstuff.data.local.ItemsRoom
import com.ppm.inventstuff.databinding.ActivityInventoryItemEditBinding
import java.io.File

class InventoryItemEditActivity : AppCompatActivity() {

    private var _binding: ActivityInventoryItemEditBinding? = null
    private val binding get() = _binding!!

    private val viewModel: InventoryItemViewModel by viewModels {
        ViewModelFactory.getInstance(this)
    }

    private var kdItem: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityInventoryItemEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        showData()
        setupActionUpdate()
    }

    private fun showData() {
        val imgItem = intent.getStringExtra(InventoryItemActivity.IMG_ITEM) ?: ""
        val nameItem = intent.getStringExtra(InventoryItemActivity.NAME_ITEM)
        val stockItem = intent.getIntExtra(InventoryItemActivity.STOCK_ITEM, 0)
        val priceItem = intent.getIntExtra(InventoryItemActivity.PRICE_ITEM, 0)

//        Log.d("IMAGE ITEM", "IMAGE ITEM RESPONSE $imgItem")
//
//        val imageFile = File(imgItem)

//        Log.d("imgItem", "imgItem: $imageFile")
        Log.d("kdItem", "kdItem: $kdItem")
        Log.d("nameItem", "nameItem: $nameItem")
        Log.d("stockItem", "stockItem: $stockItem")
        Log.d("priceItem", "priceItem: $priceItem")

//        val imageUri: Uri = FileProvider.getUriForFile(
//            this,
//            "${BuildConfig.APPLICATION_ID}.fileprovider",
//            imageFile
//        )

//        Glide.with(this)
//            .load(imgItem)
//            .into(binding.ivInventoryItemPhoto)

        binding.edtInventoryName.setText(nameItem)
        binding.edtInventoryStock.setText(stockItem.toString())
        binding.edtInventoryPrice.setText(priceItem.toString())
    }

    private fun setupActionUpdate() {
        kdItem = intent.getIntExtra(InventoryItemActivity.KD_ITEM, -1)
        Log.d("KD_ITEM","KODE ITEM RESPONSE : $kdItem")

        binding.buttonSave.setOnClickListener {
            val nameItem = binding.edtInventoryName.text.toString()
            val stockItem = binding.edtInventoryStock.text.toString().toIntOrNull() ?: 0
            val priceItem = binding.edtInventoryPrice.text.toString().toIntOrNull() ?: 0

            viewModel.updateInventoryItem(kdItem, nameItem, stockItem, priceItem)
            finish()
        }
    }


}