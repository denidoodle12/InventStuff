package com.ppm.inventstuff.di

import android.content.Context
import com.ppm.inventstuff.data.InventoryRepository
import com.ppm.inventstuff.data.local.InventoryDatabase

object Injection {
    fun provideRepository(context: Context): InventoryRepository {
        val localDatabase = InventoryDatabase.getDatabase(context)
        val inventoryDao = localDatabase.inventoryDao()
        return InventoryRepository.getInstance(inventoryDao)
    }
}