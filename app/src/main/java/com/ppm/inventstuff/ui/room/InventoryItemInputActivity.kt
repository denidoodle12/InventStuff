package com.ppm.inventstuff.ui.room

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.ppm.inventstuff.ViewModelFactory
import com.ppm.inventstuff.adapter.RoomAdapter
import com.ppm.inventstuff.data.local.ItemsRoom
import com.ppm.inventstuff.databinding.ActivityInputInventoryItemBinding

class InventoryItemInputActivity : AppCompatActivity() {

    private var _binding: ActivityInputInventoryItemBinding? = null
    private val binding get() = _binding!!

    private val viewModel: InventoryItemViewModel by viewModels {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityInputInventoryItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupAction()
        val roomIds = intent.getIntExtra(InventoryItemActivity.ID_ROOM, -1)
        Log.d("ROOM ID INPUT", "ROOM ID INPUT RESPONSE : $roomIds")
    }

    private fun setupAction() {
        viewModel.currentImageUri.observe(this) { uri ->
            uri?.let {
                showImage()
            }
        }
        binding.buttonAdd.setOnClickListener { insertDataInventoryItem() }
        binding.buttonGallery.setOnClickListener { startGallery() }
    }

    private fun startGallery() {
        launcherGallery.launch(
            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
        )
    }

    private val launcherGallery = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        uri?.let {
            viewModel.setCurrentImageUri(it)
        } ?: Log.d("Photo Picker", "No media selected")
    }

    private fun showImage() {
        viewModel.currentImageUri.value?.let {
            binding.ivInventoryItemPhoto.setImageURI(it)
        }
    }

    private fun insertDataInventoryItem() {
        val inventoryImage = viewModel.currentImageUri.value.toString()
        val roomIds = intent.getIntExtra(InventoryItemActivity.ID_ROOM, -1)
        val inventoryName = binding.edtInventoryName.text.toString()
        val inventoryStock = binding.edtInventoryStock.text.toString()
        val inventoryPrice = binding.edtInventoryPrice.text.toString()

        viewModel.insertInventoryItem(
            ItemsRoom(
                nameItems = inventoryName,
                stockItems = inventoryStock.toInt(),
                priceItems = inventoryPrice.toFloat(),
                imageInventoryItem = inventoryImage,
                roomId = roomIds
            )
        )
        finish()
    }


}