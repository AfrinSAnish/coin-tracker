package com.example.coin_track.di

import com.example.coin_track.data.repository.CoinRepositoryImpl
import com.example.coin_track.di.domain.model.repository.CoinRepository
import com.example.coin_track.remote.api.CoinAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides @Singleton
    fun provideCoinRepository(api: CoinAPI): CoinRepository = CoinRepositoryImpl(api)
}