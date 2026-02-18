package com.example.coin_track.remote.model.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CoinListResponse(
    @Json(name="data")
    val data: List<Data?>?,
    @Json(name="timestamp")
    val timestamp: Long?
)

@JsonClass(generateAdapter = true)
data class CoinDataResponse(
    @Json(name ="changePercent24Hr")
    val changePercent24Hr: String? = null,
    @Json(name="explorer")
    val explorer: String? = null,
    @Json(name="id")
    val id: String? = null,
    @Json(name="marketCapUsd")
    val marketCapUsd: String? = null,
    @Json(name="maxSupply")
    val maxSupply: String? = null,
    @Json(name="name")
    val name: String? = null,
    @Json(name="priceUsd")
    val priceUsd: String? = null,
    @Json(name="rank")
    val rank: String? = null,
    @Json(name="supply")
    val supply: String? = null,
    @Json(name="symbol")
    val symbol: String? = null,
    @Json(name="volumeUsd24Hr")
    val volumeUsd24Hr: String? = null,
    @Json(name="vwap24Hr")
    val vwap24Hr: String? = null
)