package com.example.coin_track.remote.api

import com.example.coin_track.remote.model.response.CoinListResponse
import retrofit2.http.GET

interface CoinAPI {
    @GET("v3/assets")
    suspend fun getCoinList(): CoinListResponse
}