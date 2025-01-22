package com.ppm.inventstuff.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface InventoryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertRoom(room: Room)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertInventoryItem(itemsRoom: ItemsRoom)

    @Query("SELECT * FROM Room")
    fun getAllRoom(): LiveData<List<Room>>

    @Query("SELECT * FROM ItemsRoom WHERE roomId = :roomId")
    fun getAllItemsByRoomId(roomId: Int): LiveData<List<ItemsRoom>>
}