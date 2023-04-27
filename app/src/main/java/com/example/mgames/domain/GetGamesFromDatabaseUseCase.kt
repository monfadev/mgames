package com.example.mgames.domain

import com.example.mgames.data.local.toDatabase
import com.example.mgames.repo.GameRepository
import javax.inject.Inject

class GetGamesFromDatabaseUseCase @Inject constructor(private val gameRepository: GameRepository) {
    suspend operator fun invoke(): List<GameItem> {

        val games = gameRepository.getGamesFromDatabase()
        return games.ifEmpty {
            emptyList()
        }
    }
}