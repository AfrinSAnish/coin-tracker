package com.example.coin_track.remote.api

import com.example.coin_track.remote.model.response.CoinListResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface CoinAPI {
    @GET("assets")
    suspend fun getCoinList(
        @Header("Authorization") auth: String = "Bearer a0f9485d9de5c53fc5b2e1094b2de847e6bc3c647b591fcf13e3b4115a3e07d4"
    ): CoinListResponse
}