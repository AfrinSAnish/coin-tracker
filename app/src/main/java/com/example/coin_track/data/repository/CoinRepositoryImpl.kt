package com.example.coin_track.data.repository

import com.example.coin_track.di.domain.model.ListOfCoin
import com.example.coin_track.di.domain.model.repository.CoinRepository
import com.example.coin_track.remote.api.CoinAPI
import com.example.coin_track.utils.NetworkResult
import com.shashank.cointrackerapp.data.mapper.toCoinList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(val api: CoinAPI) : CoinRepository {
    override suspend fun getCoinList(): NetworkResult<Flow<ListOfCoin>> {
        return try {
            val coinListFlow: Flow<ListOfCoin> = flow {
                emit(api.getCoinList().toCoinList())
            }
            NetworkResult.Success(coinListFlow)
        } catch (e: Exception) {
            NetworkResult.Error(e.message)
        }
    }
}