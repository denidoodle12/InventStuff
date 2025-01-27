package com.ppm.inventstuff.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.ppm.inventstuff.data.local.InventoryDao
import com.ppm.inventstuff.data.local.ItemsRoom
import com.ppm.inventstuff.data.local.RecapitulationResult
import com.ppm.inventstuff.data.local.Room

class InventoryRepository(private val inventoryDao: InventoryDao) {

    suspend fun insertRoom(room: Room) {
        inventoryDao.insertRoom(room)
    }

    suspend fun insertInventoryItem(itemsRoom: ItemsRoom) {
        inventoryDao.insertInventoryItem(itemsRoom)
    }

    suspend fun updateInventoryItem(kdItem: Int, nameItems: String, stockItems: Int, priceItems: Int) {
        inventoryDao.updateInventoryItem(kdItem, nameItems, stockItems, priceItems)
    }

    suspend fun deleteInventoryItem(kdItem: Int) {
        inventoryDao.deleteInventoryItem(kdItem)
    }

    fun getAllRoom(): LiveData<List<Room>> = inventoryDao.getAllRoom()

    fun getAllInventoryItem(): LiveData<List<ItemsRoom>> = inventoryDao.getAllInventoryItem()

    fun getAllInventoryItem(roomId: Int): LiveData<List<ItemsRoom>> = inventoryDao.getAllItemsByRoomId(roomId)

    fun getAllInventoryByKdItem(kdItem: Int): LiveData<List<ItemsRoom>> = inventoryDao.getAllInventoryByKdItem(kdItem)

    fun calculateRecapitulation(): LiveData<RecapitulationResult> {
        return getAllInventoryItem().map { items ->
            val totalQuantity = items.sumOf { it.stockItems }
            val totalAssets = items.sumOf { it.priceItems * it.stockItems }
            RecapitulationResult(totalQuantity, totalAssets)
        }
    }


    companion object {
        @Volatile
        private var instance: InventoryRepository? = null
        fun getInstance(
            inventoryDao: InventoryDao
        ): InventoryRepository = instance ?: synchronized(this) {
            instance ?: InventoryRepository(inventoryDao)
        }.also { instance = it }
    }
}