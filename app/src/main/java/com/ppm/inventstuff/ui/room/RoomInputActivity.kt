package com.ppm.inventstuff.ui.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.ppm.inventstuff.ViewModelFactory
import com.ppm.inventstuff.data.local.Room
import com.ppm.inventstuff.databinding.ActivityInputRoomBinding

class RoomInputActivity : AppCompatActivity() {

    private var _binding: ActivityInputRoomBinding? = null
    private val binding get() = _binding!!

    private val viewModel: RoomViewModel by viewModels {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityInputRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupAction()
    }

    private fun setupAction() {
        viewModel.currentImageUri.observe(this) { uri ->
            uri?.let {
                showImage()
            }
        }
        binding.buttonAdd.setOnClickListener { insertDataRoom() }
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
            binding.ivRoomPhoto.setImageURI(it)
        }
    }


    private fun insertDataRoom() {
        val roomName = binding.edtRoomName.text.toString()
        val roomDescription = binding.edtRoomDescription.text.toString()
        val roomImage = viewModel.currentImageUri.value.toString()

        if (roomName.isEmpty() || roomDescription.isEmpty() || roomImage.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        viewModel.insertRoom(
            Room(
                0,
                roomName,
                roomDescription,
                roomImage
            )
        )
        finish()
    }


}