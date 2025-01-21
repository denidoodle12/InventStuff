package com.ppm.inventstuff.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Room::class], version = 5, exportSchema = true)
abstract class InventoryDatabase : RoomDatabase() {
    abstract fun inventoryDao(): InventoryDao

    companion object {
        @Volatile
        private var INSTANCE: InventoryDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): InventoryDatabase {
            if (INSTANCE == null) {
                synchronized(InventoryDatabase::class.java) {
                    INSTANCE = androidx.room.Room.databaseBuilder(context.applicationContext,
                        InventoryDatabase::class.java, "inventory_database")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE as InventoryDatabase
        }

    }
}