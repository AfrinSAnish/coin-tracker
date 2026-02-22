package com.example.coin_track.di.presentation.utils.composable.model
import android.icu.text.NumberFormat
import androidx.annotation.DrawableRes
import com.example.coin_track.di.domain.model.Coin
import com.example.coin_track.di.presentation.utils.getDrawableForCoin
import java.util.Locale

data class CoinUI(
    val id: String,
    val rank: Int,
    val name: String,
    val symbol: String,
    val marketCapUsd: DisplayNumber,
    val priceUsd: DisplayNumber,
    val changePercent24Hr: DisplayNumber,
    @DrawableRes val icon: Int
)


data class DisplayNumber(
    val value: Double,
    val formattedValue: String
)


fun Coin.toCoinUI(): CoinUI {
    return CoinUI(
        id = id,
        rank = rank,
        name = name,
        symbol = symbol,
        marketCapUsd = marketCapUsd.toDisplayNumberFormat(),
        priceUsd = priceUsd.toDisplayNumberFormat(),
        changePercent24Hr = changePercent24Hr.toDisplayNumberFormat(),
        icon = getDrawableForCoin(symbol)
    )
}


fun Double.toDisplayNumberFormat(): DisplayNumber {
    val formatter = NumberFormat.getNumberInstance(Locale.getDefault()).apply {
        maximumFractionDigits = 2
        minimumFractionDigits = 2
    }
    return DisplayNumber(this, formatter.format(this))
}