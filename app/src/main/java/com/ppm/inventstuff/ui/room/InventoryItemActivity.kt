package com.ppm.inventstuff.ui.room

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ppm.inventstuff.ViewModelFactory
import com.ppm.inventstuff.adapter.InventoryItemAdapter
import com.ppm.inventstuff.adapter.RoomAdapter
import com.ppm.inventstuff.databinding.ActivityInventoryItemBinding

class InventoryItemActivity : AppCompatActivity() {

    private var _binding: ActivityInventoryItemBinding? = null
    private val binding get() = _binding!!

    private val viewModel: InventoryItemViewModel by viewModels {
        ViewModelFactory.getInstance(this)
    }

    private var roomIds: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityInventoryItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        showData()
        setupAction()
    }

    private fun showData() {
        val adapter = InventoryItemAdapter()
        roomIds = intent.getIntExtra(RoomAdapter.ROOM_ID, -1)
        Log.d("ID ROOM HERE", "ID ROOM RESPONSE : $roomIds")

        // Setup RecyclerView
        binding.rvInventoryItems.layoutManager = LinearLayoutManager(this)
        binding.rvInventoryItems.adapter = adapter

        // Fetch data from ViewModel based on room ID
        viewModel.getAllInventoryItem(roomIds).observe(this) { results ->
            Log.d("InventoryItemActivity", "dbResponse: $results")
            adapter.submitList(results)
        }
    }

    private fun setupAction() {
        binding.fabAddInventoryItem.setOnClickListener {
            val intent = Intent(this, InventoryItemInputActivity::class.java).apply {
                putExtra(ID_ROOM, roomIds)
            }
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val ID_ROOM = "ID_ROOM"
    }
}
