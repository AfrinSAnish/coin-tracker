package com.example.coin_track.di.domain.model.repository

import com.example.coin_track.di.domain.model.Coin
import com.example.coin_track.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface  CoinRepository {
    suspend fun getCoinList(): NetworkResult<Flow<Coin>>
}