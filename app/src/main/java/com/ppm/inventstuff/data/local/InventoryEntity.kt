package com.ppm.inventstuff.data.local

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity
data class Room(
    @PrimaryKey(autoGenerate = true)
    val idRoom: Int = 0,
    val nameRoom: String,
    val descriptionRoom: String,
    val imageRoom: String
)

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Room::class,
            parentColumns = ["idRoom"],
            childColumns = ["roomId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("roomId")]
)
data class ItemsRoom(
    @PrimaryKey(autoGenerate = true)
    val kdItem: Int = 0,
    val nameItems: String,
    val stockItems: Int,
    val priceItems: Float,
    val imageInventoryItem: String,
    val roomId: Int
)
