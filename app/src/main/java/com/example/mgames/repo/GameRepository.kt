package com.example.mgames.repo

import com.example.mgames.data.local.GameDao
import com.example.mgames.data.local.GameEntity
import com.example.mgames.data.remote.GameService
import com.example.mgames.domain.GameItem
import com.example.mgames.domain.toDomain
import javax.inject.Inject

class GameRepository @Inject constructor(
    private val gameService: GameService,
    private val gameDao: GameDao
) {

    suspend fun getGamesFromApi(): List<GameItem> {

        val games = gameService.getGames()
        return games.map { it.toDomain() }
    }

    suspend fun getGamesFromDatabase(): List<GameItem> {

        val games = gameDao.getGames()
        return games.map { it.toDomain() }
    }

    suspend fun insertGames(games: List<GameEntity>) {

        gameDao.insertGames(games)
    }

    suspend fun deleteGames() {

        gameDao.deleteGames()
    }
}