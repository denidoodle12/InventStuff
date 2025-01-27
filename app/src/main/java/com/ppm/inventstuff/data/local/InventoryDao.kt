package com.ppm.inventstuff.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface InventoryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertRoom(room: Room)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertInventoryItem(itemsRoom: ItemsRoom)

    @Query("UPDATE ItemsRoom SET nameItems = :nameItems, stockItems = :stockItems, priceItems = :priceItems WHERE kdItem = :kdItem")
    suspend fun updateInventoryItem(kdItem: Int, nameItems: String, stockItems: Int, priceItems: Int)

    @Query("DELETE FROM ItemsRoom WHERE kdItem = :kdItem")
    suspend fun deleteInventoryItem(kdItem: Int)

    @Query("SELECT * FROM Room")
    fun getAllRoom(): LiveData<List<Room>>

    @Query("SELECT * FROM ItemsRoom")
    fun getAllInventoryItem(): LiveData<List<ItemsRoom>>

    @Query("SELECT * FROM ItemsRoom WHERE roomId = :roomId")
    fun getAllItemsByRoomId(roomId: Int): LiveData<List<ItemsRoom>>

    @Query("SELECT * FROM ItemsRoom WHERE kdItem = :kdItem")
    fun getAllInventoryByKdItem(kdItem: Int): LiveData<List<ItemsRoom>>

}