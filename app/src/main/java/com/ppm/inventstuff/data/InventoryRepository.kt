package com.ppm.inventstuff.data

import androidx.lifecycle.LiveData
import com.ppm.inventstuff.data.local.InventoryDao
import com.ppm.inventstuff.data.local.Room

class InventoryRepository(private val inventoryDao: InventoryDao) {

    suspend fun insertRoom(room: Room) {
        inventoryDao.insertRoom(room)
    }

    fun getAllRoom(): LiveData<List<Room>> = inventoryDao.getAllRoom()


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