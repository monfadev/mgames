package com.example.mgames.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface GameDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGames(games: List<GameEntity>)

    @Query("DELETE FROM table_game")
    suspend fun deleteGames()

    @Query("SELECT * FROM table_game ORDER BY title DESC")
    suspend fun getGames(): List<GameEntity>
}