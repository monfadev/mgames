package com.example.mgames.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mgames.data.remote.GameModel
import com.example.mgames.domain.GameItem

@Entity(tableName = "table_game")
data class GameEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val thumbnail: String,
)

fun GameItem.toDatabase() = GameEntity(id = id, title = title, thumbnail = thumbnail)