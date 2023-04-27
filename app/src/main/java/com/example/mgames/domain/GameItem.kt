package com.example.mgames.domain

import com.example.mgames.data.local.GameEntity
import com.example.mgames.data.remote.GameModel


data class GameItem(
    val id: Int,
    val title: String,
    val thumbnail: String,
)

fun GameModel.toDomain() = GameItem(id = id, title = title, thumbnail = thumbnail)
fun GameEntity.toDomain() = GameItem(id = id, title = title, thumbnail = thumbnail)
