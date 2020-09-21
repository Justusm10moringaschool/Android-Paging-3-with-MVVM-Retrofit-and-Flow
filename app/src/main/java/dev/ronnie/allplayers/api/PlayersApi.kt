package dev.ronnie.allplayers.api

import dev.ronnie.allplayers.models.Players
import dev.ronnie.allplayers.utils.retrofit
import retrofit2.http.GET
import retrofit2.http.Query

interface PlayersApi {

    @GET("api/v1/players")
    suspend fun getPlayers(
        @Query("per_page") per_page: Int?,
        @Query("page") page: Int?,
    ): Players


}