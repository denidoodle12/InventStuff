package com.ppm.inventstuff.data

import androidx.lifecycle.LiveData
import com.ppm.inventstuff.data.local.InventoryDao
import com.ppm.inventstuff.data.local.ItemsRoom
import com.ppm.inventstuff.data.local.Room

class InventoryRepository(private val inventoryDao: InventoryDao) {

    suspend fun insertRoom(room: Room) {
        inventoryDao.insertRoom(room)
    }

    suspend fun insertInventoryItem(itemsRoom: ItemsRoom) {
        inventoryDao.insertInventoryItem(itemsRoom)
    }

    fun getAllRoom(): LiveData<List<Room>> = inventoryDao.getAllRoom()

    fun getAllInventoryItem(roomId: Int): LiveData<List<ItemsRoom>> = inventoryDao.getAllItemsByRoomId(roomId)


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