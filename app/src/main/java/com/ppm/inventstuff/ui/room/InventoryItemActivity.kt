package com.ppm.inventstuff.ui.room

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
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

    private val adapter: InventoryItemAdapter by lazy { InventoryItemAdapter() }
    private var roomIds: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityInventoryItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        setupRecyclerView()
        showData()
        setupAction()
    }

    private fun setupRecyclerView() {
        roomIds = intent.getIntExtra(RoomAdapter.ROOM_ID, -1)

        binding.rvInventoryItems.apply {
            layoutManager = LinearLayoutManager(this@InventoryItemActivity)
            adapter = this@InventoryItemActivity.adapter
        }

    }


    private fun showData() {
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

        adapter.onEditClicked = { item ->
            val intent = Intent(this@InventoryItemActivity, InventoryItemEditActivity::class.java).apply {
                putExtra(KD_ITEM, item.kdItem)
                putExtra(IMG_ITEM, item.imageInventoryItem)
                putExtra(NAME_ITEM, item.nameItems)
                putExtra(STOCK_ITEM, item.stockItems)
                putExtra(PRICE_ITEM, item.priceItems)
//                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
            }
            startActivity(intent)
        }

        adapter.onDeleteClicked = { item ->
            AlertDialog.Builder(this)
                .setTitle("Delete Item")
                .setMessage("Are you sure to delete this item?")
                .setPositiveButton("Delete") { _, _ ->
                    viewModel.deleteInventoryItem(item.kdItem)
                }
                .setNegativeButton("Cancel", null)
                .show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val ID_ROOM = "ID_ROOM"
        const val KD_ITEM = "KD_ITEM"
        const val IMG_ITEM = "IMG_ITEM"
        const val NAME_ITEM = "NAME_ITEM"
        const val STOCK_ITEM = "STOCK_ITEM"
        const val PRICE_ITEM = "PRICE_ITEM"
    }
}
