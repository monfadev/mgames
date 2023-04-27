package com.example.mgames.data.remote

import com.example.mgames.util.Constants.Companion.GAMES_ENDPOINT
import retrofit2.http.GET
import retrofit2.Response

interface GameApi {

    @GET(GAMES_ENDPOINT)
    suspend fun getGames(): Response<List<GameModel>>

}