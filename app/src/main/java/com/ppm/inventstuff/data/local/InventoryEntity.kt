package com.ppm.inventstuff.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Room(
    @PrimaryKey(autoGenerate = true)
    val idRoom: Int = 0,
    val nameRoom: String,
    val descriptionRoom: String,
    val imageRoom: String
)

@Entity
data class ItemsRoom(
    @PrimaryKey
    val kdItems: Int,
    val nameItems: String,
    val quantityItems: Int,
    val priceItems: Float
)