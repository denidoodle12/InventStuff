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

    @Query("SELECT * from Room")
    fun getAllRoom(): LiveData<List<Room>>
}