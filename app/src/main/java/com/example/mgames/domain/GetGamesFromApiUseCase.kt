package com.example.mgames.domain

import com.example.mgames.data.local.toDatabase
import com.example.mgames.data.remote.GameApi
import com.example.mgames.repo.GameRepository
import javax.inject.Inject


class GetGamesFromApiUseCase @Inject constructor(private val gameRepository: GameRepository) {

    suspend operator fun invoke(): List<GameItem> {

        val games = gameRepository.getGamesFromApi()
        return if (games.isNotEmpty()) {
            gameRepository.deleteGames()
            gameRepository.insertGames(games.map { it.toDatabase() })
            games
        } else {
            emptyList()
        }
    }
}